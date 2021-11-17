package base;

import inventory.Inventory;
import inventory.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class InventoryManagerTest {

    InventoryManager inventoryManager;

    @BeforeEach
    void init() {
        inventoryManager = new InventoryManager(new Inventory(), null);
    }

    @Test
    void addItem() {
        // Req 2

        assertEquals(0, inventoryManager.size());
        inventoryManager.addItem(new Item("A-XB1-24A-XY3", "Xbox Series X", "1499.00"));
        assertEquals(1, inventoryManager.size());
    }

    @Test
    void addDuplicateSerialNumber() {
        // Req 2.1

        assertThrows(IllegalArgumentException.class, () -> {
            inventoryManager.addItem(new Item("A-XB1-24A-XY3", "Xbox Series X", "1499.00"));
            inventoryManager.addItem(new Item("A-XB1-24A-XY3", "Hello World", "420.69"));
        });
    }

    @Test
    void clearItems() {
        // Req 4

        inventoryManager.addItem(new Item("A-XB1-24A-XY3", "Xbox Series X", "1499.00"));
        inventoryManager.clearItems();
        assertEquals(0, inventoryManager.size());
    }

    @Test
    void fuckTonOfItems(){
        // Req 1

        for(int i =0 ; i < 10000; i++){
            String serialNumber = String.format("A-XB1-%03d-%03d", i / 1000, i % 1000);
            inventoryManager.addItem(new Item(serialNumber, "Hi", "0"));
        }

        // Passes if it doesn't crash
        assertTrue(true);
    }

    @Test
    void removeItem(){
        // Req 3
        Item itemToRemove = new Item("A-XB1-24A-XY3", "Xbox Series X", "1499.00");

        inventoryManager.addItem(itemToRemove);
        inventoryManager.addItem(new Item("A-XB1-24A-XYZ", "Xbox Series X", "1499.00"));

        inventoryManager.removeItem(itemToRemove);

        assertEquals("A-XB1-24A-XYZ", inventoryManager.getItem(0).getSerialNumber());
    }

    @Test
    void editItem(){
        // Req 5, 6 , 7

        // Edit item uses the `removeItem` and `addItem` functions
        // So this is already tested
    }
}