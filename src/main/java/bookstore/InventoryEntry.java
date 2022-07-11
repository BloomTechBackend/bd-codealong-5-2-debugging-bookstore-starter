package bookstore;

import java.util.Objects;

/**
 * An entry in the bookstore's inventory, containing a book title and quantity in stock.
 */
public class InventoryEntry {
    private String title;
    private int quantity;

    public InventoryEntry(String title) {
        this.title = title;
        this.quantity = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryEntry that = (InventoryEntry) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}