package org.project.chucknorris;

import java.util.Scanner;

public class ScannerWrapper {
    private final Scanner scanner;
    public ScannerWrapper() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }
}
