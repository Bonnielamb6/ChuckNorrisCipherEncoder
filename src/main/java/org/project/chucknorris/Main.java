package org.project.chucknorris;
import java.util.Scanner;
/*
* Single responsibility
* better names
* Constants
* NOT void functions unless intended to modify the value
* */


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OperationHandler operationHandler = new OperationHandler(sc);
        operationHandler.start();
    }

}

