# Bookstore Case 2: Checking Inventory

Scenario
--------

Use Case: As Bookstore employees, we want to be able to check the current quantity in stock for a particular title, so that we can decide when to order more of a particular title.

Case 2 Class: InventoryChecker
------------------------------

Responsible for checking the current quantity of a particular title in our inventory.

### Instructions

**Bug reports:** employees report that books out of stock, or maybe uncommon titles seem to cause errors. See if you can trace through the code and find the bug.

Walk through the code together, screen sharing if your group finds that helpful to keep in sync.

Use the sample data from CASE 1 as a starting point for your inventory data.

javadoc of key methods
----------------------

InventoryChecker : getInventoryQuantityForTitle():
```
/**
* Returns the quantity in stock for the provided title, which may be 0 if the title is out
* of stock, or if the Bookstore never had the title.
*
* @param title The title to check inventory for
* @return The number of copies in stock, including 0 if the title is out of stock or never
*         has been
*/
public int getInventoryQuantityForTitle(final String title) { ...

StoreInventory : getQuantityForTitle():

/**
* Checks if the specified title is currently in the inventory, and if so, returns the
* current quantity in stock. If temporarily out of stock, or if title was never in the
* inventory, will return zero.
*
* @param title The title of the book to look for in inventory
* @return The number of the specified title in current inventory, or zero if none found.
*/
public int getQuantityForTitle(final String title) { ...
```
