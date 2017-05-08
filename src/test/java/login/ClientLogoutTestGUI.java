/**
 * Created by Vasile.Vetisan on 4/25/2017.
 */

package login;

import base.ClientBaseLogin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.client.ClientsPage;
import pageobjects.login.ClientLogoutPage;
import pageobjects.menus.ClientMenu;

import static constants.messages.ApplicationMessages.LOGOUT_PAGE_HEADER;
import static constants.messages.ApplicationMessages.SUCCESS_LOGOUT_MESSAGE;
import static constants.messages.ApplicationUrls.URL_AFTER_LOGOUT;
import static org.junit.Assert.assertEquals;
import static providers.WebDriverProvider.getDriver;
import static utilities.BrowserUtilities.getCurrentUrl;

public class ClientLogoutTestGUI extends ClientBaseLogin
{
	
	private ClientsPage clientsPage;
	private ClientMenu clientMenu;
	private ClientLogoutPage clientLogoutPage;
	
	@Override
	@Before
	public void setUp()
	{
		super.setUp();
		clientMenu = new ClientMenu();
	}
	
	@Override
	@After
	public void tearDown()
	{
		getDriver().quit();
	}
	
	@Test
	public void testUserLogout()
	{
		clientsPage = new ClientsPage();
		clientLogoutPage = new ClientLogoutPage();
		clientMenu.clientLogout();
		assertLogoutMessages();
	}
	
	private void assertLogoutMessages()
	{
		String actualLogoutPageTitle = clientLogoutPage.getLogoutPageTitle();
		String actualLogoutPageMsg = clientLogoutPage.getLogoutSuccessMsg();
		String actualLogoutPageUrl = getCurrentUrl();
		
		assertEquals("Logout Page title is not correct!", LOGOUT_PAGE_HEADER, actualLogoutPageTitle);
		assertEquals("Logout success message is not correct!", SUCCESS_LOGOUT_MESSAGE, actualLogoutPageMsg);
		assertEquals("URL after logout is not correct!", URL_AFTER_LOGOUT, actualLogoutPageUrl);
	}
}
