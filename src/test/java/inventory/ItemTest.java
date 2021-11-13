package inventory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void invalidSerial() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Item("Hi", "hello", "0");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Item("A-XXX-XXX-XX@", "hello", "0");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Item("9-XXX-XXX-XXX", "hello", "0");
        });
    }

    @Test
    void invalidName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Item("A-XXX-XXX-XXX", "", "0");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Item("A-XXX-XXX-XXX", "x".repeat(300), "0");
        });
    }

    @Test
    void invalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Item("A-XXX-XXX-XXX", "hello", "-1");
        });
    }

    @Test
    void getItemAsFormat() {

    }
}