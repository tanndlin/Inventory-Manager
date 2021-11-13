/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Tanner Sandlin
 */

package inventory;

import base.Types;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.File;

public class Item {

    private SimpleStringProperty serialNumber;
    private SimpleStringProperty name;
    private SimpleDoubleProperty value;

    public Item(String serialNumber, String name, double value) {
        // TODO: 11/12/2021 Input Validation
        this.serialNumber = new SimpleStringProperty(serialNumber);
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleDoubleProperty(value);
    }

    public String getSerialNumber() {
        return serialNumber.get();
    }

    public SimpleStringProperty serialNumberProperty() {
        return serialNumber;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public double getValue() {
        return value.get();
    }

    public SimpleDoubleProperty valueProperty() {
        return value;
    }

    public String getItemAsFormat(Types.FileFormat format){
        // Dispatch calls to getItemAs<FileFormatEnum>
        return null;
    }

    private String getItemAsTSV(){
        // Return item as tab sep values
        return null;
    }

    private String getItemAsHTML(){
        // Return item as a table cell in html
        return null;
    }

    private String getItemAsJSON(){
        // This is not necessary as GSON takes care of it from the inventory level
        return null;
    }
}