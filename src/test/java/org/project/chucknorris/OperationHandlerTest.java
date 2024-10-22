package org.project.chucknorris;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import java.io.PrintStream;
import java.util.List;
import static org.mockito.Mockito.*;

class OperationHandlerTest {

    private final OperationHandler handlerToTest;
    private final ScannerWrapper scanner;
    private final PrintStream out;

    public OperationHandlerTest() {
        scanner = Mockito.mock(ScannerWrapper.class);
        handlerToTest = new OperationHandler(scanner);
        out = Mockito.mock(PrintStream.class);
    }

    @Test
    void testStartEncode() {
        when(scanner.getUserInput()).thenReturn("encode").thenReturn("H").thenReturn("exit");
        System.setOut(out);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        try (MockedStatic<Encoder> mocked = mockStatic(Encoder.class)) {
            when(Encoder.encode("H")).thenReturn("0 0 00 00 0 0 00 000");
            handlerToTest.start();
            mocked.verify(() -> Encoder.encode("H"));
        }
        verify(out, times(5)).println(captor.capture());
        List<String> captured = captor.getAllValues();
        Assertions.assertEquals("Encoded string:\n0 0 00 00 0 0 00 000\n", captured.get(2));
        Mockito.verify(scanner, Mockito.times(3)).getUserInput();
    }

    @Test
    void testStartDecode() {
        when(scanner.getUserInput()).thenReturn("decode").thenReturn("0 0 00 00 0 0 00 000").thenReturn("exit");
        System.setOut(out);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        try (MockedStatic<Decoder> mocked = mockStatic(Decoder.class)) {
            when(Decoder.decode("0 0 00 00 0 0 00 000")).thenReturn("H");
            handlerToTest.start();
            mocked.verify(() -> Decoder.decode("0 0 00 00 0 0 00 000"));
        }
        verify(out, times(5)).println(captor.capture());
        List<String> captured = captor.getAllValues();
        Assertions.assertEquals("Decoded string:\nH\n", captured.get(2));
        Mockito.verify(scanner, Mockito.times(3)).getUserInput();
    }

    @Test
    void testStartDecodeFailed() {
        when(scanner.getUserInput()).thenReturn("decode").thenReturn("0 0 00 00 0 0 0 00 0").thenReturn("exit");
        System.setOut(out);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        try (MockedStatic<Decoder> mocked = mockStatic(Decoder.class)) {
            when(Decoder.decode("0 0 00 00 0 0 0 00 0")).thenReturn("");
            handlerToTest.start();
            mocked.verify(() -> Decoder.decode("0 0 00 00 0 0 0 00 0"));
        }
        verify(out, times(5)).println(captor.capture());
        List<String> captured = captor.getAllValues();
        Assertions.assertEquals("Encoded string is not valid\n", captured.get(2));
        Mockito.verify(scanner, Mockito.times(3)).getUserInput();
    }

    @Test
    void testStartEmpty() {
        when(scanner.getUserInput()).thenReturn("").thenReturn("exit");
        System.setOut(out);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        handlerToTest.start();
        Mockito.verify(scanner, Mockito.times(2)).getUserInput();
        verify(out, times(4)).println(captor.capture());
        List<String> captured = captor.getAllValues();
        Assertions.assertEquals("There is no '' operation\n", captured.get(1));
    }

    @Test
    void testStartExit() {
        when(scanner.getUserInput()).thenReturn("exit");
        System.setOut(out);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        handlerToTest.start();
        Mockito.verify(scanner, Mockito.times(1)).getUserInput();
        verify(out, times(2)).println(captor.capture());
        List<String> captured = captor.getAllValues();
        Assertions.assertEquals("Bye!", captured.get(1));
    }
}
