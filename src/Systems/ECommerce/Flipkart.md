**RETAIL**

### MVP
- Get all Products
- Add to cart
- Checkout
- Order History
- Payment

### Estimation of Scale
- 1Billion users
- 100Million DAU
- 100M / 86400 = 1000qps ~= read peak 2000qps
- 50M/86400 = 500qps write
- Order
  - OrderId - 8B
  - productId - 8B
  - userId - 8B
  - Amount - 8B
- Approx 50B * 100M = 5GB of order data per day = 150GB /Month = 1.8 TB = 18TB for 10 years
- Orders DB - 
- Products DB (NO Sql) sharded by productId
  - 500Million products = 500M * 1KB = 500GB (need more read replicas)
- User DB (Sql) = 1B * 1Kb = 1TB (SQL)

### CAP
- View Products - Eventually consistent
- Placing Order - Strong consistent
- Order history - Eventual consistency

#### DESIGN Deep Dive
- **API**
  - GetProducts(Filters)
    - Use products DB with filters - Use more read replicas to cater high read qps
    
  - PlaceOrder(requestId,userId,productId)
    - Get Product Availability
    - Get Lock on the Product document
      - Q:If the quantity is 1000 remaining ? And we have 10 threads asking for the same product. Why should we hold the lock on the document until
            we do the payment. 
      - A: Duplicate the documents with the same product and with lesser quantity(My answer)
      - A: Optimistic locking if there are not many concurrent requests
    - Proceed to payment
    - Notify Seller and respond back to the client
    - Client -> LB -> App servers -> Get Products status from Products DB and obtain a lock -> Proceed with payment -> Insert into orders -> notify the seller and client
     - 
  
  - GetOrderHistory(userId)
    - Get from orders table