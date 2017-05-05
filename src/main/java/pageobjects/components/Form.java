/**
 * Created by Vasile.Vetisan on 4/23/2017.
 */

package pageobjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static providers.WebDriverProvider.getDriver;
import static providers.WebDriverWaitProvider.getWebDriverWait;

public class Form {

    public static void setFieldValue(WebElement element, String value)
    {
        getWebDriverWait().until(visibilityOf(element));
        element.click();
        element.clear();
        element.sendKeys(value);

    }

    public static String getFieldValue(WebElement inputField)
    {
        getWebDriverWait().until(visibilityOf(inputField));
        return inputField.getAttribute("value");
    }

    public static void setDropDownByOptionValue(String dropdownXpath, String optionValue)
    {
        Select dropdown = new Select (getDriver().findElement(By.xpath(dropdownXpath)));
        dropdown.selectByVisibleText(optionValue);
    }

    public static String getDropDownSelectedOption(String dropdownXpath)
    {
        Select dropdown = new Select (getDriver().findElement(By.xpath(dropdownXpath)));
        return dropdown.getFirstSelectedOption().getText();
    }

    public static boolean searchIfOptionExists(String dropdownXpath, String searchText)
    {
        boolean found = false;
        Select selectElement = new Select(getDriver().findElement(By.xpath(dropdownXpath)));
        ArrayList<String> allOptions = new ArrayList<String>();
        for(int i=0; i<allOptions.size(); i++) {
            if(allOptions.get(i).equals(searchText)) {
                found=true;
                break;
            }
        }
        return found;
    }
    public static void setCheckBox(WebElement checkBox)
    {
        boolean isChecked = checkBox.isSelected();
        if(!isChecked)
        {
            checkBox.click();
        }
    }

    public static boolean getCheckboxStatus(WebElement checkBox)
    {
        boolean isChecked = false;
        if(checkBox.isSelected()== true)
        {
            isChecked = true;
        }
        else if(checkBox.isSelected() == false)
        {
            isChecked = false;
        }

        return isChecked;
    }
}
