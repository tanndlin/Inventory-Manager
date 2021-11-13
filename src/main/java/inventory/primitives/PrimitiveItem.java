/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Tanner Sandlin
 */

package inventory.primitives;

public class PrimitiveItem {
    String serialNumber;
    String name;
    double value;

    public PrimitiveItem(String serialNumber, String name, double value) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.value = value;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }
}