package providers;

import constants.PropertiesKeys;
import constants.config.PropertiesConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vasile.Vetisan on 4/21/2017.
 */
public class WebDriverProvider {
    private static WebDriver driver;
    private static final String BROWSER = PropertiesConfig.getProperty(PropertiesKeys.BROWSER_TYPE);

    private WebDriverProvider() {
    }

    public static WebDriver getDriver() {
        switch (BROWSER) {
            case "firefox":
                if (driver == null || ((FirefoxDriver) driver).getSessionId() == null) {
                    System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver.exe");
                    driver = new FirefoxDriver();
                }
                break;
            case "iexplorer":
                if (driver == null || ((InternetExplorerDriver) driver).getSessionId() == null) {
                    System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/IEDriverServer.exe");

                    final DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
                    ieCapabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
                    ieCapabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, "accept");
                    ieCapabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
                    driver = new InternetExplorerDriver(ieCapabilities);
                    driver.manage().window().maximize();
                }
            case "chrome":
                if (driver == null || ((ChromeDriver) driver).getSessionId() == null) {
                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("disable-infobars");
                    options.addArguments("--disable-extensions");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--no-proxy-server");
                    options.addArguments("--enable-automation");

                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    options.setExperimentalOption("prefs", prefs);

                    driver = new ChromeDriver(options);
                    driver.manage().window().maximize();
                }
                break;
        }
        return driver;
    }

}
