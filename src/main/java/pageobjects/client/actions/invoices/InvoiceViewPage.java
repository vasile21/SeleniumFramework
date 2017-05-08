/**
 * Created by Vasile.Vetisan on 5/7/2017.
 */
package pageobjects.client.actions.invoices;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static providers.WebDriverProvider.getDriver;
import static providers.WebDriverWaitProvider.getWebDriverWait;

public class InvoiceViewPage
{
	
	@FindBy(xpath = "//a[contains(@href, 'dl.php?type=i&id=')]")
	private WebElement downloadBtn;
	
	@FindBy(xpath = "//a[@href = 'clientarea.php']")
	private WebElement toClientArea;
	
	public InvoiceViewPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public void downloadInvoiceToPdf()
	{
		getWebDriverWait().until(elementToBeClickable(downloadBtn));
		downloadBtn.click();
		try
		{
			Thread.sleep(200);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public void backToClientArea()
	{
		getWebDriverWait().until(elementToBeClickable(toClientArea));
		toClientArea.click();
	}
}
