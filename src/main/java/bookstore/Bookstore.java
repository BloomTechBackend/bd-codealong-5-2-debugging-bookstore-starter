package bookstore;

public class Bookstore {
    public static void main(String[] args) {

//        checkInventory(); // Case 2
        processOrder(); // Case 3

    }

    static void checkInventory() {
        // GIVEN
        StoreInventory storeInventory = new StoreInventory();
        String title = "Harry Potter ";
        int quantity = 1;
        storeInventory.addInventory(title, quantity);
        InventoryChecker inventoryChecker = new InventoryChecker(storeInventory);

        // WHEN
        int result = inventoryChecker.getInventoryQuantityForTitle(title);

        // THEN
        if (result == quantity) {
            System.out.println("checkInventory Looks good!");
        } else {
            System.out.println("Something is wrong in checkInventory!");
        }
    }

    static void processOrder() {
        // GIVEN
        StoreInventory storeInventory = new StoreInventory();
        String title1 = "Harry Potter";
        int quantity = 5;
        storeInventory.addInventory(title1, quantity);
        String title2 = "Don Quixote";
        storeInventory.addInventory(title2, quantity);
        storeInventory.removeInventory(title2, 2);
        String title3 = "1984";
        storeInventory.addInventory(title3, 2);
        OrderProcessor orderProcessor = new OrderProcessor(storeInventory);
        String orderList = "Harry Potter::3\nDon Quixote::2\n1984::2";

        // WHEN
        boolean result = orderProcessor.processOrder(orderList);

        // THEN
        if (result) {
            System.out.println("processOrder Looks good!");
        } else {
            System.out.println("Something is wrong in processOrder!");
        }

    }
}
