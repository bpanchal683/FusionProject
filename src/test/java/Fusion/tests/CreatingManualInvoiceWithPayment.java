package Fusion.tests;

import Fusion.TestComponent.BaseTest;
import Fusion.pageobjects.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

import static Fusion.AbstractComponents.AbstractComponent.fnRandomNum;
import static Fusion.AbstractComponents.AbstractComponent.getCurrentDateFormatted;

public class CreatingManualInvoiceWithPayment extends BaseTest {

    @Test
    public void CreateManualInvoiceWithPayment() throws InterruptedException {

        String businessUnit="IT Convergence USA";
        String supplier="ADVANZ101 BUSINESS SYSTEMS INC";
        String invoiceNumber=fnRandomNum();
        String invoiceAmount="56";
        String desc="Creating Invoice";
        String invoiceDate=getCurrentDateFormatted();
        String paymentTerms="Net 30";
        String termsDate=fnRandomNum();
        String accountingDate=getCurrentDateFormatted();
        String liabilityDistribution="01.000.201100.00.000.00000.000000.000000";
        String linesAmount="56";
        String distributionCombination="01.000.201100.00.000.00000.000000.000000";
        String ledger="ITC USA PL";
        String paymentDate=getCurrentDateFormatted();
        String description="invoice";
        String bankAccount="Action Capital # ZAITC-90";
        String paymentMethod="Electronic";
        String paymentProcessProfile="ANSI X12 820";
        String category="Payments";
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
}
