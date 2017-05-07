/**
 * Created by Vasile.Vetisan on 5/5/2017.
 */

package pageobjects.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeToBe;
import static providers.WebDriverProvider.getDriver;
import static providers.WebDriverWaitProvider.getWebDriverWait;
import static utilities.WebElementUtilities.getWebElementByXpath;

public class UserHomePage
{
	private final static  String ACCOUNT_TOGGLE_XPATH =  "//li[contains(@class, 'width_change')][2]//a[@data-toggle='dropdown']";
	
	@FindBy(xpath = "//a[@href='http://www.phptravels.net/login']")
	private WebElement loginOption;
	@FindBy(xpath = "//a[@href='http://www.phptravels.net/register']")
	private WebElement registerOption;
	
	
	public UserHomePage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public UserLoginPage navigateToUserLoginPage()
	{
		expandUserAccountOptions();
		getWebDriverWait().until(ExpectedConditions.elementToBeClickable(loginOption));
		loginOption.click();
		
		return new UserLoginPage();
	}
	
	private void expandUserAccountOptions()
	{
		WebElement accountToggleLink = getWebElementByXpath(ACCOUNT_TOGGLE_XPATH);
		
		accountToggleLink.click();
		
		getWebDriverWait().until(attributeToBe(By.xpath(ACCOUNT_TOGGLE_XPATH),
				"aria-expanded", "true"));
	}

	public UserRegistrationPage goToUserRegistrationPage()
	{
		expandUserAccountOptions();
		registerOption.click();
		return new UserRegistrationPage();
	}

}
