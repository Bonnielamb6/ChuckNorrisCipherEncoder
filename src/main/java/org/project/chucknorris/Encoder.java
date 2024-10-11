package org.project.chucknorris;

public final class Encoder {


    public static String encode(String stringToEncode) {
        StringBuilder resultString = new StringBuilder();
        String binaryString = stringToBinary(stringToEncode);
        encodeBinaryString(binaryString, resultString);

        return resultString.toString();
    }

    private static void encodeBinaryString(String binaryString, StringBuilder resultString) {
        char lastChar = binaryString.charAt(0);
        int currentCounter = 1;

        for (int currentBitPos = 1; currentBitPos < binaryString.length(); currentBitPos++) {
            if (lastChar == binaryString.charAt(currentBitPos)) {
                currentCounter++;
            } else {
                appendEncodedSegment(resultString, lastChar, currentCounter);
                lastChar = binaryString.charAt(currentBitPos);
                currentCounter = 1;
            }
        }

        appendEncodedSegment(resultString, lastChar, currentCounter);
    }

    private static void appendEncodedSegment(StringBuilder resultString, char character, int count) {
        if (character == '0') {
            resultString.append("00 ");
        } else {
            resultString.append("0 ");
        }

        for (int i = 0; i < count; i++) {
            resultString.append("0");
        }
        resultString.append(" ");
    }

    private static String stringToBinary(String stringToChange) {
        StringBuilder resultString = new StringBuilder();

        for (Character charToEncode : stringToChange.toCharArray()) {
            String number = Integer.toBinaryString(charToEncode);
            number = "0000000".substring(number.length()) + number;
            resultString.append(number);
        }

        return resultString.toString();
    }
}
