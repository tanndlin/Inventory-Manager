/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Tanner Sandlin
 */

package util;

import base.Types;
import com.google.gson.Gson;
import inventory.Inventory;
import inventory.Item;
import inventory.primitives.PrimitiveInventory;
import inventory.primitives.PrimitiveItem;

public class InventoryConverter {

    private static String defaultHTML = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Document</title>
            </head>
            <body>
                <table>
                    <tr>
                        <th>Serial Number</th>
                        <th>Name</th>
                        <th>Value</th>
                    </tr>
                        DATA_GOES_HERE
                </table>
            </body>
            </html>""";

    public static String getInventoryAsFileType(Inventory inventory, Types.FileFormat format) {
        if (format.equals(Types.FileFormat.TSV))
            return getAsTSV(inventory);

        if (format.equals(Types.FileFormat.HTML))
            return getAsHTML(inventory);

        // Default return
        return getAsJSON(inventory);
    }

    private static String getAsTSV(Inventory inventory) {
        StringBuilder builder = new StringBuilder();

        // Append each item to the builder
        for (Item i : inventory.getItems())
            builder.append(i.getItemAsFormat(Types.FileFormat.TSV)).append('\n');

        return builder.toString();
    }

    private static String getAsHTML(Inventory inventory) {
        // Populate table with each element
        StringBuilder itemBuilder = new StringBuilder();
        for (Item i : inventory.getItems())
            itemBuilder.append(i.getItemAsFormat(Types.FileFormat.HTML));

        //Finish HTML
        return defaultHTML.replace("DATA_GOES_HERE", itemBuilder.toString());
    }

    private static String getAsJSON(Inventory inventory) {
        return new Gson().toJson(new PrimitiveInventory(inventory));
    }

    //Converts PrimitiveInventory to Inventory
    public static Inventory primitiveInventoryToSuper(PrimitiveInventory privInv) {
        Inventory inventory = new Inventory();

        // For each item, turn it into a regular Item
        for (PrimitiveItem privItem : privInv.getItems())
            inventory.addItem(new Item(
                    privItem.getSerialNumber(),
                    privItem.getName(),
                    privItem.getValue() + ""));

        return inventory;
    }
}
