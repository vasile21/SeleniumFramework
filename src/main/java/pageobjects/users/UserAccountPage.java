/**
 * Created by Vasile.Vetisan on 5/7/2017.
 */

package pageobjects.users;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static providers.WebDriverProvider.getDriver;
import static providers.WebDriverWaitProvider.getWebDriverWait;


public class UserAccountPage
{

	@FindBy(xpath = "//h3[@class='RTL']")
	private WebElement userWelcomeMsg;
	
	public UserAccountPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public String getUserWelcomeMesage()
	{
		getWebDriverWait().until(ExpectedConditions.visibilityOf(userWelcomeMsg));
		
		return userWelcomeMsg.getText();
	}
}
