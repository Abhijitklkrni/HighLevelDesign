. Find the Total Cost of All Orders
Question: Given a list of Order objects, calculate the total cost of all orders.

java
Copy code
List<Order> orders = Arrays.asList(
new Order(1, Arrays.asList(new Item("Item1", 10), new Item("Item2", 20))),
new Order(2, Arrays.asList(new Item("Item3", 30), new Item("Item4", 40)))
);

double totalCost = orders.stream()
.flatMap(order -> order.getItems().stream())
.mapToDouble(Item::getPrice)
.sum();

System.out.println("Total Cost: " + totalCost);
2. Find the Most Expensive Item in All Orders
   Question: Given a list of Order objects, find the most expensive item across all orders.

java
Copy code
List<Order> orders = Arrays.asList(
new Order(1, Arrays.asList(new Item("Item1", 10), new Item("Item2", 20))),
new Order(2, Arrays.asList(new Item("Item3", 30), new Item("Item4", 40)))
);

Item mostExpensiveItem = orders.stream()
.flatMap(order -> order.getItems().stream())
.max(Comparator.comparingDouble(Item::getPrice))
.orElse(null);

System.out.println("Most Expensive Item: " + (mostExpensiveItem != null ? mostExpensiveItem.getName() : "None"));
3. List All Unique Item Names
   Question: Given a list of Order objects, list all unique item names.

java
Copy code
List<Order> orders = Arrays.asList(
new Order(1, Arrays.asList(new Item("Item1", 10), new Item("Item2", 20))),
new Order(2, Arrays.asList(new Item("Item1", 30), new Item("Item3", 40)))
);

Set<String> uniqueItemNames = orders.stream()
.flatMap(order -> order.getItems().stream())
.map(Item::getName)
.collect(Collectors.toSet());

System.out.println("Unique Item Names: " + uniqueItemNames);
4. Group Items by Their Price
   Question: Given a list of Order objects, group items by their price.

java
Copy code
List<Order> orders = Arrays.asList(
new Order(1, Arrays.asList(new Item("Item1", 10), new Item("Item2", 20))),
new Order(2, Arrays.asList(new Item("Item3", 10), new Item("Item4", 40)))
);

Map<Double, List<Item>> itemsByPrice = orders.stream()
.flatMap(order -> order.getItems().stream())
.collect(Collectors.groupingBy(Item::getPrice));

System.out.println("Items Grouped by Price: " + itemsByPrice);
5. Find the Order with the Highest Total Cost
   Question: Given a list of Order objects, find the order with the highest total cost.

java
Copy code
List<Order> orders = Arrays.asList(
new Order(1, Arrays.asList(new Item("Item1", 10), new Item("Item2", 20))),
new Order(2, Arrays.asList(new Item("Item3", 30), new Item("Item4", 40)))
);

Order orderWithHighestCost = orders.stream()
.max(Comparator.comparingDouble(order -> order.getItems().stream().mapToDouble(Item::getPrice).sum()))
.orElse(null);

System.out.println("Order with Highest Total Cost: " + (orderWithHighestCost != null ? orderWithHighestCost.getId() : "None"));
6. Get a Summary of Prices in All Orders
   Question: Given a list of Order objects, generate a summary of item prices including count, sum, min, average, and max.

java
Copy code
List<Order> orders = Arrays.asList(
new Order(1, Arrays.asList(new Item("Item1", 10), new Item("Item2", 20))),
new Order(2, Arrays.asList(new Item("Item3", 30), new Item("Item4", 40)))
);

DoubleSummaryStatistics priceSummary = orders.stream()
.flatMap(order -> order.getItems().stream())
.collect(Collectors.summarizingDouble(Item::getPrice));

System.out.println(priceSummary);
7. Find the Order with the Most Items
   Question: Given a list of Order objects, find the order that contains the most items.

java
Copy code
List<Order> orders = Arrays.asList(
new Order(1, Arrays.asList(new Item("Item1", 10), new Item("Item2", 20))),
new Order(2, Arrays.asList(new Item("Item3", 30), new Item("Item4", 40), new Item("Item5", 50)))
);

Order orderWithMostItems = orders.stream()
.max(Comparator.comparingInt(order -> order.getItems().size()))
.orElse(null);

System.out.println("Order with Most Items: " + (orderWithMostItems != null ? orderWithMostItems.getId() : "None"));
These questions should give you a solid understanding of how to work with Java 8 Streams in the context of aggregated classes.





8. Filter Orders Containing a Specific Item
   Question: Given a list of Order objects, find all orders that contain an item with a specific name (e.g., "Item1").

java
Copy code
List<Order> orders = Arrays.asList(
new Order(1, Arrays.asList(new Item("Item1", 10), new Item("Item2", 20))),
new Order(2, Arrays.asList(new Item("Item3", 30), new Item("Item1", 40))),
new Order(3, Arrays.asList(new Item("Item4", 50), new Item("Item5", 60)))
);

List<Order> ordersWithItem1 = orders.stream()
.filter(order -> order.getItems().stream().anyMatch(item -> "Item1".equals(item.getName())))
.collect(Collectors.toList());

System.out.println("Orders with Item1: " + ordersWithItem1);
9. Find the Least Expensive Item in Each Order
   Question: Given a list of Order objects, find the least expensive item in each order.

java
Copy code
List<Order> orders = Arrays.asList(
new Order(1, Arrays.asList(new Item("Item1", 10), new Item("Item2", 20))),
new Order(2, Arrays.asList(new Item("Item3", 30), new Item("Item4", 40))),
new Order(3, Arrays.asList(new Item("Item5", 50), new Item("Item6", 60)))
);

List<Item> leastExpensiveItems = orders.stream()
.map(order -> order.getItems().stream().min(Comparator.comparingDouble(Item::getPrice)).orElse(null))
.collect(Collectors.toList());

System.out.println("Least Expensive Items: " + leastExpensiveItems);
10. Calculate the Total Cost Per Order
    Question: Given a list of Order objects, calculate the total cost of items for each order and return a map of order ID to total cost.

java
Copy code
List<Order> orders = Arrays.asList(
new Order(1, Arrays.asList(new Item("Item1", 10), new Item("Item2", 20))),
new Order(2, Arrays.asList(new Item("Item3", 30), new Item("Item4", 40))),
new Order(3, Arrays.asList(new Item("Item5", 50), new Item("Item6", 60)))
);

Map<Integer, Double> totalCostPerOrder = orders.stream()
.collect(Collectors.toMap(
Order::getId,
order -> order.getItems().stream().mapToDouble(Item::getPrice).sum()
));

System.out.println("Total Cost Per Order: " + totalCostPerOrder);
11. Create a List of All Items Across All Orders
    Question: Given a list of Order objects, create a single list containing all items from all orders.

java
Copy code
List<Order> orders = Arrays.asList(
new Order(1, Arrays.asList(new Item("Item1", 10), new Item("Item2", 20))),
new Order(2, Arrays.asList(new Item("Item3", 30), new Item("Item4", 40))),
new Order(3, Arrays.asList(new Item("Item5", 50), new Item("Item6", 60)))
);

List<Item> allItems = orders.stream()
.flatMap(order -> order.getItems().stream())
.collect(Collectors.toList());

System.out.println("All Items: " + allItems);
12. Find the Average Price of Items in Each Order
    Question: Given a list of Order objects, find the average price of items for each order.

java
Copy code
List<Order> orders = Arrays.asList(
new Order(1, Arrays.asList(new Item("Item1", 10), new Item("Item2", 20))),
new Order(2, Arrays.asList(new Item("Item3", 30), new Item("Item4", 40))),
new Order(3, Arrays.asList(new Item("Item5", 50), new Item("Item6", 60)))
);

Map<Integer, Double> averagePricePerOrder = orders.stream()
.collect(Collectors.toMap(
Order::getId,
order -> order.getItems().stream().mapToDouble(Item::getPrice).average().orElse(0.0)
));

System.out.println("Average Price Per Order: " + averagePricePerOrder);
13. List Orders Sorted by Total Cost
    Question: Given a list of Order objects, sort the orders by their total cost in descending order.

java
Copy code
List<Order> orders = Arrays.asList(
new Order(1, Arrays.asList(new Item("Item1", 10), new Item("Item2", 20))),
new Order(2, Arrays.asList(new Item("Item3", 30), new Item("Item4", 40))),
new Order(3, Arrays.asList(new Item("Item5", 50), new Item("Item6", 60)))
);

List<Order> sortedOrders = orders.stream()
.sorted(Comparator.comparingDouble(order -> order.getItems().stream().mapToDouble(Item::getPrice).sum()).reversed())
.collect(Collectors.toList());

System.out.println("Sorted Orders by Total Cost: " + sortedOrders);
14. Count the Number of Items in Each Order
    Question: Given a list of Order objects, count the number of items in each order and return a map of order ID to item count.

java
Copy code
List<Order> orders = Arrays.asList(
new Order(1, Arrays.asList(new Item("Item1", 10), new Item("Item2", 20))),
new Order(2, Arrays.asList(new Item("Item3", 30), new Item("Item4", 40))),
new Order(3, Arrays.asList(new Item("Item5", 50), new Item("Item6", 60)))
);

Map<Integer, Long> itemCountPerOrder = orders.stream()
.collect(Collectors.toMap(
Order::getId,
order -> order.getItems().stream().count()
));

System.out.println("Item Count Per Order: " + itemCountPerOrder);
15. Find Orders Where All Items Are Below a Certain Price
    Question: Given a list of Order objects, find all orders where all items have a price below a specified amount (e.g., 50).

java
Copy code
List<Order> orders = Arrays.asList(
new Order(1, Arrays.asList(new Item("Item1", 10), new Item("Item2", 20))),
new Order(2, Arrays.asList(new Item("Item3", 30), new Item("Item4", 40))),
new Order(3, Arrays.asList(new Item("Item5", 50), new Item("Item6", 60)))
);

double priceThreshold = 50;
List<Order> ordersBelowThreshold = orders.stream()
.filter(order -> order.getItems().stream().allMatch(item -> item.getPrice() < priceThreshold))
.collect(Collectors.toList());

System.out.println("Orders with All Items Below " + priceThreshold + ": " + ordersBelowThreshold);
These additional examples should further enhance your understanding and proficiency with Java 8 Streams, particularly in handling aggregated classes like Order and Item