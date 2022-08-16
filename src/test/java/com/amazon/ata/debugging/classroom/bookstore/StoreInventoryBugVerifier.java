package com.amazon.ata.debugging.classroom.bookstore;

import bookstore.StoreInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Verifies that the bugs we expect are in fact present in the StoreInventory class.
 */
class StoreInventoryBugVerifier {
    private StoreInventory storeInventory;
    private String titleWithTwoInventory;
    private String titleNotInInventory;

    @BeforeEach
    private void populateInventory() {
        storeInventory = new StoreInventory();
        titleWithTwoInventory = "Case Histories";
        titleNotInInventory = "Cloud Atlas";
        storeInventory.addInventory(titleWithTwoInventory, 2);
    }

    // removeInventory

    // quantity doesn't change when removeQuantity called (or should it be changed to the quantity removed?)
    // TODO: or should the quantity be set to the quantity removed?
    @Test
    void removeInventory_quantityRemovedLessThanCurrentInventory_quantityRemainingIsUnchanged() {
        // GIVEN - item in inventory

        // WHEN - remove less than current inventory
        storeInventory.removeInventory(titleWithTwoInventory, 1);

        // THEN - the original inventory is unchanged
        assertEquals(2, storeInventory.getQuantityForTitle(titleWithTwoInventory),
                     String.format("Expected bug missing! Expect removing inventory to leave quantity for title " +
                                   "'%s' unchanged",
                                   titleWithTwoInventory
                     )
        );
    }


    // getQuantityForTitle
    @Test
    void getQuantityForTitle_missingTitle_throwsException() {
        // GIVEN - title not in inventory

        // WHEN + THEN - get quantity for missing title -- NPE
        assertThrows(NullPointerException.class, () -> storeInventory.getQuantityForTitle(titleNotInInventory));
    }

}
