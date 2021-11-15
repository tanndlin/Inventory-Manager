/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Tanner Sandlin
 */

package inventory;

import base.Types;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    ObservableList<Item> items;

    public Inventory() {
        items = FXCollections.observableArrayList();
    }

    public Inventory(ObservableList<Item> items) {
        this.items = items;
    }

    public ObservableList<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void stealItems(Inventory newInventory) {
        items.clear();
        for(Item item : newInventory.getItems())
            addItem(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
}
