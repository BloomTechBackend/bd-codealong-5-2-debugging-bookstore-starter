# Bookstore Case 3: Processing Orders

Scenario
--------

Use Case: As Bookstore employees, we want to be able to process orders, ensuring that we have the inventory to satisfy a customer order, and update our inventory when an order is processed so that we can keep our inventory up to date.

Case 3 Class: OrderProcessor
----------------------------

Responsible for taking a string from a customer order, containing a list of book titles and ordered quantity for each, checking if there is sufficient inventory for the order, and removing the appropriate quantity from inventory if so.

### INSTRUCTIONS

**Bug reports:** Employees state that our inventory is often overstated, and we sometimes get orders processed for titles for which we're completely out of stock. See if you can trace through the code and find the bug.

Walkthrough the code together, screen sharing if your group finds that helpful to keep in sync.

Use the sample data from CASE 1 as a starting point for your inventory data. Keep the inventory data table from CASES 1&2, but now update it as orders are processed. Pay close attention to what happens!

javadoc of key methods
----------------------

OrderProcessor : processOrder():

/\*\*
\* Processes a book order, which may contain multiple lines. Makes best effort to fulfill
\* the order, processing titles that are found and have sufficient copies in stock.
\*
\* Similar to deliveries, each delivered title is on its own line, with two fields
\* (title, quantity), separated by "::".
\* For example:
\*
\* Title A :: 1
\* Title B: Subtitle::1
\* A Very Long Title That Will Work Fine::10
\*
\* @param orderList String representation of the order, with one title/quantity pair per line
\* @return {@code true} if the order was completely fulfillable
\*/
public boolean processOrder(final String orderList) { ...

StoreInventory : removeInventory():

/\*\*
\* Removes the specified quantity from available inventory.
\*
\* Throws an IllegalArgumentException if:
\*     \* quantity is negative
\*     \* the specified title isn't in the inventory
\*     \* the quantity is greater than the inventory available for that title
\*
\* @param title The title of the book to remove inventory for
\* @param reducedQuantity The (positive) quantity to reduce the inventory quantity by
\* @return the current total quantity in stock for the specified title
\*/
public int removeInventory(final String title, final int reducedQuantity) { ...
