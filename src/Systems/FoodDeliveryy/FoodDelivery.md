ZOMATO , SWIGGY, UBER EATS

** MVP (Minimum Viable Product) **
- User should be able to see all the restaurants in the homepage - Eventual consistency
- User should be able to search for restaurants
- User should be able to place a order (All items from the same restaurant) - Strongly consistent
- Add items to the cart - Strongly consistent
- Do payment - Strongly consistent
- User should be able to see the order history - Eventual consistency

** Estimation of Scale **
- 300 Million users
- 100 Million active users
- 1Million delivery partners
- 1 Million restaurants
- 1Kb of user data - 300M * 1Kb = 300GB of user data
- Order:
    1.requestId - 8B
    2.userId - 8B
    3.restaurantId - 8B
    4.OrderItems{productId, quantity} - 100B
    5.instructions - 8B
- 100M order per day = 100M * 200B = 20GB, 30days = 600GB, 1year = 7200GB ~ 7.2TB, 10 years = 72TB
- Sharding is a must, shard key can be userId
- User DB - SQL
- Restaurant DB : 1M - Quad Trees
- Orders - shard on userId

** CAP **
- User should be able to see all the restaurants in the homepage - Eventual consistency
- User should be able to search for restaurants
- User should be able to place a order (All items from the same restaurant) - Strongly consistent
- Add items to the cart - Strongly consistent
- Do payment - Strongly consistent
- User should be able to see the order history - Eventual consistency

** Design Deep dive **
- PlaceOrder(requestId, userId,restaurantId, OrderItems)
  - Client -> gateway+LB -> app servers(stateless) --userId-> DB Shard(persist) -> Payment -> Place it in a queue for Restaurant Notification
  - Restaurant accepts the order and places it in a queue -> user notification service modifies the status and sends notification 
  - Find delivery partner service will also consume and assign the delivery partner
  - Restaurant  updates the status - Food ready , notification service updates the order status and notifies the user
  - Food is delivered

- Add to cart(userId, restaurantId, itemId, Quantity)
  - sharded by userId

- Get All Restaurants(userId, location)
  - Quad trees
  - App server Cache with list of restaurants for active users
  - Global cache to have list of restaurants to be displayed for requests coming from same grid

- Find nearby delivery partners
  - get lat,long of drivers every 5 seconds and keep track to which quad tree do they belong
  - When request comes for delivery, find partners near to restaurant as distance to restaurant and home will be constant
  - Once a partner gets assigned , send out location details to the user
  