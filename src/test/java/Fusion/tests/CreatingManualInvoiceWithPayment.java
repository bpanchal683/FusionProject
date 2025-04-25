package Fusion.tests;

import Fusion.TestComponent.BaseTest;
import Fusion.TestComponent.ExcelReader;
import Fusion.pageobjects.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static Fusion.AbstractComponents.AbstractComponent.fnRandomNum;
import static Fusion.AbstractComponents.AbstractComponent.getCurrentDateFormatted;

public class CreatingManualInvoiceWithPayment extends BaseTest {

    @Test(dataProvider = "InvoiceWithPayment")
    public void CreateManualInvoiceWithPayment(Map<String, String> data) throws InterruptedException {

        String businessUnit=data.get("businessUnit");
        String supplier=data.get("supplier");
        String invoiceNumber=fnRandomNum();
        String invoiceAmount=data.get("invoiceAmount");
        String desc=data.get("desc");
        String invoiceDate=getCurrentDateFormatted();
        String paymentTerms=data.get("paymentTerms");
        String termsDate=fnRandomNum();
        String accountingDate=getCurrentDateFormatted();
        String liabilityDistribution=data.get("liabilityDistribution");
        String linesAmount=data.get("linesAmount");
        String distributionCombination=data.get("distributionCombination");
        String ledger=data.get("ledger");
        String paymentDate=getCurrentDateFormatted();
        String description=data.get("description");
        String bankAccount=data.get("bankAccount");
        String paymentMethod=data.get("paymentMethod");
        String paymentProcessProfile=data.get("paymentProcessProfile");
        String category=data.get("category");
        //start test
        //create invoice
        HomePage homePage=lp.login("mjaime","Welcome@123");
        NavigatorPage navigatorPage=homePage.clickNavigator();
        navigatorPage.setShowMore();
        InvoicesPage invoicesPage=navigatorPage.clickInvoice();
        CreateInvoicePage createInvoicePage=invoicesPage.goToCreateInvoice();
        createInvoicePage.setInvoiceFields(businessUnit,supplier,invoiceNumber,invoiceAmount,desc,invoiceDate,paymentTerms,termsDate);
        String invoiceNum= createInvoicePage.getInvoiceNumber();
        createInvoicePage.setAccounting(accountingDate,liabilityDistribution);
        createInvoicePage.setLineDetails(linesAmount,distributionCombination);
        createInvoicePage.validateAndAccountingInvoice();
        createInvoicePage.accountingConfirmation();
        //create accounting
        SubmitRequestPage submitRequestPage=invoicesPage.goToCreateAccounting();
        submitRequestPage.submitRequest(ledger);
        String processId=submitRequestPage.getProcessId();
        submitRequestPage.clickOK();
        submitRequestPage.clickNavigator();
        ScheduleProcessDetailsPage scheduleProcessDetailsPage=navigatorPage.clickScheduleProcesses();
        scheduleProcessDetailsPage.verifyCreateAccountingStatus(processId);
        //create payment
        homePage.clickHome();
        homePage.clickNavigator();
        navigatorPage.clickPayments();
        CreatePaymentPage createPaymentPage=invoicesPage.goToCreatePayment();
        createPaymentPage.setPaymentDetails(businessUnit,supplier,paymentDate,description,bankAccount,paymentMethod,paymentProcessProfile);
        createPaymentPage.enterInvoiceDetails(invoiceNum);
        createPaymentPage.saveClose();
        String pymNumber=createPaymentPage.getPaymentNumber();
        createPaymentPage.confirmationMsgPopup();
        ManagePaymentPage managePaymentPage=invoicesPage.goTOManagePayment();
        managePaymentPage.setPaymentFields(paymentDate,pymNumber);
        //verify payment
        homePage.clickHome();
        homePage.clickNavigator();
        JournalsPage journalsPage=navigatorPage.clickJournals();
        ManageJournalsPage manageJournalsPage=journalsPage.goToManageJournals();
        EditJournalPage editJournalPage=manageJournalsPage.searchPayment(category,invoiceAmount);
        editJournalPage.clickAmount();
    }

    @DataProvider(name = "InvoiceWithPayment")
    public Iterator<Object[]> getInvoiceWithPaymentData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/src/test/java/resources/AP_TestData.xlsx";
        List<Map<String, String>> testData = ExcelReader.getTestData(filePath, "CreateManualInvoiceWithPayment");

        List<Object[]> dataProvider = new ArrayList<>();
        for (Map<String, String> row : testData) {
            dataProvider.add(new Object[]{row});
        }

        return dataProvider.iterator();
    }
}
