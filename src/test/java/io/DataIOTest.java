/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Tanner Sandlin
 */

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
        f.mkdir(); // Don't care about the result of this
    }

    @BeforeEach
    void init() {
        inventoryManager = new InventoryManager(new Inventory(), null);
        inventoryManager.addItem(new Item("A-XXX-XXX-XXX", "Playstation 5", "699.99"));
        inventoryManager.addItem(new Item("A-XXX-XXX-420", "Xbox Series X", "569.50"));
        inventoryManager.addItem(new Item("A-XXX-XXX-069", "3090", "1500"));

        dataIO = new DataIO(inventoryManager.getInventory());
    }

    @Test
    void saveInventoryAsTSV() {
        File f = new File(path + "listSaveTest.tsv");
        f.delete(); // Don't care about the result of this

        Types.FileFormat format = Types.FileFormat.TSV;
        String inventoryAsTSV = InventoryConverter.getInventoryAsFileType(inventoryManager.getInventory(), format);
        dataIO.saveInventory(f);

        assertTrue(f.exists());
    }

    @Test
    void saveInventoryAsHTML() {
        File f = new File(path + "listSaveTest.html");
        f.delete(); // Don't care about the result of this

        Types.FileFormat format = Types.FileFormat.HTML;
        String inventoryAsTSV = InventoryConverter.getInventoryAsFileType(inventoryManager.getInventory(), format);
        dataIO.saveInventory(f);

        assertTrue(f.exists());
    }

    @Test
    void saveInventoryAsJSON() {
        File f = new File(path + "listSaveTest.json");
        f.delete(); // Don't care about the result of this

        Types.FileFormat format = Types.FileFormat.JSON;
        String inventoryAsTSV = InventoryConverter.getInventoryAsFileType(inventoryManager.getInventory(), format);
        dataIO.saveInventory(f);

        assertTrue(f.exists());
    }

    @Test
    void loadInventoryFromTSV() {
        File f = new File(path + "listLoadTest.tsv");
        Inventory inventory = dataIO.loadInventory(f);

        assertEquals("Playstation 5", inventory.getItems().get(0).getName());
        assertEquals(699.99, inventory.getItems().get(0).getValue());
        assertEquals("A-XXX-XXX-420", inventory.getItems().get(1).getSerialNumber());
    }

    @Test
    void loadInventoryFromHTML() {
        File f = new File(path + "listLoadTest.html");
        Inventory inventory = dataIO.loadInventory(f);

        assertEquals("Playstation 5", inventory.getItems().get(0).getName());
        assertEquals(699.99, inventory.getItems().get(0).getValue());
        assertEquals("A-XXX-XXX-420", inventory.getItems().get(1).getSerialNumber());
    }

    @Test
    void loadInventoryFromJSON() {
        File f = new File(path + "listLoadTest.json");
        Inventory inventory = dataIO.loadInventory(f);

        assertEquals("Playstation 5", inventory.getItems().get(0).getName());
        assertEquals(699.99, inventory.getItems().get(0).getValue());
        assertEquals("A-XXX-XXX-420", inventory.getItems().get(1).getSerialNumber());
    }
}