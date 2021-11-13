/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Tanner Sandlin
 */

package base;

public class Types {
    public enum FileFormat {
        TSV,
        HTML,
        JSON,
    }

    public static FileFormat stringToFileFormat(String s) {
        if (s.equalsIgnoreCase("TSV"))
            return FileFormat.TSV;

        if (s.equalsIgnoreCase("HTML"))
            return FileFormat.HTML;

        if (s.equalsIgnoreCase("JSON"))
            return FileFormat.JSON;

        return null;
    }

}
