package bookstore;

/**
 * Utility that allows Bookstore employees to search for the current quantity
 * in stock for a given title.
 */
public class InventoryChecker {
    private StoreInventory storeInventory;

    /**
     * Constructs an InventoryChecker, with the given StoreInventory.
     *
     * @param storeInventory The Bookstore's StoreInventory to use
     */
    public InventoryChecker(final StoreInventory storeInventory) {
        this.storeInventory = storeInventory;
    }

    /**
     * Returns the quantity in stock for the provided title, which may be 0 if
     * the title is out of stock, or if the Bookstore never had the title.
     *
     * @param title The title to check inventory for
     * @return The number of copies in stock, including 0 if the title is
     * out of stock, or never has been in stock
     */
    public int getInventoryQuantityForTitle(final String title) {
        String cleanTitle = title.trim();
        return storeInventory.getQuantityForTitle(cleanTitle);
    }
}
