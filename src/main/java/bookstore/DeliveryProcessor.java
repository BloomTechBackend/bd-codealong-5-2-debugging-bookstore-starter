package bookstore;

/**
 * Processes deliveries to the bookstore, adding the received titles into inventory.
 */
public class DeliveryProcessor {
    private StoreInventory inventory;

    /**
     * Creates a DeliveryProcessor using the provided inventory.
     *
     * @param inventory The bookstore's inventory
     */
    public DeliveryProcessor(final StoreInventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Processes a list of titles/quantities in a delivery.
     *
     * Each delivered title on its own line with two fields (title, quantity)
     * separated by "::".
     *
     * For example:
     *
     * Title A :: 4
     * Title B: Subtitle::3
     * A Very Long Title That Will Work Fine::0
     *
     * @param deliveryList a String containing a list of titles delivered, with
     *                     their quantities.
     */
    public void processDelivery(final String deliveryList) {
        final String deliveryListNoWhitespace = deliveryList.trim();
        for (String deliveryLine : deliveryListNoWhitespace.split("\n")) {
            String[] titleAndQuantity = deliveryLine.split("::");

            String title = titleAndQuantity[0];
            title = title.trim();
            String quantityString = titleAndQuantity[1];
            quantityString = quantityString.trim();
            int quantity = Integer.parseInt(quantityString);

            int newQuantity = inventory.addInventory(title, quantity);

            System.out.println(
                    String.format(
                            "Title: '%s', Quantity: %d", title, newQuantity
                    )
            );
        }
    }
}
