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

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static pageobjects.components.Form.*;
import static providers.WebDriverProvider.getDriver;
import static providers.WebDriverWaitProvider.getWebDriverWait;

public class LoginPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);


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

    public LoginPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void setEmail(String userEmail) {
        setFieldValue(emailField, userEmail);
        getWebDriverWait().until(textToBePresentInElementValue(emailField, userEmail));
    }

    public void setPassword(String userPassword) {
        setFieldValue(passwordField, userPassword);
        getWebDriverWait().until(textToBePresentInElementValue(passwordField, userPassword));
    }

    public String getInvalidLoginMsg() {
        return invalidLoginMsg.getText();
    }

    public ClientsPage submitValidLoginForm() {
        getWebDriverWait().until(elementToBeClickable(loginBtn));
        loginBtn.submit();
        return new ClientsPage();

    }

    public void submitInvalidLoginForm() {
        getWebDriverWait().until(elementToBeClickable(loginBtn));
        loginBtn.submit();
    }

    public void setRememberMeCheckbox()
    {
        setCheckBox(rememberMeCheckbox);
    }

    public void completeValidLoginForm(String userEmail, String userPassword, boolean rememberLogin) {
        setEmail(userEmail);
        setPassword(userPassword);
        if(rememberLogin == true)
        {
            setRememberMeCheckbox();
        }
        submitValidLoginForm();
        LOGGER.info("Logged in as: " + userEmail);
    }

    public void completeInvalidLoginForm(String userEmail, String userPassword) {
        setEmail(userEmail);
        setPassword(userPassword);
        submitInvalidLoginForm();
    }




}
