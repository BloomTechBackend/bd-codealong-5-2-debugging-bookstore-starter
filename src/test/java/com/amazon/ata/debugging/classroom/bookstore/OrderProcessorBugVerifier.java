package com.amazon.ata.debugging.classroom.bookstore;

import bookstore.OrderProcessor;
import bookstore.StoreInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderProcessorBugVerifier {
    private OrderProcessor orderProcessor;
    private StoreInventory storeInventory;

    @BeforeEach
    private void setupOrderProcessor() {
        storeInventory = new StoreInventory();
        orderProcessor = new OrderProcessor(storeInventory);
    }

    @Test
    void processOrder_withValidOrder_attemptsToRemoveInventory() {
        // GIVEN
        // order processor with 2-title inventory
        String title1 = "The Sisters Brothers";
        String title2 = "Oryx and Crake";
        // inventory is >= order quantities
        int originalQuantity1 = 3;
        int originalQuantity2 = 5;
        storeInventory.addInventory(title1, originalQuantity1);
        storeInventory.addInventory(title2, originalQuantity2);

        // order with valid titles and quantities less than inventory
        String orderList = "The Sisters Brothers :: 2 \n" +
                           "Oryx and Crake :: 5 \n";

        // WHEN
        boolean orderCompletelyFulfilled = orderProcessor.processOrder(orderList);

        // THEN
        // order was fulfilled completely
        assertTrue(orderCompletelyFulfilled, "Expected order to be fully fulfilled, but wasn't");

        // the original quantities remain in inventory
        assertEquals(originalQuantity1, storeInventory.getQuantityForTitle(title1));
        assertEquals(originalQuantity2, storeInventory.getQuantityForTitle(title2));
    }
}
