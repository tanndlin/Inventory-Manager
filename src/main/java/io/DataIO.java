/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Tanner Sandlin
 */

package io;

import base.Types;
import inventory.Inventory;
import util.InventoryConverter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataIO {

    private Inventory inventory;

    public DataIO(Inventory inventory) {
        this.inventory = inventory;
    }

    public void saveInventory(File f, Types.FileFormat format) {
        // Get Inventory as designated file type
        String inventoryAsFormat = InventoryConverter.getInventoryAsFileType(inventory, format);
        // Save it

        try (FileWriter writer = new FileWriter(f)) {
            if (!f.exists())
                f.createNewFile();

            writer.write(inventoryAsFormat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Inventory loadInventory(File f) {
        return null;
    }
}
