package org.project.chucknorris;

import java.util.ArrayList;
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
        String op = "";
        String answer = "";
        while (!op.equals("exit")) {
            System.out.println("Please input operation (encode/decode/exit):");
            answer = sc.nextLine();
            op = answer.toLowerCase();

            switch (op) {
                case "encode":
                    System.out.println("Input string:");
                    System.out.println("Encoded string:\n"+Encoder.encode(sc.nextLine())+"\n");
                    break;
                case "decode":
                    System.out.println("Input encoded string:");
                    String resultDecode = Decoder.decode(sc.nextLine());
                    System.out.println(resultDecode.isBlank() ? "Encoded string is not valid\n" : "Decoded string:\n" + resultDecode + "\n");
                    break;
                case "exit":
                    System.out.print("Bye!");
                    break;
                default:
                    System.out.println("There is no '" + answer + "' operation\n");
                    break;
            }
        }
    }

}

