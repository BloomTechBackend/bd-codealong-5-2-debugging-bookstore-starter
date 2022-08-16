# Bookstore Case 1: Processing Deliveries

Scenario
--------

Use Case: As Bookstore employees, we want to be able to process book deliveries to keep our inventory up to date. (No bugs reported)

Case 1 Class: DeliveryProcessor
-------------------------------

Responsible for taking a string from a delivery manifest from a supplier, containing a list of book titles and delivery quantity for each, and updating our inventory for the newly arrived books.

### Instructions

Start with `processDelivery()` and consider different possible scenarios for input data.

javadoc of key methods
----------------------

DeliveryProcessor : processDelivery():

```
/* Processes a list of titles/quantities in a delivery.
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

StoreInventory : addInventory():

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
```
