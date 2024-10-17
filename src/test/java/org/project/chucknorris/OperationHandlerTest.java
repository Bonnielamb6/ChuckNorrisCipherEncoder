package org.project.chucknorris;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperationHandlerTest {

    private OperationHandler handler;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    void setUp(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        handler = new OperationHandler(scanner);
    }

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testStartEncode() {
        setUp("encode\nHey!\nexit\n");
        String expected = """
                Please input operation (encode/decode/exit):
                Input string:
                Encoded string:
                0 0 00 00 0 0 00 000 0 00 00 00 0 0 00 0 0 00000 00 00 0 0 00 0 0 0 00 0000 0 0
                
                Please input operation (encode/decode/exit):
                Bye!""";

        handler.start();

        assertEquals(expected.trim(), outputStream.toString().trim());
    }

    @Test
    void testStartDecode() {
        setUp("decode\n0 0 00 00 0 0 00 000 0 00 00 00 0 0 00 0 0 00000 00 00 0 0 00 0 0 0 00 0000 0 0\nexit\n");
        String expected = """
                Please input operation (encode/decode/exit):
                Input encoded string:
                Decoded string:
                Hey!
                
                Please input operation (encode/decode/exit):
                Bye!""";
        handler.start();

        assertEquals(expected.trim(), outputStream.toString().trim());
    }

    @Test
    void testStartEmpty() {
        setUp("\nexit\n");
        String expected = """
                Please input operation (encode/decode/exit):
                There is no '' operation
                
                Please input operation (encode/decode/exit):
                Bye!
                """;
        handler.start();

        assertEquals(expected.trim(), outputStream.toString().trim());

    }


    @Test
    void testStartExit() {
        setUp("exit\n");
        String expected = """
                Please input operation (encode/decode/exit):
                Bye!
                """;
        handler.start();

        assertEquals(expected.trim(), outputStream.toString().trim());
    }
}
