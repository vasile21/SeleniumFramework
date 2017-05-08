/**
 * Created by Vasile.Vetisan on 5/8/2017.
 */

package utilities;

import com.testautomationguru.utility.PDFUtil;
import constants.PropertiesKeys;
import constants.config.PropertiesConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtilities
{
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtilities.class);
	
	private static final String BROWSER_TYPE = PropertiesConfig.getProperty(PropertiesKeys.BROWSER_TYPE);
	
	public static void checkDownloadStatus(String fileName)
	{
		final String filePath = PropertiesConfig.getProperty(PropertiesKeys.DOWNLOAD_PATH).concat(fileName);
		File downloadedFile = new File(filePath);
		for (int i = 0; i < 10; i++)
		{
			if (BROWSER_TYPE.equals("chrome"))
			{
				try
				{
					Thread.sleep(2000);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
				if (downloadedFile.isFile() && downloadedFile.canRead())
				{
					LOGGER.info("File: " + fileName + " was downloaded to: " + PropertiesConfig.getProperty(PropertiesKeys.DOWNLOAD_PATH));
					break;
				}
			}
		}
	}
	
	public static boolean verifyIfContentIsIdentic(String downloadedFile, String expectedFile)
	{
		boolean identicContent = false;
		PDFUtil pdfUtil = new PDFUtil();
		
		SimpleDateFormat formDate = new SimpleDateFormat("dd/MM/yyyy");
		String currentDate = formDate.format(new Date());
		
		//07/05/2017 - is the date when test PDF document was generated
		pdfUtil.excludeText("07/05/2017", currentDate);
		final String actual_file = PropertiesConfig.getProperty(PropertiesKeys.DOWNLOAD_PATH).concat(downloadedFile);
		;
		final String expected_file = System.getProperty("user.dir") + "/src/test/java/testresources/".concat(expectedFile);
		try
		{
			identicContent = pdfUtil.compare(actual_file, expected_file);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return identicContent;
	}
	
	public static void deleteFile(String fileName)
	{
		final String filePath = PropertiesConfig.getProperty(PropertiesKeys.DOWNLOAD_PATH).concat(fileName);
		File file = new File(filePath);
		if (file.isFile())
		{
			file.delete();
		}
	}
	
	
}
