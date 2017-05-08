/**
 * Created by Vasile.Vetisan on 4/22/2017.
 */

package pageobjects.menus;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.demo.DemoPage;
import pageobjects.login.ClientLoginPage;
import utilities.NavigationUtilities;

import static providers.WebDriverProvider.getDriver;
import static providers.WebDriverWaitProvider.getWebDriverWait;
import static utilities.WebElementUtilities.getContextElement;

public class DefaultPage
{
	private final static String loginOption = ".//a[@class='login']";
	
	@FindBy(xpath = "//nav[@id='main-menu']//ul[@class='nav reset']")
	private WebElement defaultMenuContainer;
	
	@FindBy(xpath = "//div[@class='container']//a[@href='http://phptravels.com/demo/']")
	private WebElement demoBtn;
	
	public DefaultPage()
	{
		PageFactory.initElements(getDriver(), this);
		NavigationUtilities.visit("/");
	}
	
	public ClientLoginPage openLoginPage()
	{
		final WebElement loginMenuOption = getContextElement(defaultMenuContainer, loginOption);
		loginMenuOption.click();
		
		return new ClientLoginPage();
	}
	
	public DemoPage openDemoPage()
	{
		getWebDriverWait().until(ExpectedConditions.elementToBeClickable(demoBtn));
		demoBtn.click();
		
		return new DemoPage();
	}
}
