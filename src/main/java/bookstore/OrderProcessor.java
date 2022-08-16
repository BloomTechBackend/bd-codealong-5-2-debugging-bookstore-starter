package bookstore;

/**
 * Processes orders submitted to the store, removing stock from inventory as
 * appropriate.
 */
public class OrderProcessor {
    private StoreInventory storeInventory;

    /**
     * Construct a new OrderProcessor, using the specified StoreInventory.
     *
     * @param storeInventory The bookstore's inventory to update when orders
     *                       arrive
     */
    public OrderProcessor(final StoreInventory storeInventory) {
        this.storeInventory = storeInventory;
    }

    /**
     * Processes a book order, which may contain multiple lines. Makes best
     * effort to fulfill the order, processing titles that are found and
     * have sufficient copies in stock.
     *
     * Similar to deliveries, each delivered title is on its own line, with
     * two fields (title, quantity), separated by "::".
     * For example:
     *
     * Title A :: 1
     * Title B: Subtitle::1
     * A Very Long Title That Will Work Fine::10
     *
     * @param orderList String representation of the order, with one
     *                  title/quantity pair per line
     * @return {@code true} if the order was completely fulfillable
     */
    public boolean processOrder(final String orderList) {
        final String orderListNoWhitespace = orderList.trim();
        boolean orderCompletelyFulfilled = true;
        for (String orderLine : orderListNoWhitespace.split("\n")) {
            String[] titleAndQuantity = orderLine.split("::");

            String title = titleAndQuantity[0];
            title = title.trim();
            String quantityString = titleAndQuantity[1];
            quantityString = quantityString.trim();
            int quantity = Integer.parseInt(quantityString);

            int remainingQuantity = storeInventory.getQuantityForTitle(title);

            if (remainingQuantity >= quantity) {
                storeInventory.removeInventory(title, quantity);
            } else {
                orderCompletelyFulfilled = false;
            }
        }

        return orderCompletelyFulfilled;
    }
}
