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
import pageobjects.login.ClientLoginPage;
import pageobjects.menus.ClientMenu;
import pageobjects.menus.DefaultPage;

import static providers.WebDriverProvider.getDriver;
import static utilities.BrowserUtilities.navigateToTab;

public class ClientBaseLogin extends BaseSelenium
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientBaseLogin.class);
	
	private final String userEmail = PropertiesConfig.getProperty(PropertiesKeys.APP_CLIENT_MAIL);
	private final String userPassword = PropertiesConfig.getProperty(PropertiesKeys.APP_CLIENT_PASSWORD);
	
	private static final int DEFAULT_TAB = 0;
	private static final int LOGIN_TAB = 1;
	
	@Before
	public void setUp()
	{
		DefaultPage defaultPage = new DefaultPage();
		ClientLoginPage clientLoginPage = new ClientLoginPage();
		defaultPage.openLoginPage();
		navigateToTab(LOGIN_TAB);
		clientLoginPage.completeValidLoginForm(userEmail, userPassword, false);
	}
	
	@After
	public void tearDown()
	{
		final ClientMenu clientsMenu = new ClientMenu();
		clientsMenu.clientLogout();
		getDriver().quit();
	}
}
