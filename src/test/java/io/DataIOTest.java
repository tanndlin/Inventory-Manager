package io;

import base.InventoryManager;
import base.Types;
import inventory.Inventory;
import inventory.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.InventoryConverter;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DataIOTest {

    static final String path = "./data/";
    InventoryManager inventoryManager;
    DataIO dataIO;

    @BeforeAll
    static void createTargetDir() {
        File f = new File(path);
        f.mkdir();
    }

    @BeforeEach
    void init() {
        inventoryManager = new InventoryManager(new Inventory(), null);
        inventoryManager.addItem(new Item("A-xxx-xxx-xxx", "Playstation 5", "699.99"));
        inventoryManager.addItem(new Item("A-xxx-xxx-420", "Xbox Series X", "569.50"));
        inventoryManager.addItem(new Item("A-xxx-xxx-069", "3090", "1500"));

        dataIO = new DataIO(inventoryManager.getInventory());
    }

    @Test
    void saveInventoryAsTSV() {
        File f = new File(path + "listSaveTest.tsv");
        f.delete();

        Types.FileFormat format = Types.FileFormat.TSV;
        String inventoryAsTSV = InventoryConverter.getInventoryAsFileType(inventoryManager.getInventory(), format);
        dataIO.saveInventory(f, format);

        assertTrue(f.exists());
    }

    @Test
    void saveInventoryAsHTML() {
        File f = new File(path + "listSaveTest.html");
        f.delete();

        Types.FileFormat format = Types.FileFormat.HTML;
        String inventoryAsTSV = InventoryConverter.getInventoryAsFileType(inventoryManager.getInventory(), format);
        dataIO.saveInventory(f, format);

        assertTrue(f.exists());
    }

    @Test
    void saveInventoryAsJSON() {
        File f = new File(path + "listSaveTest.json");
        f.delete();

        Types.FileFormat format = Types.FileFormat.JSON;
        String inventoryAsTSV = InventoryConverter.getInventoryAsFileType(inventoryManager.getInventory(), format);
        dataIO.saveInventory(f, format);

        assertTrue(f.exists());
    }

    @Test
    void loadInventory() {

    }
}