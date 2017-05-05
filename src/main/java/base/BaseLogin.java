/**
 * Created by Vasile.Vetisan on 4/25/2017.
 */

package base;

import constants.PropertiesKeys;
import constants.config.PropertiesConfig;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.client.ClientsPage;
import pageobjects.login.LoginPage;
import pageobjects.menus.DefaultMenuPage;

import static providers.WebDriverProvider.getDriver;
import static utilities.BrowserUtilities.navigateToTab;

public class BaseLogin extends BaseSelenium {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseLogin.class);

    private final String userEmail = PropertiesConfig.getProperty(PropertiesKeys.APP_CLIENT_USER);
    private final String userPassword = PropertiesConfig.getProperty(PropertiesKeys.APP_CLIENT_PASSWORD);

    private static final int DEFAULT_TAB = 0;
    private static final int LOGIN_TAB = 1;


    @Before
    public void setUp()
    {
        DefaultMenuPage defaultMenuPage = new DefaultMenuPage();
        LoginPage loginPage = new LoginPage();
        defaultMenuPage.openLoginPage();
        navigateToTab(LOGIN_TAB);
        loginPage.completeValidLoginForm(userEmail, userPassword, false);
        LOGGER.info("Logged in as: " + userEmail);
    }


    @After
    public void tearDown()
    {
       final ClientsPage clientsPage = new ClientsPage();
       clientsPage.userLogout();
       getDriver().quit();
    }
}
