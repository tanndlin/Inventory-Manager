/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Tanner Sandlin
 */

package base;

import inventory.Inventory;
import inventory.Item;
import io.DataIO;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

public class InventoryManager {

    private Inventory inventory;
    private Scene scene;

    public InventoryManager(Inventory inventory, Scene scene) {
        this.inventory = inventory;
        this.scene = scene;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    // Alias for DataIO method
    public void save() {
        File f = getFileFromUser(scene.getWindow(), false);
        if (f == null)
            return;

        DataIO dataIO = new DataIO(inventory);
        dataIO.saveInventory(f);
    }

    // Alias for DataIO method
    public void load() {
        File f = getFileFromUser(scene.getWindow(), true);
        if (f == null)
            return;

        DataIO dataIO = new DataIO(inventory);
        inventory.stealItems(dataIO.loadInventory(f));
    }

    public void addItem(Item item) {
        if (isDuplicate(item))
            throw new IllegalArgumentException("Duplicate Serial Numbers: " + item.getSerialNumber());

        inventory.addItem(item);
    }

    public void clearItems() {
        inventory.getItems().clear();
    }

    private boolean isDuplicate(Item item) {
        for (Item i : inventory.getItems())
            if (i.getSerialNumber().equals(item.getSerialNumber()))
                return true;

        return false;
    }

    private File getFileFromUser(Window window, boolean load) {
        // Use file browser to get file location
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TSV", "*.tsv"),
                new FileChooser.ExtensionFilter("HTML", "*.html"),
                new FileChooser.ExtensionFilter("JSON", "*.json")
        );
        fileChooser.setInitialFileName("Inventory");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/desktop/"));

        if (load)
            return fileChooser.showOpenDialog(window);
        return fileChooser.showSaveDialog(window);
    }
}
