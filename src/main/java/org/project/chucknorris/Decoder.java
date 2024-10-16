package org.project.chucknorris;

public final class Decoder {
    private static final short BIT_SIZE = 7;
    private static final short BINARY_BLOCK_SEPARATION = 2;
    private static final short MAXIMUM_FIRST_BLOCK_SIZE = 2;
    private static final short BINARY_BASE_CONVERSION = 2;

    public static String decode(String stringToDecode) {
        if(stringToDecode == null) {
            return "";
        }
        String[] splitString = stringToDecode.split(" ");
        if (isEncoded(stringToDecode, splitString)) {
            return "";
        }
        String decodedString = decodeBinaryBlocks(splitString);
        if (validateSize(decodedString)) {
            return "";
        }
        return convertToString(decodedString);
    }

    private static String decodeBinaryBlocks(String[] splitString) {
        StringBuilder resultString = new StringBuilder();
        for (int currentBitPos = 0; currentBitPos < splitString.length; currentBitPos += BINARY_BLOCK_SEPARATION) {
            char currentChar = splitString[currentBitPos].equals("0") ? '1' : '0';
            int count = splitString[currentBitPos + 1].length();

            resultString.append(String.valueOf(currentChar).repeat(count));
        }
        return resultString.toString();
    }

    private static String convertToString(String decodedString) {
        StringBuilder returnString = new StringBuilder();
        for (int currentBitPos = 0; currentBitPos < decodedString.length(); currentBitPos += BIT_SIZE) {
            String binaryString = decodedString.substring(currentBitPos, currentBitPos + BIT_SIZE);
            char number = (char) Integer.parseInt(binaryString, BINARY_BASE_CONVERSION);
            returnString.append(number);
        }
        return returnString.toString();
    }

    private static boolean validateSize(String decodedString) {
        return decodedString.length() % BIT_SIZE != 0;
    }

    private static boolean isEncoded(String verify, String[] blocks) {
        if ((!blocks[0].equals("0")) && (!blocks[0].equals("00"))) {
            return true;
        }
        if (!isPairSize(blocks.length)) {
            return true;
        }
        for (int currentBitPos = 0; currentBitPos < verify.length(); currentBitPos++) {
            if (verify.charAt(currentBitPos) != '0' && verify.charAt(currentBitPos) != ' ') {
                return true;
            }
        }
        for (int currentBinaryBlock = 0; currentBinaryBlock < blocks.length; currentBinaryBlock = currentBinaryBlock + BINARY_BLOCK_SEPARATION) {
            if (blocks[currentBinaryBlock].length() > MAXIMUM_FIRST_BLOCK_SIZE || blocks[currentBinaryBlock].isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private static boolean isPairSize(int size) {
        return size % 2 == 0;
    }


}
