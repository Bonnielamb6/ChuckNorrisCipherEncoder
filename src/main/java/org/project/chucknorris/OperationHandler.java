package org.project.chucknorris;

import java.util.Scanner;

public class OperationHandler {
    private final Scanner scanner;

    public OperationHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start(){
        String operation = "";
        while (!operation.equals("exit")) {
            System.out.println("Please input operation (encode/decode/exit):");
            operation = scanner.nextLine().toLowerCase();

            switch (operation) {
                case "encode":
                    handleEncode();
                    break;
                case "decode":
                    handleDecode();
                    break;
                case "exit":
                    System.out.print("Bye!");
                    break;
                default:
                    System.out.println("There is no '" + operation + "' operation\n");
                    break;
            }
        }
    }

    private void handleEncode() {
        System.out.println("Input string:");
        String input = scanner.nextLine();
        String encoded = Encoder.encode(input);
        System.out.println("Encoded string:\n" + encoded + "\n");
    }

    private void handleDecode() {
        System.out.println("Input encoded string:");
        String input = scanner.nextLine();
        String decoded = Decoder.decode(input);
        System.out.println(decoded.isBlank() ? "Encoded string is not valid\n" : "Decoded string:\n" + decoded + "\n");
    }

}
