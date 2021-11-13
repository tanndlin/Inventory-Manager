/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Tanner Sandlin
 */

package inventory;

import base.Types;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    ObservableList items;

    public Inventory() {
        items = FXCollections.observableArrayList();
    }

    public Inventory(ObservableList items) {
        this.items = items;
    }

    public ObservableList getItems() {
        return items;
    }

    public String getAsFileType(Types.FileFormat format){
        // Check format enum type and delegate to function below
        return null;
    }

    private String getAsTSV(){
        // Use StringBuilder to concat all items separated by \n
        return null;
    }

    private String getAsHTML(){
        // Create top of html

        // Populate table with each element

        //Finish HTML
        return null;
    }

    private String getAsJSON(){
        // Use GSON
        return null;
    }
}
