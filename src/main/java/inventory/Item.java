/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Tanner Sandlin
 */

package inventory;

import base.Types;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import util.ItemValidator;

import java.util.stream.Stream;

public class Item {

    private final SimpleStringProperty serialNumber;
    private final SimpleStringProperty name;
    private final SimpleDoubleProperty value;

    public Item(String serialNumber, String name, String value) {
        // Make sure each param is valid then add it

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

    public String getName() {
        return name.get();
    }

    public double getValue() {
        return value.get();
    }

    public String getItemAsFormat(Types.FileFormat format) {
        // Delegate to the appropriate method depending on enum value
        if (format.equals(Types.FileFormat.TSV))
            return getItemAsTSV();

        if (format.equals(Types.FileFormat.HTML))
            return getItemAsHTML();

        if (format.equals(Types.FileFormat.JSON))
            return getItemAsJSON();
        return null;
    }

    private String getItemAsTSV() {
        // Attributes sep by tabs
        return String.format("%s\t%s\t%s", serialNumber.get(), name.get(), value.get());
    }

    private String getItemAsHTML() {
        // Create an HTML table row

        String[] itemArray = new String[]{serialNumber.get(), name.get(), value.get() + ""};

        // Wrap each element in HTML table cell
        String[] asHTML = Stream.of(itemArray).map((String s) -> String.format("<td>%s</td>\n", s)).toArray(String[]::new);

        // Add each cell to the row
        StringBuilder builder = new StringBuilder();
        for (String s : asHTML)
            builder.append(s);

        return String.format("<tr>\n%s</tr>", builder.toString());
    }

    private String getItemAsJSON() {
        // This is not necessary as GSON takes care of it from the inventory level
        return null;
    }

    public boolean matches(String filter) {
        String filterLower = filter.toLowerCase();

        // Check to see if any of the attributes contain the query
        boolean matchedSerial = getSerialNumber().toLowerCase().contains(filterLower);
        boolean matchedName = getName().toLowerCase().contains(filterLower);
        boolean matchedValue = (getValue() + "").toLowerCase().contains(filterLower);

        return matchedSerial || matchedName || matchedValue;
    }
}