package inventory;

import base.Types;
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
    void getItemAsTSV() {
        String expected = "A-XXX-XXX-XXX\thello\t0.0";

        Item item = new Item("A-XXX-XXX-XXX", "hello", "0");
        assertEquals(expected, item.getItemAsFormat(Types.FileFormat.TSV));
    }

    @Test
    void getItemAsHTML() {
        String expected = """
                <tr>
                <td>A-XXX-XXX-XXX</td>
                <td>hello</td>
                <td>0.0</td>
                </tr>""";

        Item item = new Item("A-XXX-XXX-XXX", "hello", "0");
        assertEquals(expected, item.getItemAsFormat(Types.FileFormat.HTML));
    }

    @Test
    void getItemAsJSON() {
        // This is not necessary as storing in JSON is done by the containing class
    }

    @Test
    void searchTest(){
        // Req 11 && 12

        Item item = new Item("A-XXX-XXX-XXX", "Hello World", "420.69");

        assertTrue(item.matches("Hell"));
        assertTrue(item.matches("X"));
        assertFalse(item.matches("A-XXX-XXX-420"));
    }
}