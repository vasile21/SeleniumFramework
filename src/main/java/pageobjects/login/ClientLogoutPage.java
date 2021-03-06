/**
 * Created by Vasile.Vetisan on 4/23/2017.
 */

package pageobjects.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static providers.WebDriverProvider.getDriver;

public class ClientLogoutPage
{
	
	@FindBy(xpath = "//div[@class='header-lined']/h1")
	private WebElement logutTitle;
	
	@FindBy(xpath = "//div[@class='logincontainer']//div[contains(@class, 'alert-success')]")
	private WebElement successLogoutMsg;
	
	public ClientLogoutPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public String getLogoutPageTitle()
	{
		return logutTitle.getText();
	}
	
	public String getLogoutSuccessMsg()
	{
		return successLogoutMsg.getText();
	}
}
