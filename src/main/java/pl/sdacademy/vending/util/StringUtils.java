package pl.sdacademy.vending.util;

public class StringUtils {
    public static String adjustText(String textToMatch, Integer expectedTextLength) {
        if (textToMatch.length() < expectedTextLength) {
            int requiredSpaces = expectedTextLength - textToMatch.length();
            int spacesOnLeft = (requiredSpaces + 1) / 2;
            int spacesOnRight = requiredSpaces / 2;
            return multiplyText(" ", spacesOnLeft)
                    + textToMatch
                    + multiplyText(" ", spacesOnRight);
        } else {
            return textToMatch.substring(0, expectedTextLength);
        }
    }

    public static String multiplyText(String textToMultiply, Integer times) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer count = 0; count < times; count++) {
            stringBuilder = stringBuilder.append(textToMultiply);
        }
        return stringBuilder.toString();
    }

    public static String adjustPrice(Long price) {
        String priceToText;
        if (price > 99) {
            priceToText = price.toString();
        } else if (price < 10) {
            priceToText = "00" + price.toString();
        } else {
            priceToText = "0" + price.toString();
        }


        String priceToSplit = priceToText.substring(0, priceToText.length() - 2);
        String splittedText = "";
        int counter = 0;

        for (int charIndex = priceToSplit.length() - 1; charIndex >= 0; charIndex--) {
            if (counter < 3) {
                splittedText = String.valueOf(priceToSplit.charAt(charIndex)) + splittedText;
                counter++;
            } else {
                splittedText = new StringBuilder()
                        .append(String.valueOf(priceToSplit.charAt(charIndex)))
                        .append(" ")
                        .append(splittedText)
                        .toString();
                counter = 1;
            }
        }

        return splittedText + "," + priceToText.substring(priceToText.length() - 2);
    }
}
