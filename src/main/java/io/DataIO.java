/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Tanner Sandlin
 */

package io;

import inventory.Inventory;

import java.io.File;

public class DataIO {

    private Inventory inventory;

    public DataIO(Inventory inventory) {
        this.inventory = inventory;
    }

    public void saveInventory(){
        // Get file location from user
        // Get file type from user

        // Get Inventory as designated file type
        // Save it
    }

    public Inventory loadInventory(){
        // Get file location from user
        return null;
    }

    private File getFileFromUser(boolean saving){
        // Use file chooser to get location
        // Create FileChooser

        // If saving, Display for save

        // else display for load

        // Different kind of chooser for saving
        return null;
    }
}
