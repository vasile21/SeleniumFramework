/**
 * Created by Vasile.Vetisan on 4/23/2017.
 */

package pageobjects.client;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.Document;
import pageobjects.client.actions.invoices.InvoicesPage;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static constants.ContactAttributes.FIRST_NAME_NODE_KEY;
import static constants.ContactAttributes.LAST_NAME_NODE_KEY;
import static constants.xpaths.WebElementText.INVOICES_OPTION;
import static pageobjects.client.ContactsPage.CONTACTS_FILE_PATH;
import static pageobjects.client.actions.ClientActionList.clickOnAction;
import static providers.WebDriverProvider.getDriver;
import static utilities.XMLUtilities.getContactsChildNamesAndValues;
import static utilities.XMLUtilities.readXMLFile;

public class ClientsPage
{
	private static final String CONTACT_LIST = "//div[@class= 'list-group']//a[contains(@href, 'action=contacts')]";
	
	@FindBy(xpath = "//div[@class = 'header-lined']//h1")
	private WebElement welcomeMessage;
	
	@FindBy(xpath = "//a[@href='clientarea.php?action=addcontact']")
	private WebElement addContactBtn;
	
	public ClientsPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public String getWelcomeMessage()
	{
		return welcomeMessage.getText();
	}
	
	public ContactsPage addContact()
	{
		addContactBtn.click();
		
		return new ContactsPage();
	}
	
	public String contactOptionContactList(int contactIdx)
	{
		Map<String, String> contactInputAttrbutes = getContactInputValues(contactIdx);
		StringBuilder contactInfo = new StringBuilder();
		for (Map.Entry entry : contactInputAttrbutes.entrySet())
		{
			if (entry.getKey() == FIRST_NAME_NODE_KEY)
			{
				contactInfo.append(entry.getValue() + " ");
				for (Map.Entry entry1 : contactInputAttrbutes.entrySet())
				{
					if (entry1.getKey() == LAST_NAME_NODE_KEY)
					{
						contactInfo.append(entry1.getValue());
					}
				}
			}
		}
		
		return String.valueOf(contactInfo);
	}
	
	private Map<String, String> getContactInputValues(int contactIdx)
	{
		Document xmlContactsDoc = readXMLFile(CONTACTS_FILE_PATH);
		
		return getContactsChildNamesAndValues(xmlContactsDoc, "contact", contactIdx);
	}
	
	public boolean checkIfDeletedContactIsPresent(String expectedContact)
	{
		boolean isContactPresent = false;
		boolean contactListIsPresent = getDriver().findElements(By.xpath(CONTACT_LIST)).size() > 0;
		if (contactListIsPresent)
		{
			isContactPresent = true;
			List<WebElement> contacts = getDriver().findElements(By.xpath(CONTACT_LIST));
			for (WebElement cList : contacts)
			{
				isContactPresent = false;
				if (Objects.equals(expectedContact, cList.getText()))
				{
					isContactPresent = true;
					break;
				}
			}
		}
		
		return isContactPresent;
	}
	
	public InvoicesPage navigateToInvoicesPage()
	{
		clickOnAction(INVOICES_OPTION);
		return new InvoicesPage();
	}
}
