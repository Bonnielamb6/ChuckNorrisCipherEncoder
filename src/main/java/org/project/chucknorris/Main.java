import java.util.ArrayList;
import java.util.Scanner;
/*
* Single responsibility
* better names
* Constants
* NOT void functions unless intended to modify the value
* */


public class Main {

    private static final short BIT_SIZE = 7;
    private static final short BINARY_BLOCK_SEPARATION = 2;
    private static final short MAXIMUM_FIRST_BLOCK_SIZE = 2;
    private static final short BINARY_BASE_CONVERSION = 2;

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
                    encode();
                    break;
                case "decode":
                    decode();
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

        for (int currentBitPos = 1; currentBitPos < resultBinary.length(); currentBitPos++) {
            if (lastChar == resultBinary.charAt(currentBitPos)) {
                currentCounter++;
            } else {
                if (lastChar == '0') {//101010101111010101101
                                        //0 0 00 0 0 0
                    result.append("00 ");
                } else {
                    result.append("0 ");
                }

                for (int charReplication = 0; charReplication < currentCounter; charReplication++) {
                    result.append("0");
                }
                result.append(" ");
                lastChar = resultBinary.charAt(currentBitPos);
                currentCounter = 1;
            }
        }


        if (lastChar == '0') {
            result.append("00 ");
        } else {
            result.append("0 ");
        }

        for (int charReplication = 0; charReplication < currentCounter; charReplication++) {
            result.append("0");
        }
        result.append(" ");
        System.out.println("Encoded string:");
        System.out.println(result + "\n");

    }

    public static void decode() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input encoded string:");
        String encodedString = sc.nextLine();

        String[] splitString = encodedString.split(" ");
        StringBuilder decodedString = new StringBuilder();
        ArrayList<String> individualBinaries = new ArrayList<>();

        if(verifyEncoded(encodedString,splitString)){
            System.out.println("Encoded string is not valid\n");
            return;
        }

        for (int currentBitPos = 0; currentBitPos < splitString.length; currentBitPos = currentBitPos + BINARY_BLOCK_SEPARATION) {
            char currentChar;
            if (splitString[currentBitPos].equals("0")) {
                currentChar = '1';
            } else {
                currentChar = '0';
            }

            for (int charReplication = 0; charReplication < splitString[currentBitPos + 1].length(); charReplication++) {
                decodedString.append(currentChar);
            }

        }
        if(validateSize(decodedString)){
            System.out.println("Encoded string is not valid\n");
            return;
        }

        for (int currentBitPos = 0; currentBitPos < decodedString.length(); currentBitPos = currentBitPos + BIT_SIZE) {
            individualBinaries.add(decodedString.substring(currentBitPos, currentBitPos + BIT_SIZE));
        }
        System.out.println("Decoded string:");
        for (String binaryString : individualBinaries) {
            char number = (char) Integer.parseInt(binaryString, BINARY_BASE_CONVERSION);
            System.out.print(number);
        }
        System.out.println("\n");
    }

    private static boolean validateSize(StringBuilder decodedString) {
        return decodedString.length() % BIT_SIZE != 0;
    }

    private static boolean verifyEncoded(String verify,String[] blocks) {
        if((!blocks[0].equals("0")) && (!blocks[0].equals("00"))){
            return true;
        }
        if(!isPairSize(blocks.length)){
            return true;
        }
        for (int currentBitPos = 0; currentBitPos < verify.length(); currentBitPos++) {
            if (verify.charAt(currentBitPos) != '0' && verify.charAt(currentBitPos) != ' ') {
                return true;
            }
        }
        for(int currentBinaryBlock = 0;currentBinaryBlock< blocks.length;currentBinaryBlock = currentBinaryBlock+BINARY_BLOCK_SEPARATION){
            if(blocks[currentBinaryBlock].length()>MAXIMUM_FIRST_BLOCK_SIZE || blocks[currentBinaryBlock].isEmpty()){
                return true;
            }
        }
        return false;
    }

    private static boolean isPairSize(int size){
        return size % 2 == 0;
    }
}

