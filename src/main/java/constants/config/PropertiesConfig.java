/**
 * Created by Vasile.Vetisan on 4/21/2017.
 */

package constants.config;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class PropertiesConfig
{
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesConfig.class);
    private static final Properties PROPERTIES = getProperties();

    private PropertiesConfig()
    {
    }

    private static Properties getProperties()
    {
        final Properties props = new Properties();
        final String dir = System.getProperty("user.dir");
        final String file = "/src/main/resources/selenium.properties";
        try (FileInputStream fileInput = new FileInputStream(dir + file))
        {
            props.load(fileInput);
        }
        catch (IOException ex)
        {
            LOGGER.error("Error while reading property file:{},{}", ex, ex.getMessage());
            throw new RuntimeException(ex);
        }
        return props;
    }

    public static String getProperty(String propertyName)
    {
        final String result = PROPERTIES.getProperty(propertyName);
        Assert.assertNotNull("Property must not be null " + propertyName, result);

        return result;
    }
}
