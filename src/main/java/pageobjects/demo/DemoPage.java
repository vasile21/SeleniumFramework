/**
 * Created by Vasile.Vetisan on 5/5/2017.
 */

package pageobjects.demo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.users.UserHomePage;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static providers.WebDriverProvider.getDriver;
import static providers.WebDriverWaitProvider.getWebDriverWait;

public class DemoPage
{
	@FindBy(xpath = "//a[@href='//www.phptravels.net']")
	private WebElement homepageLink;
	
	public DemoPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	
	public UserHomePage navigateToUserHomePage()
	{
		getWebDriverWait().until(elementToBeClickable(homepageLink));
		homepageLink.click();
		
		return new UserHomePage();
	}
}
