/**
 * Created by Vasile.Vetisan on 5/6/2017.
 */

package services;


import base.BaseLogin;
import org.junit.Test;
import pageobjects.client.ClientsPage;
import pageobjects.services.ServicesPage;

import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class ServicesTestGUI extends BaseLogin
{
	private ClientsPage clientsPage;
	private ServicesPage servicesPage;
	
	@Override
	public void setUp()
	{
		super.setUp();
		clientsPage = new ClientsPage();
		
	}
	
	
	@Test
	public void testServiceDateAscendingSorting()
	{
		servicesPage = clientsPage.openMyServicesPage();
		servicesPage.sortServicesDateAscending();
		assertDateColumnSortedValues();
		
	}
	
	private void assertDateColumnSortedValues()
	{
		List<Date> dateValues = servicesPage.getDateValuesFromColumn(2);
		for (int i = 0; i < dateValues.size(); i++)
		{
			if (i + 1 < dateValues.size())
			{
				Date date1 = dateValues.get(i);
				Date date2 = dateValues.get(i + 1);
				assertTrue("Dates are not sorted correctly!", date1.compareTo(date2) <= 0);
			}
		}
	}
}
