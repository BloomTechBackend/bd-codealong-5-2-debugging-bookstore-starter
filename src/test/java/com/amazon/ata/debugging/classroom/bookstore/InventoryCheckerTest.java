package com.amazon.ata.debugging.classroom.bookstore;

import bookstore.InventoryChecker;
import bookstore.StoreInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InventoryCheckerTest {
    private InventoryChecker inventoryChecker;
    private StoreInventory storeInventory;

    @BeforeEach
    private void setupInventoryChecker() {
        storeInventory = mock(StoreInventory.class);
        inventoryChecker = new InventoryChecker(storeInventory);
    }

    @Test
    void getInventoryQuanityForTitle_withTitle_usesStoreInventory() {
        // GIVEN
        String title = "Where'd You Go, Bernadette?";
        int quantity = 7;
        when(storeInventory.getQuantityForTitle(title)).thenReturn(quantity);

        // WHEN
        int result = inventoryChecker.getInventoryQuantityForTitle(title);

        // THEN
        verify(storeInventory, times(1)).getQuantityForTitle(title);
        assertEquals(quantity, result,
                     String.format("Expected '%s' to return quantity of %d", title, quantity)
        );
    }

    @Test
    void getInventoryQuanityForTitle_withTitleWithWhitespace_checksWithTrimmedTitle() {
        // GIVEN
        String whitespaceyTitle = "  Where'd You Go, Bernadette?  \n";
        String title = "Where'd You Go, Bernadette?";

        // WHEN
        inventoryChecker.getInventoryQuantityForTitle(whitespaceyTitle);

        // THEN
        verify(storeInventory, times(1)).getQuantityForTitle(title);
    }
}
