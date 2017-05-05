/**
 * Created by Vasile.Vetisan on 4/23/2017.
 */

package utilities;

import java.util.ArrayList;

import static providers.WebDriverProvider.getDriver;

public class BrowserUtilities {
    public static void navigateToTab(int tabNumber) {
        ArrayList<String> browserTabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(browserTabs.get(tabNumber));
    }

    public static void closeCurrentBrowserTab(int tabToSwitch) {
        ArrayList<String> browserTabs = new ArrayList<>(getDriver().getWindowHandles());
        //close current browser tab
        getDriver().close();
        getDriver().switchTo().window(browserTabs.get(tabToSwitch));
    }

    public static String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }
}
