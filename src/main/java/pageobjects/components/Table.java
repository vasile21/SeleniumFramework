/**
 * Created by Vasile.Vetisan on 5/7/2017.
 */

package pageobjects.components;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.client.actions.invoices.InvoiceViewPage;

import java.util.List;

import static providers.WebDriverProvider.getDriver;
import static providers.WebDriverWaitProvider.getWebDriverWait;

public class Table
{
	@FindBy(xpath = ".//tbody//tr[@role='row']")
	private static List<WebElement> tableRows;
	
	public Table()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public List<WebElement> getTableRows()
	{
		return tableRows;
	}
	
	public WebElement getColumnCells(int rowIdx, int columnIdx)
	{
		return getTableRows().get(rowIdx).findElements(By.xpath(".//td")).get(columnIdx);
	}
	
	public static InvoiceViewPage clickOnTableRow(int rowIdx)
	{
		WebElement selectedRow = tableRows.get(rowIdx);
		getWebDriverWait().until(ExpectedConditions.elementToBeClickable(selectedRow));
		selectedRow.click();
		
		return new InvoiceViewPage();
	}
}
