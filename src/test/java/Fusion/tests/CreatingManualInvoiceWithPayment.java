package Fusion.tests;

import Fusion.TestComponent.BaseTest;
import Fusion.pageobjects.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static Fusion.AbstractComponents.AbstractComponent.fnRandomNum;
import static Fusion.AbstractComponents.AbstractComponent.getCurrentDateFormatted;

public class CreatingManualInvoiceWithPayment extends BaseTest {

    public static void main(String[] args) throws Exception {

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

        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        //start test
        LandingPage lp=new LandingPage(driver);
        lp.goTo();
        lp.login("mjaime","Welcome@123");
        HomePage homePage=new HomePage(driver);
        homePage.clickNavigator();
        NavigatorPage navigatorPage=new NavigatorPage(driver);
        navigatorPage.setShowMore();
        navigatorPage.clickInvoice();
        InvoicesPage invoicesPage=new InvoicesPage(driver);
        invoicesPage.goToCreateInvoice();
        CreateInvoicePage createInvoicePage=new CreateInvoicePage(driver);
        createInvoicePage.setInvoiceFields(businessUnit,supplier,invoiceNumber,invoiceAmount,desc,invoiceDate,paymentTerms,termsDate);
        createInvoicePage.setAccounting(accountingDate,liabilityDistribution);
        createInvoicePage.setLineDetails(linesAmount,distributionCombination);
        createInvoicePage.invoiceActions();
        createInvoicePage.accountingConfirmation();
        invoicesPage.goToCreateAccounting();
        SubmitRequestPage submitRequestPage=new SubmitRequestPage(driver);
        submitRequestPage.submitRequest(ledger);

    }
}
