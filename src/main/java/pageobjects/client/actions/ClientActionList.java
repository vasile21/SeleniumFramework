/**
 * Created by Vasile.Vetisan on 5/7/2017.
 */

package pageobjects.client.actions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.WebElementUtilities;

import static providers.WebDriverProvider.getDriver;
import static providers.WebDriverWaitProvider.getWebDriverWait;

public class ClientActionList
{
	private static final String ACTIONS_TITLE = "//div[@class='title' and text() = '%s']";
	
	public ClientActionList()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public static void clickOnAction(String actionTitle)
	{
		WebElement actionItem = WebElementUtilities.getWebElementByXpathAndText(ACTIONS_TITLE, actionTitle);
		getWebDriverWait().until(ExpectedConditions.elementToBeClickable(actionItem));
		
		actionItem.click();
	}
	
}
