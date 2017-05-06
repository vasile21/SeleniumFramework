/**
 * Created by Vasile.Vetisan on 4/25/2017.
 */

package pageobjects.client;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.Document;

import java.util.Map;

import static constants.ContactAttributes.*;
import static constants.xpaths.CommonXpaths.COUNTRY_DROPDOWN_XPATH;
import static pageobjects.components.Form.*;
import static providers.WebDriverProvider.getDriver;
import static utilities.WebElementUtilities.getWebElementByXpath;
import static utilities.WebElementUtilities.getWebElementByXpathAndText;
import static utilities.XMLUtilities.getContactsChildNamesAndValues;
import static utilities.XMLUtilities.readXMLFile;

public class ContactsPage
{
	
	public static final String CONTACTS_FILE_PATH = System.getProperty("user.dir") + "\\src\\test\\java\\testresources\\testContacts.xml";
	private static final String CONTACT_INPUT_FIELD_XPATH = "//form[@role='form']//div[@class='row']//input[@id = '%s']";
	private static final String CONTACT_CHECKBOX_XPATH = "//div[@class='controls checkbox']//input[@id = '%s']";
	
	@FindBy(xpath = "//select[@id='country']")
	private WebElement countryInputField;
	
	@FindBy(xpath = "//input[@type = 'submit']")
	private WebElement submitContact;
	
	@FindBy(xpath = "//*[@id='main-body']//div[contains(@class, 'alert-success')]")
	private WebElement successMsg;
	
	@FindBy(xpath = "//input[@value ='Delete Contact']")
	private WebElement deleteBtn;
	
	@FindBy(xpath = "//a[@href='index.php']")
	private WebElement clientPageUrl;
	
	public ContactsPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public void addContactWithMandatoryFields(int contaxtIdx)
	{
		setContactFirstName(contaxtIdx);
		setContactLastName(contaxtIdx);
		setContactEmail(contaxtIdx);
		setContactAddress1(contaxtIdx);
		setContactCity(contaxtIdx);
		setContactPhone(contaxtIdx);
		submitContactForm();
	}
	
	public void setContactFirstName(int contactIdx)
	{
		setContactInputField(FIRST_NAME_NODE_KEY, INPUT_FIRST_NAME_ID, contactIdx);
	}
	
	public void setContactLastName(int contactIdx)
	{
		setContactInputField(LAST_NAME_NODE_KEY, INPUT_LAST_NAME_ID, contactIdx);
	}
	
	public void setContactCompanyName(int contactIdx)
	{
		setContactInputField(COMPANY_NAME_NODE_KEY, INPUT_COMPANY_NAME_ID, contactIdx);
	}
	
	public void setContactEmail(int contactIdx)
	{
		setContactInputField(EMAIL_ADDRESS_NODE_KEY, INPUT_EMAIL_ID, contactIdx);
	}
	
	public void setContactPhone(int contactIdx)
	{
		setContactInputField(PHONE_NUMBER_NODE_KEY, INPUT_PHONE_ID, contactIdx);
	}
	
	public void setContactAddress1(int contactIdx)
	{
		setContactInputField(ADDRESS1_NODE_KEY, INPUT_ADDRESS1_ID, contactIdx);
	}
	
	public void setContactAddress2(int contactIdx)
	{
		setContactInputField(ADDRESS2_NODE_KEY, INPUT_ADDRESS2_ID, contactIdx);
	}
	
	public void setContactCity(int contactIdx)
	{
		setContactInputField(CITY_NODE_KEY, INPUT_CITY, contactIdx);
	}
	
	public void setContactRegion(int contactIdx)
	{
		setContactInputField(REGION_NODE_KEY, INPUT_STATE_ID, contactIdx);
	}
	
	public void setContactZipCode(int contactIdx)
	{
		setContactInputField(ZIP_CODE_NODE_KEY, INPUT_POST_CODE_ID, contactIdx);
	}
	
	public void setContactCountry(int contactIdx)
	{
		setContactCountry(COUNTRY_NODE_KEY, contactIdx);
	}
	
	public void setContactSubAccount()
	{
		setEmailCheckBoxes(SUBACCOUNT_ID);
	}
	
	public void setContactGeneralEmail()
	{
		setEmailCheckBoxes(GENERAL_EMAIL_ID);
	}
	
	public void setContactProductEmail()
	{
		setEmailCheckBoxes(PRODUCT_EMAIL_ID);
	}
	
	public void setContactDomainEmail()
	{
		setEmailCheckBoxes(DOMAIN_EMAIL_ID);
	}
	
	public void setContactInvoiceEmail()
	{
		setEmailCheckBoxes(INVOICE_EMAIL_ID);
	}
	
	public void setContactSupportEmails()
	{
		setEmailCheckBoxes(SUPPORT_EMAIL_ID);
	}
	
	public static boolean getEmailCheckboxStatus(String checkBoxId)
	{
		WebElement checkbox = getWebElementByXpathAndText(CONTACT_CHECKBOX_XPATH, checkBoxId);
		return getCheckboxStatus(checkbox);
	}
	
	public static String getContactFieldValue(String fieldId)
	{
		final WebElement contactInputField = getWebElementByXpathAndText(CONTACT_INPUT_FIELD_XPATH, fieldId);
		return getFieldValue(contactInputField);
	}
	
	public static String getCountryDropDownValue()
	{
		return getDropDownSelectedOption(COUNTRY_DROPDOWN_XPATH);
	}
	
	public void submitContactForm()
	{
		submitContact.click();
	}
	
	public String getContactCreationMsg()
	{
		return successMsg.getText();
	}
	
	public void deleteContact()
	{
		deleteBtn.click();
		Alert javascriptconfirm = getDriver().switchTo().alert();
		javascriptconfirm.accept();
		
	}
	
	public String contactOptionDropDownValue(int contactIdx)
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
						contactInfo.append(entry1.getValue() + " - ");
					}
				}
				for (Map.Entry entry2 : contactInputAttrbutes.entrySet())
				{
					if (entry2.getKey() == EMAIL_ADDRESS_NODE_KEY)
					{
						contactInfo.append(entry2.getValue());
					}
				}
			}
		}
		
		return String.valueOf(contactInfo);
	}
	
	public String getContactDropDownOptionValue(String dropdownXpath)
	{
		final WebElement countryDropdown = getWebElementByXpath(dropdownXpath);
		countryDropdown.click();
		
		return getDropDownSelectedOption(dropdownXpath);
	}
	
	public boolean checkIfOptionExists(String dropdownXpath, String optionValue)
	{
		return searchIfOptionExists(dropdownXpath, optionValue);
	}
	
	private void setContactInputField(String fieldKey, String fieldId, int contactIdx)
	{
		final WebElement inputField = getWebElementByXpathAndText(CONTACT_INPUT_FIELD_XPATH, fieldId);
		Map<String, String> contactInputAttributes = getContactInputValues(contactIdx);
		for (Map.Entry entry : contactInputAttributes.entrySet())
		{
			if (entry.getKey() == fieldKey && !entry.getValue().equals(""))
			{
				setFieldValue(inputField, (String) entry.getValue());
			}
		}
	}
	
	public ClientsPage navigateToClientsPage()
	{
		clientPageUrl.click();
		
		return new ClientsPage();
	}
	
	private void setContactCountry(String fieldKey, int contactIdx)
	{
		final WebElement countryDropdown = getWebElementByXpath(COUNTRY_DROPDOWN_XPATH);
		countryDropdown.click();
		Map<String, String> contactInputAttributes = getContactInputValues(contactIdx);
		for (Map.Entry entry : contactInputAttributes.entrySet())
		{
			if (entry.getKey() == fieldKey && !entry.getValue().equals(""))
			{
				setDropDownByOptionValue(COUNTRY_DROPDOWN_XPATH, (String) entry.getValue());
			}
		}
	}
	
	private void setEmailCheckBoxes(String checkBoxId)
	{
		WebElement emailCheckBox = getWebElementByXpathAndText(CONTACT_CHECKBOX_XPATH, checkBoxId);
		setCheckBox(emailCheckBox);
	}
	
	private Map<String, String> getContactInputValues(int contactIdx)
	{
		Document xmlContactsDoc = readXMLFile(CONTACTS_FILE_PATH);
		
		return getContactsChildNamesAndValues(xmlContactsDoc, "contact", contactIdx);
	}
	
}
