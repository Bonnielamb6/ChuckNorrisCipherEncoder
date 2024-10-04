import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String op = "";
        while (!op.equals("exit")) {
            System.out.println("Please input operation (encode/decode/exit):");
            op = sc.nextLine().toLowerCase();

            switch (op) {
                case "encode":
                    encode();
                    break;
                case "decode":
                    decode();
                    break;
                case "exit":
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("There is no '" + op + "' operation");
                    break;

            }

        }


    }

    public static void encode() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input string:");
        String message = sc.nextLine();


        StringBuilder resultBinary = new StringBuilder();

        for (Character x : message.toCharArray()) {
            String number = Integer.toBinaryString(x);
            number = "0000000".substring(number.length()) + number;
            resultBinary.append(number);
        }

        char lastChar = resultBinary.charAt(0);
        int currentCounter = 1;
        StringBuilder result = new StringBuilder();

        for (int i = 1; i < resultBinary.length(); i++) {
            if (lastChar == resultBinary.charAt(i)) {
                currentCounter++;
            } else {
                if (lastChar == '0') {
                    result.append("00 ");
                } else {
                    result.append("0 ");
                }

                for (int j = 0; j < currentCounter; j++) {
                    result.append("0");
                }
                result.append(" ");
                lastChar = resultBinary.charAt(i);
                currentCounter = 1;
            }
        }


        if (lastChar == '0') {
            result.append("00 ");
        } else {
            result.append("0 ");
        }

        for (int k = 0; k < currentCounter; k++) {
            result.append("0");
        }
        result.append(" ");
        System.out.println("Encoded string:");
        System.out.println(result);

    }

    public static void decode() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input encoded string:");
        String encodedString = sc.nextLine();

        String[] splitString = encodedString.split(" ");
        StringBuilder decodedString = new StringBuilder();
        ArrayList<String> individualBinaries = new ArrayList<>();

        if(verifyEncoded(encodedString,splitString)){
            System.out.println("Encoded string is not valid");
            return;
        }

        for (int i = 0; i < splitString.length; i = i + 2) {
            char currentChar;
            if (splitString[i].equals("0")) {
                currentChar = '1';
            } else {
                currentChar = '0';
            }

            for (int j = 0; j < splitString[i + 1].length(); j++) {
                decodedString.append(currentChar);
            }

        }
        if(validateSize(decodedString)){
            System.out.println("Encoded string is not valid");
            return;
        }

        for (int i = 0; i < decodedString.length(); i = i + 7) {
            individualBinaries.add(decodedString.substring(i, i + 7));
        }
        System.out.println("Decoded string:");
        for (String x : individualBinaries) {
            char number = (char) Integer.parseInt(x, 2);
            System.out.print(number);
        }
        System.out.println();
    }

    private static boolean validateSize(StringBuilder decodedString) {
        return decodedString.length() % 7 != 0;
    }

    public static boolean verifyEncoded(String verify,String[] blocks) {
        if((!blocks[0].equals("0")) && (!blocks[0].equals("00"))){
            return true;
        }
        if(blocks.length % 2 !=0){
            return true;
        }
        for (int i = 0; i < verify.length(); i++) {
            if (verify.charAt(i) != '0' && verify.charAt(i) != ' ') {
                return true;
            }
        }
        return false;
    }
}

