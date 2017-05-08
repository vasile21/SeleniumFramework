/**
 * Created by Vasile.Vetisan on 5/7/2017.
 */

package login;


import base.BaseSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.demo.DemoPage;
import pageobjects.menus.DefaultPage;
import pageobjects.users.UserAccountPage;
import pageobjects.users.UserHomePage;
import pageobjects.users.UserLoginPage;

import static constants.messages.ApplicationMessages.USER_WELCOME_MESSAGE;
import static org.junit.Assert.assertEquals;
import static providers.WebDriverProvider.getDriver;
import static utilities.BrowserUtilities.navigateToTab;

public class UserLoginTestGUI extends BaseSelenium
{
	private static final String VALID_USER_MAIL = "user@phptravels.com";//PropertiesConfig.getProperty(PropertiesKeys.APP_USER_MAIL);
	private static final String VALID_USER_PASSWORD = "demouser"; //PropertiesConfig.getProperty(PropertiesKeys.APP_USER_PASSWORD);
	
	private static final int DEFAULT_TAB = 0;
	private static final int HOMEPAGE_TAB = 1;
	
	private DefaultPage defaultPage;
	private DemoPage demoPage;
	private UserHomePage userHomePage;
	private UserLoginPage userLoginpage;
	private UserAccountPage userAccountpage;
	
	@Before
	public void setUp()
	{
		defaultPage = new DefaultPage();
	}
	
	@After
	public void tearDown()
	{
		getDriver().quit();
	}
	
	@Test
	public void testUserLogin()
	{
		demoPage = defaultPage.openDemoPage();
		userHomePage = demoPage.navigateToUserHomePage();
		navigateToTab(HOMEPAGE_TAB);
		userLoginpage = userHomePage.navigateToUserLoginPage();
		userAccountpage = userLoginpage.userValidLogin(VALID_USER_MAIL, VALID_USER_PASSWORD);
		assertUserWelcomeMessage();
	}
	
	private void assertUserWelcomeMessage()
	{
		assertEquals("Welcome message is not correct", USER_WELCOME_MESSAGE, userAccountpage.getUserWelcomeMesage());
	}
	
	
}
