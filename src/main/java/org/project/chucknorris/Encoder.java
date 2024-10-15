package org.project.chucknorris;

public final class Encoder {


    public static String encode(String stringToEncode) {
        String binaryString = stringToBinary(stringToEncode);
        return encodeBinaryString(binaryString);
    }

    private static String encodeBinaryString(String binaryString) {
        StringBuilder encodedString = new StringBuilder();
        char lastChar = binaryString.charAt(0);
        int currentCounter = 1;

        for (int currentBitPos = 1; currentBitPos < binaryString.length(); currentBitPos++) {
            if (lastChar == binaryString.charAt(currentBitPos)) {
                currentCounter++;
            } else {
                encodedString.append(appendEncodedSegment(lastChar, currentCounter));
                lastChar = binaryString.charAt(currentBitPos);
                currentCounter = 1;
            }
        }

        encodedString.append(appendEncodedSegment( lastChar, currentCounter));
        return encodedString.toString();
    }

    private static String appendEncodedSegment( char character, int count) {
        StringBuilder stringToAppend = new StringBuilder();
        if (character == '0') {
            stringToAppend.append("00 ");
        } else {
            stringToAppend.append("0 ");
        }

        stringToAppend.append("0".repeat(Math.max(0, count)));
        stringToAppend.append(" ");
        return stringToAppend.toString();
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
