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
    private Set<InventoryEntry> inventory;

    public StoreInventory() {
        this.inventory = new HashSet<>();
    }

    /**
     * Checks if the specified title is currently in the inventory, and if so, returns the
     * current quantity in stock. If temporarily out of stock, or if title was never in the
     * inventory, will return zero.
     *
     * @param title The title of the book to look for in inventory
     * @return The number of the specified title in current inventory, or zero if none found.
     */
    public int getQuantityForTitle(String title) {
        InventoryEntry entry = this.getEntryByTitle(title);
        if (entry != null) {
            return entry.getQuantity();
        }
        return 0;
    }


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
    public int addInventory(String title, int additionalQuantity) {
        InventoryEntry entry = this.getEntryByTitle(title);
        if (entry == null || additionalQuantity < 0) {
            throw new IllegalArgumentException();
        } else {
            int newQuantity = entry.getQuantity() + additionalQuantity;
            entry.setQuantity(newQuantity);
            return newQuantity;
        }
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
     * @param reducedQuantity The (positive) quantity to reduce the inventory quantity by
     * @return the current total quantity in stock for the specified title
     */
    public void removeInventory(String title, int reducedQuantity) {
        InventoryEntry entry = this.getEntryByTitle(title);
        if (entry == null || reducedQuantity < 0 || entry.getQuantity() < reducedQuantity) {
            throw new IllegalArgumentException();
        } else {
            int newQuantity = entry.getQuantity() - reducedQuantity;
            entry.setQuantity(newQuantity);
        }
    }

    /**
     * Looks up an InventoryEntry by title
     *
     * @param title The title of the book to look up
     * @return the InventoryEntry from inventory for the specified title or null if not found
     */
    private InventoryEntry getEntryByTitle(String title) {
        for (InventoryEntry entry : this.inventory) {
            if (entry.getTitle().equals(title)) {
                return entry;
            }
        }
        return null;
    }
}

