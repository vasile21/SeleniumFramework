package providers;

import constants.PropertiesKeys;
import constants.config.PropertiesConfig;
import org.openqa.selenium.support.ui.WebDriverWait;

import static providers.WebDriverProvider.getDriver;

/**
 * Created by Vasile.Vetisan on 4/21/2017.
 */
public class WebDriverWaitProvider {
    private WebDriverWaitProvider()
    {
    }

    public static WebDriverWait getWebDriverWait()
    {
        final Long timeOutInSeconds = Long.parseLong(PropertiesConfig.getProperty(PropertiesKeys.TIMEOUT_IN_SECONDS));
        final Long sleepInMillis = Long.parseLong(PropertiesConfig.getProperty(PropertiesKeys.SLEEP_IN_MILLIS));
        return new WebDriverWait(getDriver(), timeOutInSeconds, sleepInMillis);
    }
}
