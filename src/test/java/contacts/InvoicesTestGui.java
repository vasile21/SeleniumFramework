/**
 * Created by Vasile.Vetisan on 5/7/2017.
 */

package contacts;

import base.ClientBaseLogin;
import org.junit.Test;
import pageobjects.client.ClientsPage;
import pageobjects.client.actions.invoices.InvoiceViewPage;
import pageobjects.client.actions.invoices.InvoicesPage;

import java.util.List;

import static constants.FileNames.INVOICE_2978_EXPECTED_NAME;
import static constants.FileNames.INVOICE_2978_NAME;
import static org.junit.Assert.assertTrue;
import static pageobjects.components.Table.clickOnTableRow;
import static utilities.FileUtilities.*;

public class InvoicesTestGui extends ClientBaseLogin
{
	public static final String PARTIAL_INVOICE_ID = "97";
	public static final String DOWNLOAD_INVOICE_ID = "2978";
	
	private ClientsPage clientsPage;
	private InvoicesPage invoicesPage;
	private InvoiceViewPage invoiceViewPage;
	
	@Override
	public void setUp()
	{
		
		super.setUp();
		clientsPage = new ClientsPage();
	}
	
	@Test
	public void testSearchPartialInvoiceId()
	{
		invoicesPage = clientsPage.navigateToInvoicesPage();
		invoicesPage.searchInvoiceId(PARTIAL_INVOICE_ID);
		assertSearchResults();
	}
	
	
	@Test
	public void testInvoicePdfDownload()
	{
		try
		{
			invoicesPage = clientsPage.navigateToInvoicesPage();
			invoicesPage.searchInvoiceId(DOWNLOAD_INVOICE_ID);
			invoiceViewPage = clickOnTableRow(0);
			invoiceViewPage.downloadInvoiceToPdf();
			invoiceViewPage.backToClientArea();
			checkDownloadStatus(INVOICE_2978_NAME);
			assertDonwloadedFileContet();
		} finally
		{
			//clean-up
			deleteFile(INVOICE_2978_NAME);
		}
	}
	
	private void assertSearchResults()
	{
		List<String> invoiceIdList = invoicesPage.getInvoiceIdValues(0);
		for (String tempItem : invoiceIdList)
		{
			assertTrue("Id cell value does not contain " + PARTIAL_INVOICE_ID, tempItem.contains(PARTIAL_INVOICE_ID));
		}
	}
	
	private void assertDonwloadedFileContet()
	{
		assertTrue("File content is not correct!", verifyIfContentIsIdentic(INVOICE_2978_NAME, INVOICE_2978_EXPECTED_NAME));
	}
}
