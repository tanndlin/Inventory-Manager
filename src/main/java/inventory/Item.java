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
import java.util.stream.Stream;

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
        if (format.equals(Types.FileFormat.TSV))
            return getItemAsTSV();

        if (format.equals(Types.FileFormat.HTML))
            return getItemAsHTML();

        if (format.equals(Types.FileFormat.JSON))
            return getItemAsJSON();
        return null;
    }

    private String getItemAsTSV() {
        return String.format("%s\t%s\t%s", serialNumber.get(), name.get(), value.get());
    }

    private String getItemAsHTML() {
        String[] itemArray = new String[]{serialNumber.get(), name.get(), value.get() + ""};
        String[] asHTML = Stream.of(itemArray).map((String s) -> String.format("<td>%s</td>\n", s)).toArray(String[]::new);

        StringBuilder builder = new StringBuilder();
        for (String s : asHTML)
            builder.append(s);

        return String.format("<tr>%s</tr>", builder.toString());
    }

    private String getItemAsJSON() {
        // This is not necessary as GSON takes care of it from the inventory level
        return null;
    }

    public void setSerial(String newSerial) {
        if (ItemValidator.isValidSerial(newSerial))
            serialNumber.set(newSerial);
    }

    public void setName(String newName) {
        if (ItemValidator.isValidName(newName))
            name.set(newName);
    }

    public void setValue(String newValue) {
        if (ItemValidator.isValidValue(newValue))
            value.set(Double.parseDouble(newValue));
    }

    public boolean matches(String filter) {
        boolean matchedSerial = getSerialNumber().contains(filter);
        boolean matchedName = getName().contains(filter);
        boolean matchedValue = (getValue() + "").contains(filter);

        return matchedSerial || matchedName || matchedValue;
    }
}