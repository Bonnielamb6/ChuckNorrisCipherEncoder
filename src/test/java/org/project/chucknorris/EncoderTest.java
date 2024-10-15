package org.project.chucknorris;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncoderTest {

    @Test
    void testEncodeSingleCharacter() {
        String input = "H";
        String expected = "0 0 00 00 0 0 00 000";
        String actual = Encoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeMultipleCharacters() {
        String input = "Hey";
        String expected = "0 0 00 00 0 0 00 000 0 00 00 00 0 0 00 0 0 00000 00 00 0 0";
        String actual = Encoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeEmptyString() {
        String input = "";
        String expected = "";
        String actual = Encoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeRepeatedCharacters() {
        String input = "HH";
        String expected = "0 0 00 00 0 0 00 000 0 0 00 00 0 0 00 000";
        String actual = Encoder.encode(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeBlankSpace() {
        String input = " ";
        String expected = "00 0 0 0 00 00000";
        String actual = Encoder.encode(input);
        assertEquals(expected, actual);
    }
}