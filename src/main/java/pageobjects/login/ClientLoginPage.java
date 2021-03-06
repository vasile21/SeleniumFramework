/**
 * Created by Vasile.Vetisan on 4/22/2017.
 */

package pageobjects.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.client.ClientsPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementValue;
import static pageobjects.components.Form.setCheckBox;
import static pageobjects.components.Form.setFieldValue;
import static providers.WebDriverProvider.getDriver;
import static providers.WebDriverWaitProvider.getWebDriverWait;

public class ClientLoginPage
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientLoginPage.class);
	
	@FindBy(xpath = "//input[@id='inputEmail']")
	private WebElement emailField;
	
	@FindBy(xpath = "//input[@id='inputPassword']")
	private WebElement passwordField;
	
	@FindBy(xpath = "//div[@class='checkbox']//input[@name = 'rememberme']")
	private WebElement rememberMeCheckbox;
	
	@FindBy(xpath = "//input[@id ='login']")
	private WebElement loginBtn;
	
	@FindBy(xpath = "//div[@class='logincontainer']//div[contains(@class,'alert-danger')]")
	private WebElement invalidLoginMsg;
	
	public ClientLoginPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public void setEmail(String clientEmail)
	{
		setFieldValue(emailField, clientEmail);
		getWebDriverWait().until(textToBePresentInElementValue(emailField, clientEmail));
	}
	
	public void setPassword(String clientPassword)
	{
		setFieldValue(passwordField, clientPassword);
		getWebDriverWait().until(textToBePresentInElementValue(passwordField, clientPassword));
	}
	
	public String getInvalidLoginMsg()
	{
		return invalidLoginMsg.getText();
	}
	
	public ClientsPage submitValidLoginForm()
	{
		getWebDriverWait().until(elementToBeClickable(loginBtn));
		loginBtn.submit();
		
		return new ClientsPage();
	}
	
	public void submitInvalidLoginForm()
	{
		getWebDriverWait().until(elementToBeClickable(loginBtn));
		loginBtn.submit();
	}
	
	public void setRememberMeCheckbox()
	{
		setCheckBox(rememberMeCheckbox);
	}
	
	public void completeValidLoginForm(String clientEmail, String clientPassword, boolean rememberLogin)
	{
		setEmail(clientEmail);
		setPassword(clientPassword);
		if (rememberLogin == true)
		{
			setRememberMeCheckbox();
		}
		submitValidLoginForm();
		LOGGER.info("Client user logged in as: " + clientEmail);
	}
	
	public void completeInvalidLoginForm(String clientEmail, String clientPassword)
	{
		setEmail(clientEmail);
		setPassword(clientPassword);
		submitInvalidLoginForm();
	}
}
