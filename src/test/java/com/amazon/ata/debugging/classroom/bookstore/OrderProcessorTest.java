package com.amazon.ata.debugging.classroom.bookstore;

import bookstore.OrderProcessor;
import bookstore.StoreInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderProcessorTest {
    private OrderProcessor orderProcessor;
    private StoreInventory storeInventory;

    @BeforeEach
    private void setupOrderProcessor() {
        storeInventory = mock(StoreInventory.class);
        orderProcessor = new OrderProcessor(storeInventory);
    }

    @Test
    void processOrder_withValidOrder_attemptsToRemoveInventory() {
        // GIVEN
        // order processor with 2-title inventory
        String title1 = "The Sisters Brothers";
        String title2 = "Oryx and Crake";
        int originalQuantity1 = 3;
        int originalQuantity2 = 5;
        // order with valid titles and quantities less than inventory
        String orderList = "The Sisters Brothers :: 2 \n" +
                           "Oryx and Crake :: 5 \n";
        // inventory is >= order quantities
        when(storeInventory.getQuantityForTitle(title1)).thenReturn(originalQuantity1);
        when(storeInventory.removeInventory(title1, 2)).thenReturn(originalQuantity1);
        when(storeInventory.getQuantityForTitle(title2)).thenReturn(originalQuantity2);
        when(storeInventory.removeInventory(title2, 5)).thenReturn(originalQuantity2);

        // WHEN
        boolean orderCompletelyFulfilled = orderProcessor.processOrder(orderList);

        // THEN
        // order was fulfilled completely
        assertTrue(orderCompletelyFulfilled, "Expected order to be fully fulfilled, but wasn't");

        // inventory received instructions to remove the appropriate quantities for the correct titles
        ArgumentCaptor<String> titleArg = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> quantityArg = ArgumentCaptor.forClass(Integer.class);
        verify(storeInventory, times(2)).removeInventory(titleArg.capture(), quantityArg.capture());

        List<String> titles = titleArg.getAllValues();
        assertEquals(title1, titles.get(0));
        assertEquals(title2, titles.get(1));

        List<Integer> quantities = quantityArg.getAllValues();
        assertEquals(Integer.valueOf(2), quantities.get(0));
        assertEquals(Integer.valueOf(5), quantities.get(1));
    }
}
