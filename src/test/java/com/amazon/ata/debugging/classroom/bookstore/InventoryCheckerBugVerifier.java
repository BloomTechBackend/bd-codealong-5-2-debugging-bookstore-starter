package com.amazon.ata.debugging.classroom.bookstore;

import bookstore.InventoryChecker;
import bookstore.StoreInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InventoryCheckerBugVerifier {
    private InventoryChecker inventoryChecker;
    private StoreInventory storeInventory;

    @BeforeEach
    private void setupInventoryChecker() {
        storeInventory = new StoreInventory();
        inventoryChecker = new InventoryChecker(storeInventory);
    }

    @Test
    void getInventoryQuanityForTitle_withTitleNotInInventory_throwsNullPointerException() {
        // GIVEN - inventory checker with 1-title inventory
        storeInventory.addInventory("Some title in inventory", 1);

        // WHEN + THEN - checking inventory for title not in inventory throws NPE
        assertThrows(
            NullPointerException.class,
            () -> inventoryChecker.getInventoryQuantityForTitle("Title not in inventory")
        );
    }
}
