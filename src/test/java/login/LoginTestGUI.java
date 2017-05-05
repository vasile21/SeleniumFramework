/**
 * Created by Vasile.Vetisan on 4/22/2017.
 */

package login;

import base.BaseSelenium;
import constants.PropertiesKeys;
import constants.config.PropertiesConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.client.ClientsPage;
import pageobjects.login.LoginPage;
import pageobjects.login.LogoutPage;
import pageobjects.menus.DefaultMenuPage;

import static constants.messages.ApplicationUrl.INVALID_LOGIN_URL;
import static constants.messages.ApplicationUrl.URL_AFTER_LOGIN;
import static constants.messages.ApplicationMessages.CLIENT_WELCOME_MESSAGE;
import static constants.messages.ApplicationMessages.INVALID_LOGIN_MESSAGE;
import static org.junit.Assert.assertEquals;
import static providers.WebDriverProvider.getDriver;
import static utilities.BrowserUtilities.closeCurrentBrowserTab;
import static utilities.BrowserUtilities.getCurrentUrl;
import static utilities.BrowserUtilities.navigateToTab;

public class LoginTestGUI extends BaseSelenium {

    private DefaultMenuPage defaultMenu;
    private LoginPage loginPage;
    private ClientsPage clientsPage;
    private LogoutPage logoutPage;

    private static final String VALID_CLIENT_MAIL = PropertiesConfig.getProperty(PropertiesKeys.APP_CLIENT_USER);
    private static final String VALID_CLIENT_PASSWORD = PropertiesConfig.getProperty(PropertiesKeys.APP_CLIENT_PASSWORD);
    private static final String INVALID_CLIENT_PASSWORD = "ABCD";

    private static final int DEFAULT_TAB = 0;
    private static final int LOGIN_TAB = 1;


    @Before
    public void setUp() {
        defaultMenu = new DefaultMenuPage();
        loginPage = new LoginPage();
        clientsPage = new ClientsPage();
    }

    @After
    public void tearDown() {
        getDriver().quit();
    }

    @Test
    public void testLoginWithValidCredentials() {
        try {
            defaultMenu.openLoginPage();
            navigateToTab(LOGIN_TAB);
            loginPage.completeValidLoginForm(VALID_CLIENT_MAIL, VALID_CLIENT_PASSWORD, false);
            assertWelcomeMessage();
            assertPageUrl(URL_AFTER_LOGIN);
        }
        finally {
            //clean-up
            clientsPage.userLogout();
        }
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        defaultMenu.openLoginPage();
        navigateToTab(LOGIN_TAB);
        loginPage.completeInvalidLoginForm(VALID_CLIENT_MAIL, INVALID_CLIENT_PASSWORD);
        asserInvalidLoginMessage();
        assertPageUrl(INVALID_LOGIN_URL);
    }

    @Test
    public void testLoginRememberMe()
    {
        defaultMenu.openLoginPage();
        navigateToTab(LOGIN_TAB);
        loginPage.completeValidLoginForm(VALID_CLIENT_MAIL, VALID_CLIENT_PASSWORD, true);
        assertWelcomeMessage();
        closeCurrentBrowserTab(DEFAULT_TAB);
        defaultMenu.openLoginPage();
        navigateToTab(LOGIN_TAB);
        assertWelcomeMessage();
    }

    private void assertWelcomeMessage() {
        String welcomeMessage = clientsPage.getWelcomeMessage();
        assertEquals("Welcome message is not displayed", CLIENT_WELCOME_MESSAGE, welcomeMessage);
    }

    private void assertPageUrl(String expectedUrl) {
        String currentUrl = getCurrentUrl();
        assertEquals("Page URL is not correct!", expectedUrl, currentUrl);
    }

    private void asserInvalidLoginMessage() {
        String actualInvalidLoginMsg = loginPage.getInvalidLoginMsg();
        assertEquals("Invalid login message is not correct!", INVALID_LOGIN_MESSAGE, actualInvalidLoginMsg);
    }


}
