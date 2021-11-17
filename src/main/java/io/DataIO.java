/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Tanner Sandlin
 */

package io;

import base.Types;
import com.google.gson.Gson;
import inventory.Inventory;
import inventory.Item;
import inventory.primitives.PrimitiveInventory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.InventoryConverter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataIO {

    private final Inventory inventory;

    public DataIO(Inventory inventory) {
        this.inventory = inventory;
    }

    public void saveInventory(File f) {
        Types.FileFormat format = formatFromFile(f);

        // Get Inventory as designated file type
        String inventoryAsFormat = InventoryConverter.getInventoryAsFileType(inventory, format);
        // Save it

        try (FileWriter writer = new FileWriter(f)) {
            if (!f.exists())
                f.createNewFile(); // Result is irrelevant

            writer.write(inventoryAsFormat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Inventory loadInventory(File f) {
        Types.FileFormat format = formatFromFile(f);
        StringBuilder builder = new StringBuilder();

        try (Scanner scan = new Scanner(f)) {
            while (scan.hasNextLine())
                builder.append(scan.nextLine()).append("\n"); // add back the newline the scanner ate
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return parseFromFileType(format, builder.toString());
    }

    private Inventory parseFromFileType(Types.FileFormat format, String data) {
        if (format.equals(Types.FileFormat.TSV))
            return parseFromTSV(data);

        if (format.equals(Types.FileFormat.HTML))
            return parseFromHTML(data);

        if (format.equals(Types.FileFormat.JSON))
            return parseFromJSON(data);

        return null;
    }

    private Inventory parseFromTSV(String data) {
        Inventory parsed = new Inventory();

        // Each item is on its own line
        String[] itemsAsStrings = data.split("\n");

        // Separate by tabs to get serial name and value
        for (String itemAsString : itemsAsStrings) {
            String[] split = itemAsString.split("\t");
            parsed.addItem(new Item(split[0], split[1], split[2]));
        }

        return parsed;
    }

    private Inventory parseFromHTML(String data) {
        Inventory parsed = new Inventory();

        Document doc = Jsoup.parse(data);
        Elements tds = doc.getElementsByTag("td");

        Element[] cells = tds.toArray(Element[]::new);

        for (int i = 0; i < cells.length; i += 3) {
            parsed.addItem(new Item(cells[i].text(), cells[i + 1].text(), cells[i + 2].text()));
        }

        return parsed;
    }

    private Inventory parseFromJSON(String data) {
        // Get primitive inventory from gson
        PrimitiveInventory privInv = new Gson().fromJson(data, PrimitiveInventory.class);

        // Convert it
        return InventoryConverter.primitiveInventoryToSuper(privInv);
    }

    private Types.FileFormat formatFromFile(File f) {
        String fileName = f.toString();
        String extensionAsString = fileName.substring(fileName.lastIndexOf(".") + 1);
        return Types.stringToFileFormat(extensionAsString);
    }
}
