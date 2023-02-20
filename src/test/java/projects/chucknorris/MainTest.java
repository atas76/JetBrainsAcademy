package projects.chucknorris;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    public void testEncodeCharacter() {
        assertEquals("0 0 00 0000 0 00", Main.getChuckNorrisUnaryCode("C"));
    }

    @Test
    public void testEncodeTwoCharacters() {
        assertEquals("0 0 00 0000 0 000 00 0000 0 00", Main.getChuckNorrisUnaryCode("CC"));
    }

    @Test
    public void testEncodeString() {
        assertEquals("0 0 00 00 0 0 00 000 0 00 00 0 0 0 00 00 0 0 00 0 0 0 00 000000 0 0000 00 000 0 00 00 00 0 00",
                Main.getChuckNorrisUnaryCode("Hi <3"));
    }

    @Test
    public void testEncodeExclamationMark() {
        assertEquals("00 0 0 0 00 0000", Main.getChuckNorrisUnaryCode("!"));
    }

    @Test
    public void testEncodeSingleOne() {
        String encodedString = Main.encode('1', 1);

        assertEquals("0 0", encodedString);
    }

    @Test
    public void testEncodeFourZeros() {
        String encodedString = Main.encode('0', 4);

        assertEquals("00 0000", encodedString);
    }

    @Test
    public void testEncodeTwoOnes() {
        String encodedString = Main.encode('1', 2);

        assertEquals("0 00", encodedString);
    }
}
