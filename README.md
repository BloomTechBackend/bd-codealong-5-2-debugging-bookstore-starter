# Debugging Bookstore Code-Along

This Code-Along will focus on how to debug code using the debugging tools built into IntelliJ. 

## Project Setup

1. Clone this project to your machine using `git clone <your github url>`
2. Open the project in IntelliJ
3. In IntelliJ, open the Debugging class (java > debugging > Debugging) and run the main method. This should run without any issue.



Description
-----------

We're working on a Bookstore application, focusing on the code that handles book deliveries, book orders, and checks on a particular title's quantity in stock. Our employees have reported a few odd behaviors of the system, so we want to check it out!

We're going to get some practice reading code, and tracing its execution. We'll be our own debugger in a way! This ability to "read code" is important, and will show us a little better what our debugger is doing for us.

We'll use the "scientific method" to create predictions about how the system should behave, and then verify them by walking through the code.

The first one we'll do together, and there shouldn't be any bugs in that code. Then we'll do two in groups, where you'll see if you can explain the weird behavior reported by our Bookstore employees.

Your instructors will provide the code to trace, so you don't need to go starting to look for it.

**If the code is available online, please do not modify the code!** Everyone's looking at the same file. If your group would like to make a copy of the document, share that, and mark it up, feel free to Just try not to delete it so that you've lost the code to trace! (You can always get it from the original doc)

Key classes
-----------

*   StoreInventory: Contains current inventory of books
*   InventoryEntry: Represents current inventory of a single book. "POJO" (plain old Java object, with member variables and getters and/or setters)

javadoc
-------

StoreInventory class:

~~~ java
/**
 * Represents the current bookstore inventory with the following functionality:
 *  * determining if there is sufficient inventory in stock for a given title
 *  * adding inventory
 *  * removing inventory.
 */
public class StoreInventory { ...
~~~

InventoryEntry class:

~~~ java
/**
 * An entry in the bookstore's inventory, containing a book title and quantity in stock.
 */
public class InventoryEntry { ...
~~~

Scenario 1
--------

Use Case: As Bookstore employees, we want to be able to process book deliveries to keep our inventory up to date. (No bugs reported)

Case 1 Class: DeliveryProcessor
-------------------------------

Responsible for taking a string from a delivery manifest from a supplier, containing a list of book titles and delivery quantity for each, and updating our inventory for the newly arrived books.

### Instructions

Start with `processDelivery()` and consider different possible scenarios for input data.

javadoc of key methods
----------------------

DeliveryProcessor : processDelivery():
~~~ java
/**
 * Processes a list of titles/quantities in a delivery.
 *
 * Each delivered title on its own line with two fields (title, quantity) separated by "::".
 * For example:
 *
 * Title A :: 4
 * Title B: Subtitle::3
 * A Very Long Title That Will Work Fine::0
 *
 * @param deliveryList a String containing a list of titles delivered, with their quantities.
 */
public void processDelivery(final String deliveryList) { ...
~~~

StoreInventory : addInventory():

~~~ java
/**
 * Adds the specified quantity of the specified title to inventory, creating a new entry
 * if necessary.
 *
 * Throws an IllegalArgumentException if title is empty or null, or if quantity is negative
 *
 * Throws an IllegalStateException if there is no room in the inventory for another title.
 *
 * @param title The title of the book to add to inventory
 * @param additionalQuantity The quantity to add into stock in inventory
 * @return the current total quantity in stock for the specified title
 */
public int addInventory(final String title, final int additionalQuantity) { ...
~~~

Scenario 2
--------

Use Case: As Bookstore employees, we want to be able to check the current quantity in stock for a particular title, so that we can decide when to order more of a particular title.

Case 2 Class: InventoryChecker
------------------------------

Responsible for checking the current quantity of a particular title in our inventory.

### Instructions

**Bug reports:** employees report that books out of stock, or maybe uncommon titles seem to cause errors. See if you can trace through the code and find the bug.

Use the sample data from CASE 1 as a starting point for your inventory data.

javadoc of key methods
----------------------

InventoryChecker : getInventoryQuantityForTitle():

~~~ java
/**
 * Returns the quantity in stock for the provided title, which may be 0 if the title is out
 * of stock, or if the Bookstore never had the title.
 *
 * @param title The title to check inventory for
 * @return The number of copies in stock, including 0 if the title is out of stock or never
 *         has been
 */
public int getInventoryQuantityForTitle(final String title) { ...
~~~

StoreInventory : getQuantityForTitle():

~~~ java
/**
 * Checks if the specified title is currently in the inventory, and if so, returns the
 * current quantity in stock. If temporarily out of stock, or if title was never in the
 * inventory, will return zero.
 *
 * @param title The title of the book to look for in inventory
 * @return The number of the specified title in current inventory, or zero if none found.
 */
public int getQuantityForTitle(final String title) { ...
~~~

Scenario 3
--------

Use Case: As Bookstore employees, we want to be able to process orders, ensuring that we have the inventory to satisfy a customer order, and update our inventory when an order is processed so that we can keep our inventory up to date.

Case 3 Class: OrderProcessor
----------------------------

Responsible for taking a string from a customer order, containing a list of book titles and ordered quantity for each, checking if there is sufficient inventory for the order, and removing the appropriate quantity from inventory if so.

### INSTRUCTIONS

**Bug reports:** Employees state that our inventory is often overstated, and we sometimes get orders processed for titles for which we're completely out of stock. See if you can trace through the code and find the bug.

Use the sample data from CASE 1 as a starting point for your inventory data. Keep the inventory data table from CASES 1&2, but now update it as orders are processed. Pay close attention to what happens!

javadoc of key methods
----------------------

OrderProcessor : processOrder():
~~~ java
/**
 * Processes a book order, which may contain multiple lines. Makes best effort to fulfill
 * the order, processing titles that are found and have sufficient copies in stock.
 *
 * Similar to deliveries, each delivered title is on its own line, with two fields
 * (title, quantity), separated by "::".
 * For example:
 *
 * Title A :: 1
 * Title B: Subtitle::1 
 * A Very Long Title That Will Work Fine::10
*
 * @param orderList String representation of the order, with one title/quantity pair per line
 * @return {@code true} if the order was completely fulfillable
 */
public boolean processOrder(final String orderList) { ...
~~~

StoreInventory : removeInventory():

~~~ java
/**
 * Removes the specified quantity from available inventory.
 *
 * Throws an IllegalArgumentException if:
 *     * quantity is negative
 *     * the specified title isn't in the inventory
 *     * the quantity is greater than the inventory available for that title
 *
 * @param title The title of the book to remove inventory for
 * @param reducedQuantity The (positive) quantity to reduce the inventory quantity by
 * @return the current total quantity in stock for the specified title
 */
public int removeInventory(final String title, final int reducedQuantity) { ...
~~~
