package com.amazon.ata.debugging.classroom.bookstore;

import bookstore.StoreInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Verifies the StoreInventory class behaves as *we want it to*, which includes mostly behaving correctly, but
 * with a few expected bugs (that our participant debuggers will find!).
 */
class StoreInventoryTest {
    private StoreInventory storeInventory;
    private String titleWithZeroInventory;
    private String titleWithOneInventory;
    private String titleNotInInventory;

    @BeforeEach
    private void populateInventory() {
        titleWithZeroInventory = "The Hitchhiker's Guide to the Galaxy";
        titleWithOneInventory = "The Devil Wears Prada";
        titleNotInInventory = "How to Win Friends and Influence People";

        storeInventory = new StoreInventory();
        storeInventory.addInventory(titleWithZeroInventory, 0);
        storeInventory.addInventory(titleWithOneInventory, 1);
    }

    // addInventory

    @Test
    void addInventory_existingEntry_sumsQuantity() {
        // GIVEN - title with quantity of one
        int incrementalQuantity = 3;

        // WHEN - add more inventory
        int result = storeInventory.addInventory(titleWithOneInventory, incrementalQuantity);

        // THEN - inventory should be summed
        assertReturnAndCurrentQuantityAreExpected(
            1 + incrementalQuantity,
            result,
            storeInventory,
            titleWithOneInventory,
            "adding quantity of " + incrementalQuantity
        );
    }

    @Test
    void addInventory_entryNotInInventory_addsNewEntryWithQuantity() {
        // GIVEN - title missing from inventory
        int initialQuantity = 2;

        // WHEN - add inventory for missing title
        int result = storeInventory.addInventory(titleNotInInventory, initialQuantity);

        // THEN - should now be in inventory with specified quantity
        assertReturnAndCurrentQuantityAreExpected(
            initialQuantity,
            result,
            storeInventory,
            titleNotInInventory,
            "adding quantity to title not yet in inventory of " + initialQuantity
        );
    }

    @Test
    void addInventory_existingEntryZeroQuantity_doesntChangeQuantity() {
        // GIVEN - title in inventory

        // WHEN - add inventory for existing title with quantity of zero
        int result = storeInventory.addInventory(titleWithOneInventory, 0);

        // THEN - quantity should be the same
        assertReturnAndCurrentQuantityAreExpected(
            1,
            result,
            storeInventory,
            titleWithOneInventory,
            "adding inventory with quantity of zero"
        );
    }

    @Test
    void addInventory_nonexistingEntryZeroQuantity_createsNewEntryWithZeroQuantity() {
        // GIVEN - title in inventory

        // WHEN - add inventory for non-existing title with quantity of zero
        int result = storeInventory.addInventory(titleNotInInventory, 0);

        // THEN - quantity should be zero
        assertReturnAndCurrentQuantityAreExpected(
            0,
            result,
            storeInventory,
            titleNotInInventory,
            "adding inventory quantity of zero for title not yet in inventory"
        );
    }

    @Test
    void addInventory_nullTitle_isRejected() {
        // GIVEN - an inventory

        // WHEN + THEN - add inventory for null title throws exception
        assertThrows(IllegalArgumentException.class, () -> storeInventory.addInventory(null, 1));
    }

    @Test
    void addInventory_emptyTitle_isRejected() {
        // GIVEN - an inventory

        // WHEN + THEN - add inventory for empty title throws exception
        assertThrows(IllegalArgumentException.class, () -> storeInventory.addInventory("", 1));
    }

    @Test
    void addInventory_negativeQuantity_throwsException() {
        // GIVEN - an inventory

        // WHEN + THEN - add negative inventory throws exception
        assertThrows(IllegalArgumentException.class, () -> storeInventory.addInventory(titleWithOneInventory, -1));
    }

    // removeInventory

    @Test
    void removeInventory_existingTitle_resultIsCorrectQuantity() {
        // GIVEN - title in inventory
        String title = "Nothing to Envy";
        int initialQuantity = 6;
        int quantityReduction = 1;
        storeInventory.addInventory(title, initialQuantity);

        // WHEN - remove
        int result = storeInventory.removeInventory(title, quantityReduction);

        // THEN - expect result to be correct -- don't check current inventory => therein lies bug
        assertEquals(initialQuantity - quantityReduction, result,
                     String.format("Expected remove inventory result for title '%s' to be original - reduction", title)
        );
    }

    @Test
    void removeInventory_removeEntireInventory_returnsZero() {
        // GIVEN - title in inventory
        String title = "Nothing to Envy";
        int initialQuantity = 6;
        storeInventory.addInventory(title, initialQuantity);

        // WHEN - remove all
        int result = storeInventory.removeInventory(title, initialQuantity);

        // THEN - expect result to be correct -- don't check current inventory => therein lies bug
        assertEquals(0, result,
                     String.format("Expected remove all inventory result for title '%s' to be zero", title)
        );
    }

    @Test
    void removeInventory_quantityRemovedGreaterThanCurrentQuantity_isRejected() {
        // GIVEN - title in inventory

        // WHEN + THEN - remove more than in inventory throws exception
        assertThrows(IllegalArgumentException.class, () -> storeInventory.removeInventory(titleWithOneInventory, 15));
    }

    @Test
    void removeInventory_nullTitle_isRejected() {
        // GIVEN - an inventory

        // WHEN + THEN - remove inventory for null title throws exception
        assertThrows(IllegalArgumentException.class, () -> storeInventory.removeInventory(null, 1));
    }

    @Test
    void removeInventory_emptyTitle_isRejected() {
        // GIVEN - an inventory

        // WHEN + THEN - remove inventory for empty title throws exception
        assertThrows(IllegalArgumentException.class, () -> storeInventory.removeInventory("", 1));
    }

    @Test
    void removeInventory_negativeQuantity_isRejected() {
        // GIVEN - an inventory

        // WHEN + THEN - remove inventory with negative quantity throws exception
        assertThrows(IllegalArgumentException.class, () -> storeInventory.removeInventory(titleWithOneInventory, -1));
    }

    // getQuantityForTitle

    @Test
    void getQuantityForTitle_zeroInventory_returnsZero() {
        // GIVEN - title with zero quantity

        // WHEN - fetch quantity
        int result = storeInventory.getQuantityForTitle(titleWithZeroInventory);

        // THEN - result is zero
        assertEquals(0, result);
    }

    @Test
    void getQuantityForTitle_positiveInventory_returnsCorrectQuantity() {
        // GIVEN - title with positive quantity

        // WHEN - fetch quantity
        int result = storeInventory.getQuantityForTitle(titleWithOneInventory);

        // THEN - result is one
        assertEquals(1, result);
    }

    // helpers

    private void assertReturnAndCurrentQuantityAreExpected(
        int expected,
        int result,
        StoreInventory storeInventoryToCheck,
        String title,
        String operation
    ) {
        // verify result
        assertEquals(expected, result,
                     String.format("Expected result after %s for title '%s' would be %d, but was not",
                                   operation,
                                   title,
                                   expected
                     )
        );

        // verify quantity in inventory
        assertEquals(expected, storeInventoryToCheck.getQuantityForTitle(title),
                     String.format("Expected inventory quantity after %s for title '%s' would be %d, but was not",
                                   operation,
                                   title,
                                   expected
                     )
        );
    }
}
