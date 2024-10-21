package org.project.chucknorris;

public class Main {
    public static void main(String[] args) {
        ScannerWrapper sc = new ScannerWrapper();
        OperationHandler operationHandler = new OperationHandler(sc);
        operationHandler.start();
    }
}

