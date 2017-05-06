/**
 * Created by Vasile.Vetisan on 4/22/2017.
 */

package base;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import static providers.WebDriverProvider.getDriver;

public class BaseSelenium implements InstancteTestClassListener
{
	public static final Logger LOGGER;
	
	static
	{
		configureLOG4J();
		LOGGER = Logger.getLogger(BaseSelenium.class);
	}
	
	@Override
	public void beforeClassSetup()
	{
		getDriver();
		LOGGER.info("Running before ClassSetup ");
	}
	
	@Override
	public void afterClassSetup()
	{
		LOGGER.info("Running after ClassSetup ");
		getDriver().quit();
	}
	
	private static void configureLOG4J()
	{
		final String layout = "[%20.20x] [%20.20t] %40.40c [%5.5p] (%d{HH:mm:ss.SSS}) %m%n";
		
		Logger.getRootLogger().addAppender(new ConsoleAppender(new PatternLayout(layout)));
		Logger.getRootLogger().setLevel(Level.INFO);
		Logger.getRootLogger().getLoggerRepository().setThreshold(Level.DEBUG);
	}
}
