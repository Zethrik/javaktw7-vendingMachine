import org.junit.Before;
import org.junit.Test;
import pl.sdacademy.vending.util.PropertiesFileConfiguration;

import static org.junit.Assert.assertEquals;

public class PropertiesFileConfigurationTest {

    private PropertiesFileConfiguration configuration;

    @Before
    public void executedBeforeEach() {
        configuration = PropertiesFileConfiguration.getInstance();
    }

    @Test
    public void shouldReturnTextPropertyFromConfiguration() {
        // given
        String propertyName = "property.text.value";

        // when
        String retrievedValue = configuration.getProperty(propertyName, "DEFEAULT_VALUE");

        // then
        assertEquals("some text", retrievedValue);
    }

    @Test
    public void shouldReturnLongPropertyFromConfiguration() {
        // given
        String propertyName = "property.long.value";

        // when
        Long retrievedValue = configuration.getProperty(propertyName, 15L);

        // then
        assertEquals((Long)1337L, retrievedValue);
    }

    @Test
    public void shouldReturnDefeaultTextPropertyFromConfiguration() {
        // given
        String propertyName = "";

        // when
        String retrievedValue = configuration.getProperty(propertyName, "DEFEAULT_VALUE");

        // then
        assertEquals("DEFEAULT_VALUE", retrievedValue);
    }

    @Test
    public void shouldReturnDefeaultLongPropertyFromConfiguration() {
        // given
        String propertyName = "";

        // when
        Long retrievedValue = (configuration.getProperty(propertyName, 15L));

        // then
        assertEquals((Long)15L, retrievedValue);
    }

}
