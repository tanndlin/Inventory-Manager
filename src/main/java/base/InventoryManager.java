/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Tanner Sandlin
 */

package base;

import inventory.Inventory;

public class InventoryManager {

    private Inventory inventory;

    public InventoryManager(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    // Alias for DataIO method
    public void save(){
        // Instantiate a DataIO
        // Call its method
    }

    // Alias for DataIO method
    public void load(){
        // Instantiate a DataIO
        // Call its method
    }
}
