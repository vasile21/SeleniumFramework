/**
 * Created by Vasile.Vetisan on 4/25/2017.
 */

package contacts;

import base.BaseLogin;
import org.junit.Test;
import org.w3c.dom.Document;
import pageobjects.client.ClientsPage;
import pageobjects.client.ContactsPage;

import java.util.Map;

import static constants.ContactAttributes.*;
import static constants.messages.ApplicationMessages.CREATE_CONTACT_MESSAGE;
import static constants.xpaths.CommonXpaths.CONTACT_DROPDOWN_XPATH;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static pageobjects.client.ContactsPage.*;
import static utilities.XMLUtilities.getContactsChildNamesAndValues;
import static utilities.XMLUtilities.readXMLFile;

public class ContactsTestGUI extends BaseLogin
{
	
	private ClientsPage clientsPage;
	private ContactsPage contactsPage;
	
	@Override
	public void setUp()
	{
		
		super.setUp();
		clientsPage = new ClientsPage();
		contactsPage = new ContactsPage();
	}
	
	@Test
	public void testAddContact()
	{
		try
		{
			clientsPage.addContact();
			setContactInputFieldsValues(0);
			setProductInvoiceMails();
			saveNewContact();
			assertContactCreationMsg();
			assertContactDropDownOptionValue();
			assertContactInputFields();
			assertCheckEmailCheckbox();
		} finally
		{
			//clean-up
			contactsPage.deleteContact();
		}
	}
	
	@Test
	public void testDeleteContact()
	{
		clientsPage.addContact();
		contactsPage.addContactWithMandatoryFields(1);
		contactsPage.deleteContact();
		assertContactIsNotDisplayedInDropDown();
		contactsPage.navigateToClientsPage();
		assertContactNotPresentInContList();
	}
	
	private void setContactInputFieldsValues(int contactIdx)
	{
		contactsPage.setContactFirstName(contactIdx);
		contactsPage.setContactLastName(contactIdx);
		contactsPage.setContactCompanyName(contactIdx);
		contactsPage.setContactEmail(contactIdx);
		contactsPage.setContactPhone(contactIdx);
		contactsPage.setContactAddress1(contactIdx);
		contactsPage.setContactAddress2(contactIdx);
		contactsPage.setContactCity(contactIdx);
		contactsPage.setContactRegion(contactIdx);
		contactsPage.setContactZipCode(contactIdx);
		contactsPage.setContactCountry(contactIdx);
	}
	
	private void setProductInvoiceMails()
	{
		contactsPage.setContactProductEmail();
		contactsPage.setContactInvoiceEmail();
	}
	
	private void saveNewContact()
	{
		contactsPage.submitContactForm();
	}
	
	private void assertContactCreationMsg()
	{
		assertEquals("Success meessage is not correct!", CREATE_CONTACT_MESSAGE, contactsPage.getContactCreationMsg());
	}
	
	private void assertContactDropDownOptionValue()
	{
		String expected_value = contactsPage.contactOptionDropDownValue(0);
		String actual_value = contactsPage.getContactDropDownOptionValue(CONTACT_DROPDOWN_XPATH);
		assertEquals("Contact option is not correct", expected_value, actual_value);
	}
	
	private void assertContactInputFields()
	{
		Document xmlContactsDoc = readXMLFile(CONTACTS_FILE_PATH);
		Map<String, String> contactInput = getContactsChildNamesAndValues(xmlContactsDoc, "contact", 0);
		
		for (Map.Entry entry : contactInput.entrySet())
		{
			if (entry.getKey() == FIRST_NAME_NODE_KEY)
			{
				assertEquals("First name value is not correct!", entry.getValue(), getContactFieldValue(INPUT_FIRST_NAME_ID));
			}
			if (entry.getKey() == COMPANY_NAME_NODE_KEY)
			{
				assertEquals("Company name value is not correct!", entry.getValue(), getContactFieldValue(INPUT_COMPANY_NAME_ID));
			}
			if (entry.getKey() == PHONE_NUMBER_NODE_KEY)
			{
				assertEquals("Phone number value is not correct!", entry.getValue(), getContactFieldValue(INPUT_PHONE_ID));
			}
			if (entry.getKey() == ADDRESS1_NODE_KEY)
			{
				assertEquals("Phone number value is not correct!", entry.getValue(), getContactFieldValue(INPUT_ADDRESS1_ID));
			}
			if (entry.getKey() == COUNTRY_NODE_KEY)
			{
				assertEquals("Country name value is not correct", entry.getValue(), getCountryDropDownValue());
			}
		}
	}
	
	private void assertCheckEmailCheckbox()
	{
		assertTrue("Product status is incorrect", getEmailCheckboxStatus(PRODUCT_EMAIL_ID));
		assertTrue("Invoice status is incorrect", getEmailCheckboxStatus(INVOICE_EMAIL_ID));
		assertFalse("General email status is incorrect", getEmailCheckboxStatus(GENERAL_EMAIL_ID));
	}
	
	private void assertContactIsNotDisplayedInDropDown()
	{
		String expected_value = contactsPage.contactOptionDropDownValue(1);
		assertFalse("Option exists in dropdown!", contactsPage.checkIfOptionExists(CONTACT_DROPDOWN_XPATH, expected_value));
	}
	
	private void assertContactNotPresentInContList()
	{
		String expected_value = clientsPage.contactOptionContactList(1);
		assertFalse("Contact exists in contact list!", clientsPage.checkIfDeletedContactIsPresent(expected_value));
	}
}
