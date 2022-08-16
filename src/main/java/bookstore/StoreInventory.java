package bookstore;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents the current bookstore inventory with the following functionality:
 *  * determining if there is sufficient inventory in stock for a given title
 *  * adding inventory
 *  * removing inventory.
 */
public class StoreInventory {
    // For now, we'll continue using arrays. Soon, we'll learn a better data
    // structure to use for something like this!
    private static final int MAX_INVENTORY_SIZE = 1000;

    private InventoryEntry[] inventoryEntries = new InventoryEntry[MAX_INVENTORY_SIZE];

    /**
     * Adds the specified quantity of the specified title to inventory, creating
     * a new entry if necessary.
     *
     * Throws an IllegalArgumentException if title is empty or null, or if
     * quantity is negative
     *
     * Throws an IllegalStateException if there is no room in the inventory
     * for another title.
     *
     * @param title The title of the book to add to inventory
     * @param additionalQuantity The quantity to add into stock in inventory
     * @return the current total quantity in stock for the specified title
     */
    public int addInventory(final String title, final int additionalQuantity) {
        checkValidTitle(title);
        checkValidQuantity(additionalQuantity);

        for (int i = 0; i < inventoryEntries.length; i++) {
            InventoryEntry entry = inventoryEntries[i];

            // didn't find the title, create a new entry
            if (null == entry) {
                InventoryEntry newEntry = new InventoryEntry(title, additionalQuantity);
                inventoryEntries[i] = newEntry;
                return newEntry.getQuantity();
            }

            if (entry.getTitle().equals(title)) {
                InventoryEntry updatedEntry =
                        new InventoryEntry(title, entry.getQuantity() + additionalQuantity);
                inventoryEntries[i] = updatedEntry;
                return updatedEntry.getQuantity();
            }
        }

        throw new IllegalStateException(
                String.format(
                        "Cannot accept inventory for new title '%s', inventory is full at size %d",
                        title,
                        inventoryEntries.length
                )
        );
    }

    /**
     * Removes the specified quantity from available inventory.
     *
     * Throws an IllegalArgumentException if:
     *     * quantity is negative
     *     * the specified title isn't in the inventory
     *     * the quantity is greater than the inventory available for that title
     *
     * @param title The title of the book to remove inventory for
     * @param reducedQuantity The (positive) quantity to reduce the inventory
     *                        quantity by
     * @return the current total quantity in stock for the specified title
     */
    public int removeInventory(final String title, final int reducedQuantity) {
        checkValidTitle(title);
        checkValidQuantity(reducedQuantity);

        for (int i = 0; i < inventoryEntries.length; i++) {
            InventoryEntry entry = inventoryEntries[i];

            // didn't find the title. Stop searching because it isn't there.
            if (null == entry) {
                break;
            }

            if (entry.getTitle().equals(title)) {
                // make sure there's enough inventory left, then reduce
                if (entry.getQuantity() < reducedQuantity) {
                    throw new IllegalArgumentException(
                            String.format("Inventory for title '%s' is too low (%d) to reduce by %d",
                                    title,
                                    entry.getQuantity(),
                                    reducedQuantity
                            )
                    );
                }

                int updatedQuantity = entry.getQuantity() - reducedQuantity;
                return updatedQuantity;
            }
        }

        throw new IllegalArgumentException(
                String.format(
                        "Cannot remove inventory for title '%s' because it is not in the inventory",
                        title
                )
        );
    }

    /**
     * Checks if the specified title is currently in the inventory, and if so,
     * returns the current quantity in stock. If temporarily out of stock, or if
     * title was never in the inventory, will return zero.
     *
     * @param title The title of the book to look for in inventory
     * @return The number of the specified title in current inventory, or zero if
     * none found.
     */
    public int getQuantityForTitle(final String title) {
        for (InventoryEntry entry : inventoryEntries) {
            if (entry.getTitle().equals(title)) {
                return entry.getQuantity();
            }
        }

        // Didn't find title in inventory
        return 0;
    }


    /**
     * Checks if the given title is valid. Throws IllegalArgumentException if not
     * (if title is null or empty).
     *
     * @param title The title to check for validity
     */
    private void checkValidTitle(final String title) {
        if (null == title || "".equals(title)) {
            throw new IllegalArgumentException(String.format("Illegal title: '%s' ", title));
        }
    }

    /**
     * Checks for valid increments in quantity. Throws IllegalArgumentException
     * if not (if quantity is non-positive).
     *
     * @param quantity The quantity to check for validity
     */
    private void checkValidQuantity(final int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException(String.format("Illegal quantity increment: %d",
                    quantity));
        }
    }
}

