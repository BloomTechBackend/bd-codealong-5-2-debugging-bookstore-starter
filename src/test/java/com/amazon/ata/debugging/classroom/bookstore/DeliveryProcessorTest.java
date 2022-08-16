package com.amazon.ata.debugging.classroom.bookstore;

import bookstore.DeliveryProcessor;
import bookstore.StoreInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeliveryProcessorTest {
    private DeliveryProcessor deliveryProcessor;
    private StoreInventory storeInventory;

    @BeforeEach
    private void setupProcessor() {
        storeInventory = mock(StoreInventory.class);
        deliveryProcessor = new DeliveryProcessor(storeInventory);
    }

    @Test
    void processDelivery_withValidList_processesTitles() {
        // GIVEN
        // delivery processor with 3-title inventory
        String title1 = "Goodnight Moon";
        String title2 = "Educated: A Memoir";
        String title3 = "Born A Crime: Stories from a South African Childhood";
        String deliveryList = "Goodnight Moon :: 1 \n" +
                              "Educated: A Memoir :: 6 \n" +
                              "Born A Crime: Stories from a South African Childhood :: 2\n";
        // allow adding the inventory, and return accurate resulting quantity
        when(storeInventory.addInventory(title1, 1)).thenReturn(1);
        when(storeInventory.addInventory(title2, 6)).thenReturn(7);
        when(storeInventory.addInventory(title3, 2)).thenReturn(4);

        // WHEN - add the three titles
        deliveryProcessor.processDelivery(deliveryList);

        // THEN - inventory receives instructions to add all three titles with correct quantities
        ArgumentCaptor<String> titleArg = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> quantityArg = ArgumentCaptor.forClass(Integer.class);
        verify(storeInventory, times(3)).addInventory(titleArg.capture(), quantityArg.capture());

        List<String> titles = titleArg.getAllValues();
        assertEquals(title1, titles.get(0));
        assertEquals(title2, titles.get(1));
        assertEquals(title3, titles.get(2));

        List<Integer> quantities = quantityArg.getAllValues();
        assertEquals(Integer.valueOf(1), quantities.get(0));
        assertEquals(Integer.valueOf(6), quantities.get(1));
        assertEquals(Integer.valueOf(2), quantities.get(2));
    }
}
