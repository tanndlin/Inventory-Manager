/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Tanner Sandlin
 */

package inventory;

import base.Types;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import util.ItemValidator;

import java.io.File;

public class Item {

    private SimpleStringProperty serialNumber;
    private SimpleStringProperty name;
    private SimpleDoubleProperty value;

    public Item(String serialNumber, String name, String value) {
        // Duplicates are checked at a higher level
        if (!ItemValidator.isValidSerial(serialNumber))
            throw new IllegalArgumentException("Serial not in correct form. Must be in the form A-XXX-XXX-XXX");
        this.serialNumber = new SimpleStringProperty(serialNumber);

        if (!ItemValidator.isValidName(name))
            throw new IllegalArgumentException("Name not correct length. Must be between 2 and 256");
        this.name = new SimpleStringProperty(name);

        if (!ItemValidator.isValidValue(value))
            throw new IllegalArgumentException("Value must a number and >= 0");
        this.value = new SimpleDoubleProperty(Double.parseDouble(value));
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

    public String getItemAsFormat(Types.FileFormat format) {
        // Dispatch calls to getItemAs<FileFormatEnum>
        return null;
    }

    private String getItemAsTSV() {
        // Return item as tab sep values
        return null;
    }

    private String getItemAsHTML() {
        // Return item as a table cell in html
        return null;
    }

    private String getItemAsJSON() {
        // This is not necessary as GSON takes care of it from the inventory level
        return null;
    }
}