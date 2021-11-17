/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Tanner Sandlin
 */

package inventory.primitives;

import inventory.Inventory;
import inventory.Item;

public class PrimitiveInventory {
    PrimitiveItem[] items;

    public PrimitiveInventory(Inventory inventory) {
        items = new PrimitiveItem[inventory.getItems().size()];
        for(int i = 0; i < items.length; i++)
            items[i] = itemToPrimitiveItem(inventory.getItems().get(i));

    }

    private PrimitiveItem itemToPrimitiveItem(Item item) {
        // Takes a normal item and creates a primitive item with the same attributes
        return new PrimitiveItem(item.getSerialNumber(), item.getName(), item.getValue());
    }

    public PrimitiveItem[] getItems() {
        return items;
    }
}