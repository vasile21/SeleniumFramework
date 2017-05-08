/**
 * Created by Vasile.Vetisan on 5/7/2017.
 */

package pageobjects.menus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.client.actions.ServicesPage;
import pageobjects.login.ClientLogoutPage;

import static providers.WebDriverProvider.getDriver;
import static providers.WebDriverWaitProvider.getWebDriverWait;
import static utilities.WebElementUtilities.getWebElementByXpath;

public class ClientMenu
{
	private static final String SERVICES_TOGGLE_OPTION = "//li[@id='Primary_Navbar-Services']/a";
	private static final String ACCOUNT_TOGGLE_OPTION = "//li[@id='Secondary_Navbar-Account']/a";
	
	@FindBy(xpath = "//nav[@id='nav']//div[contains(@class, 'navbar-collapse')]")
	private WebElement clientNavContainer;
	
	@FindBy(xpath = "//li[@id='Primary_Navbar-Services']//a[@href='clientarea.php?action=services']")
	private WebElement myServicesMenuOption;
	@FindBy(xpath = "//li[@id='Secondary_Navbar-Account-Logout']/a")
	private WebElement logoutMenuOption;
	
	public ClientMenu()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	
	public ServicesPage navigateToServicesPage()
	{
		getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SERVICES_TOGGLE_OPTION)));
		final WebElement servicesToggle = getWebElementByXpath(SERVICES_TOGGLE_OPTION);
		servicesToggle.click();
		getWebDriverWait().until(ExpectedConditions.attributeToBe(By.xpath(SERVICES_TOGGLE_OPTION), "aria-expanded", "true"));
		myServicesMenuOption.click();
		
		return new ServicesPage();
	}
	
	public ClientLogoutPage clientLogout()
	{
		getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ACCOUNT_TOGGLE_OPTION)));
		final WebElement accountToggle = getWebElementByXpath(ACCOUNT_TOGGLE_OPTION);
		accountToggle.click();
		getWebDriverWait().until(ExpectedConditions.attributeToBe(By.xpath(ACCOUNT_TOGGLE_OPTION), "aria-expanded", "true"));
		
		logoutMenuOption.click();
		
		return new ClientLogoutPage();
	}
	
}
