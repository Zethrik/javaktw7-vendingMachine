package pl.sdacademy.vending.util;

public class StringUtils {
    public static String adjustText(String textToMatch, Integer expectedTextLength) {
        if (textToMatch.length() < expectedTextLength) {
            int requiredSpaces = expectedTextLength - textToMatch.length();
            int spaciesOnLeft = (requiredSpaces + 1) / 2;
            int spaciesOnRight = requiredSpaces / 2;
            return multiplyText(" ", spaciesOnLeft)
                    + textToMatch
                    + multiplyText(" ", spaciesOnRight);
        } else {
            return textToMatch.substring(0, expectedTextLength);
        }
    }

    public static String multiplyText (String textToMultiply, Integer times) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer count = 0; count < times; count++) {
            stringBuilder = stringBuilder.append(textToMultiply);
        }
        return stringBuilder.toString();
    }
}
