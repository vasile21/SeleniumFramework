/**
 * Created by Vasile.Vetisan on 5/7/2017.
 */

package pageobjects.client.actions.invoices;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.components.Table;

import java.util.ArrayList;
import java.util.List;

import static pageobjects.components.Form.setFieldValue;
import static providers.WebDriverProvider.getDriver;
import static providers.WebDriverWaitProvider.getWebDriverWait;

public class InvoicesPage
{
	@FindBy(xpath = "//div[@id='tableInvoicesList_filter']//input[@type='search']")
	private WebElement invoiceSearchField;
	
	public InvoicesPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	
	private Table invoices = new Table();
	
	public void searchInvoiceId(String invoiceId)
	{
		getWebDriverWait().until(ExpectedConditions.elementToBeClickable(invoiceSearchField));
		setFieldValue(invoiceSearchField, invoiceId);
	}
	
	public List<String> getInvoiceIdValues(int columnIdx)
	{
		List<String> idCellValues = new ArrayList<>();
		for (int i = 0; i < invoices.getTableRows().size(); i++)
		{
			String invoiceIdValue = invoices.getColumnCells(i, 0).getText();
			idCellValues.add(invoiceIdValue);
		}
		return idCellValues;
	}
	
}
