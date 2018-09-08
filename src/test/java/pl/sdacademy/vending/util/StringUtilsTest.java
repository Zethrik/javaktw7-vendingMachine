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

    @Test
    public void shouldAddCommaToCorrectPrice() {
        // given
        Long priceToAdjust1 = 123L;
        Long priceToAdjust2 = 12L;
        Long priceToAdjust3 = 3L;

        // when
        String adjustedPrice1 = StringUtils.adjustPrice(priceToAdjust1);
        String adjustedPrice2 = StringUtils.adjustPrice(priceToAdjust2);
        String adjustedPrice3 = StringUtils.adjustPrice(priceToAdjust3);

        // then
        assertEquals("1,23", adjustedPrice1);
        assertEquals("0,12", adjustedPrice2);
        assertEquals("0,03", adjustedPrice3);
    }

    @Test
    public void shouldSplitThousandsAndHundreds() {
        // given
        Long priceToAdjust = 123456L;

        // when
        String adjustedPrice = StringUtils.adjustPrice(priceToAdjust);

        // then
        assertEquals("1 234,56", adjustedPrice);
    }

    @Test
    public void shouldSplitThousandsAndHundreds1() {
        // given
        Long priceToAdjust = 12345L;

        // when
        String adjustedPrice = StringUtils.adjustPrice(priceToAdjust);

        // then
        assertEquals("123,45", adjustedPrice);
    }

    @Test
    public void shouldSplitThousandsAndHundreds0() {
        // given
        Long priceToAdjust = 0L;

        // when
        String adjustedPrice = StringUtils.adjustPrice(priceToAdjust);

        // then
        assertEquals("0,00", adjustedPrice);
    }

    @Test
    public void shouldSplitBillionsMillionsAndThousands() {
        // given
        Long priceToAdjust = 1234567890123456789L;

        // when
        String adjustedPrice = StringUtils.adjustPrice(priceToAdjust);

        // then
        assertEquals("12 345 678 901 234 567,89", adjustedPrice);
    }
}