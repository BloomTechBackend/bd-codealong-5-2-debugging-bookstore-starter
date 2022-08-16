package bookstore;

import java.util.Objects;

/**
 * An entry in the bookstore's inventory, containing a book title and quantity in stock.
 */
public class InventoryEntry {
    private String title;
    private int quantity;

    /**
     * Create a new InventoryEntry with given title and quantity.
     * @param title non-null, non-empty String with the book's title
     * @param quantity how many of the book are in stock
     */
    public InventoryEntry(final String title, final int quantity) {
        if (null == title || "".equals(title)) {
            throw new IllegalArgumentException(String.format(
                    "Cannot create an InventoryEntry with null/empty title: '%s'", title)
            );
        }

        this.title = title;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }
}