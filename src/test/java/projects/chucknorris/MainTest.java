package projects.chucknorris;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    public void testEncodeCharacter() {
        assertEquals("0 0 00 0000 0 00", Main.encode("C"));
    }

    @Test
    public void testDecodeCharacter() {
        assertEquals("C", Main.decode("0 0 00 0000 0 00"));
    }

    @Test
    public void testEncodeTwoCharacters() {
        assertEquals("0 0 00 0000 0 000 00 0000 0 00", Main.encode("CC"));
    }

    @Test
    public void testDecodeTwoCharacters() {
        assertEquals("CC", Main.decode("0 0 00 0000 0 000 00 0000 0 00"));
    }

    @Test
    public void testEncodeString() {
        assertEquals("0 0 00 00 0 0 00 000 0 00 00 0 0 0 00 00 0 0 00 0 0 0 00 000000 0 0000 00 000 0 00 00 00 0 00",
                Main.encode("Hi <3"));
    }

    @Test
    public void testDecodeString() {
        assertEquals("Hi <3",
                Main.decode("0 0 00 00 0 0 00 000 0 00 00 0 0 0 00 00 0 0 00 0 0 0 00 000000 0 0000 00 000 0 00 00 00 0 00"));
    }

    @Test
    public void testEncodeExclamationMark() {
        assertEquals("00 0 0 0 00 0000 0 0", Main.encode("!"));
    }

    @Test
    public void testEncodeSingleOne() {
        String encodedString = Main.encodeSequence('1', 1);

        assertEquals("0 0", encodedString);
    }

    @Test
    public void testEncodeFourZeros() {
        String encodedString = Main.encodeSequence('0', 4);

        assertEquals("00 0000", encodedString);
    }

    @Test
    public void testEncodeTwoOnes() {
        String encodedString = Main.encodeSequence('1', 2);

        assertEquals("0 00", encodedString);
    }
}
