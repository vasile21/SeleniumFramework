/**
 * Created by Vasile.Vetisan on 5/6/2017.
 */

package pageobjects.client.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageobjects.components.Table;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeToBe;
import static providers.WebDriverProvider.getDriver;
import static providers.WebDriverWaitProvider.getWebDriverWait;
import static utilities.WebElementUtilities.getWebElementByXpath;

public class ServicesPage
{
	private final static String DATE_COLUMN = "//table[@id = 'tableServicesList']//th[@aria-label = 'Next Due Date: activate to sort column ascending']";
	
	public ServicesPage()
	{
		PageFactory.initElements(getDriver(),this);
	}
	
	public void sortServicesDateDescending()
	{
		WebElement dateColumn = getWebElementByXpath(DATE_COLUMN);
		String sortClass = dateColumn.getAttribute("class");
		if(sortClass.equals("sorting"))
		{
			dateColumn.click();
			getWebDriverWait().until(attributeToBe(By.xpath(String.valueOf(dateColumn)),"class",
					"sorting-desc"));
		}
		else if(sortClass.equals("sorting-asc"))
		{
			dateColumn.click();
			getWebDriverWait().until(attributeToBe(By.xpath(String.valueOf(dateColumn)),"class",
					"sorting-desc"));
		}
	}
	
	public void sortServicesDateAscending()
	{
		WebElement dateColumn = getWebElementByXpath(DATE_COLUMN);
		String sortClass = dateColumn.getAttribute("class");
		if(sortClass.equals("sorting"))
		{
			dateColumn.click();
			getWebDriverWait().until(attributeToBe(dateColumn,"class", "sorting_asc"));
		}
		else if(sortClass.equals("sorting_desc"))
		{
			dateColumn.click();
			getWebDriverWait().until(attributeToBe(By.xpath(String.valueOf(dateColumn)),"class",
					"sorting_asc"));
		}
	}
	
	public List<Date> getDateValuesFromColumn(int colIdx)
	{
		List<Date> columnCellValues = new ArrayList<>(); ;
		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		for(int i = 0; i < getServicesTableRows().size(); i++)
		{
			String itemValue = getColumnCells(i, colIdx).getText();
			Date date = null;
			try
			{
				date = sourceFormat.parse(itemValue);
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			columnCellValues.add(date);
		}
		return columnCellValues;
	}
	
	private List<WebElement> getServicesTableRows()
	{
		Table table = new Table();
		return table.getTableRows();
	}
	
	private WebElement getColumnCells(int rowIdx, int columnIdx)
	{
		return getServicesTableRows().get(rowIdx).findElements(By.xpath(".//td")).get(columnIdx);
	}
}
