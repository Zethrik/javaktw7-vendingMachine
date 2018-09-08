package pl.sdacademy.vending.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {

    @Test
    public void shouldNotModifyMatchedText() {
        // given
        String textToMatch = "Pavel";
        Integer expectedTextLength = 5;

        // when
        String adjustedText = StringUtils.adjustText(textToMatch, expectedTextLength);

        // then
        assertEquals("Pavel", adjustedText);
    }

    @Test
    public void shouldTrimTextThatIsTooLong() {
        // given
        String textToAdjust = "Ala ma kota";
        Integer expectedTextLength = 8;

        // when
        String adjustedText = StringUtils.adjustText(textToAdjust, expectedTextLength);

        // then
        assertEquals("Ala ma k", adjustedText);
    }

    @Test
    public void shouldCenterTextWhenSpacesAreEqual() {
        // given
        String textToAdjust = "Ala";
        Integer expectedTextLength = 7;

        // when
        String adjustedText = StringUtils.adjustText(textToAdjust, expectedTextLength);

        // then
        assertEquals("  Ala  ", adjustedText);
    }

    @Test
    public void shouldCenterTextWhenSpacesAreNotEqual() {
        // given
        String textToAdjust = "Ala";
        Integer expectedTextLength = 8;

        // when
        String adjustedText = StringUtils.adjustText(textToAdjust, expectedTextLength);

        // then
        assertEquals("   Ala  ", adjustedText);
    }
}