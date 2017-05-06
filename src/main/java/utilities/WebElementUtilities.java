/**
 * Created by Vasile.Vetisan on 4/22/2017.
 */

package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static providers.WebDriverProvider.getDriver;
import static providers.WebDriverWaitProvider.getWebDriverWait;

public class WebElementUtilities
{
	
	public static WebElement webElementFromStringWithText(String stringXpath)
	{
		final WebElement webElement = getDriver().findElement(By.xpath(stringXpath));
		return webElement;
	}
	
	public static WebElement getWebElementByXpath(String webElementXpath)
	{
		final WebElement webElement = getDriver().findElement(By.xpath(webElementXpath));
		
		return webElement;
	}
	
	public static WebElement getContextElement(WebElement contextWebElement, String webElementXpath)
	{
		getWebDriverWait().until(visibilityOf(contextWebElement));
		final WebElement webElement = contextWebElement.findElement(By.xpath(webElementXpath));
		getWebDriverWait().until(visibilityOf(webElement));
		
		return webElement;
	}
	
	public static WebElement getWebElementByXpathAndText(String webElementXpath, String webElementText)
	{
		final String webElementPath = String.format(webElementXpath, webElementText);
		
		return getDriver().findElement(By.xpath(webElementPath));
	}
}
