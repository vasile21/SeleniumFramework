/**
 * Created by Vasile.Vetisan on 4/23/2017.
 */

package utilities;

import constants.PropertiesKeys;
import constants.config.PropertiesConfig;

import static providers.WebDriverProvider.getDriver;

public class NavigationUtilities
{
	
	private static final String BASE_URL = PropertiesConfig.getProperty(PropertiesKeys.URL_APP_MAIN);
	
	public static void visit(String url)
	{
		getDriver().get(BASE_URL + url);
	}
}
