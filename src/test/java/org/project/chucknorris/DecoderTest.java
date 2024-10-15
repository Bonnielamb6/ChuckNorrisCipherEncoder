package org.project.chucknorris;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecoderTest {

    @Test
    void testDecodeSingleCharacter() {
        String input = "0 0 00 00 0 0 00 000";
        String expected = "H";
        String actual = Decoder.decode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testDecodeMultipleCharacters() {
        String input = "0 0 00 00 0 0 00 000 0 00 00 00 0 0 00 0 0 00000 00 00 0 0";
        String expected = "Hey";
        String actual = Decoder.decode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testDecodeEmptyString() {
        String input = "";
        String expected = "";
        String actual = Decoder.decode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testDecodeRepeatedCharacters() {
        String input = "0 0 00 00 0 0 00 000 0 0 00 00 0 0 00 000";
        String expected = "HH";
        String actual = Decoder.decode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testDecodeBlankSpace() {
        String input = "00 0 0 0 00 00000";
        String expected = " ";
        String actual = Decoder.decode(input);
        assertEquals(expected, actual);
    }
}