/**
 * Created by Vasile.Vetisan on 4/22/2017.
 */

package pageobjects.menus;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.login.LoginPage;
import utilities.NavigationUtilities;

import static providers.WebDriverProvider.getDriver;
import static utilities.WebElementUtilities.getContextElement;

public class DefaultMenuPage {

    private final static String loginOption = ".//a[@class='login']";

    @FindBy(xpath = "//nav[@id='main-menu']//ul[@class='nav reset']")
    private WebElement defaultMenuContainer;

    public DefaultMenuPage() {
        PageFactory.initElements(getDriver(), this);
        NavigationUtilities.visit("/");
    }

    public LoginPage openLoginPage() {
        final WebElement loginMenuOption =  getContextElement(defaultMenuContainer, loginOption);
        loginMenuOption.click();
        return new LoginPage();
    }


}
