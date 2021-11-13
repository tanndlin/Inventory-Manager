package util;

public class ItemValidator {

    public static boolean isValidSerial(String serial) {
        if (serial.length() != "A-XXX-XXX-XXX".length())
            return false;

        // Make sure first char is a letter
        if (!Character.isAlphabetic(serial.toCharArray()[0]))
            return false;

        String[] sections = serial.split("-");
        if (sections.length != 4)
            return false;


        // Make sure each section is strictly alphanumeric
        for (String section : sections)
            for (char c : section.toCharArray())
                if (!(Character.isAlphabetic(c) || Character.isDigit(c)))
                    return false;

        return true;
    }

    public static boolean isValidName(String name) {
        return name.length() >= 2 && name.length() <= 256;
    }

    public static boolean isValidValue(double value) {
        return value >= 0;
    }

}
