/**
 * Created by Vasile.Vetisan on 5/7/2017.
 */

package pageobjects.users;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static pageobjects.components.Form.setFieldValue;
import static providers.WebDriverProvider.getDriver;

public class UserLoginPage
{
	@FindBy(xpath = "//input[@name = 'username']")
	public WebElement emailField;
	
	@FindBy(xpath = "//input[@name = 'password']")
	public WebElement passwordField;
	
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement loginBtn;
	
	
	public UserLoginPage()
	{
		PageFactory.initElements(getDriver(), this);
	}

	
	public UserAccountPage userValidLogin(String userMail, String userPassword)
	{
		setEmailField(userMail);
		setPasswordField(userPassword);
		loginBtn.click();
		
		return new UserAccountPage();
	}
	
	private void setEmailField(String userEmail)
	{
		setFieldValue(emailField, userEmail);
	}
	
	private void setPasswordField(String userPassword)
	{
		setFieldValue(passwordField, userPassword);
	}
	
}
