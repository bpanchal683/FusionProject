package Fusion.tests;

import Fusion.pageobjects.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static Fusion.AbstractComponents.AbstractComponent.fnRandomNum;
import static Fusion.AbstractComponents.AbstractComponent.getCurrentDateFormatted;

public class ProcureToPay {

    public static void main(String[] args) throws Exception {

        String itemName="TL02";
        String quantity="2";
        String price="25";
        String deliveryDate=getCurrentDateFormatted();
        String deliveryLocation="ITC Singapore";
        String supplier="JOHN LEO MABALA DAAN";
        String subinventory="RM";
        String shipment="1234";
        String waybill="1234";
        String packingSlip="1234";
        String billOfLading="1234";
        String businessUnit="IT Convergence Singapore";
        String invoiceNumber=fnRandomNum();
        String InvAmount="50";
        String desc="Invoice";
        String invDate=getCurrentDateFormatted();
        String pymtTerms="Net 30";
        String termDate=getCurrentDateFormatted();
        String accountingDate=getCurrentDateFormatted();
        String linesAmount="50";
        String distributionCombination="11.200.161600.00.306.44000.000000.000000";
        String paymentDate=getCurrentDateFormatted();
        String description= "Invoice";
        String bankAccount="PNC SGP-Regular Checking (USD)-582";
        String paymentMethod="Wire";
        String paymentProcessProfile="ITC SGP AP Wire Format USD";
        String scheduleProcessName="Payables Invoice Register";
        String fromEnteredDate=getCurrentDateFormatted();
        String toEnteredDate=getCurrentDateFormatted();

        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        //Start Test
        //Creating Requsitions
        LandingPage lp=new LandingPage(driver);
        lp.goTo();
        lp.login("Llongxi","Oracle123");
        HomePage homePage=new HomePage(driver);
        NavigatorPage navigatorPage=homePage.clickNavigator();
        navigatorPage.setShowMore();
        RequsitionPage requsitionPage=navigatorPage.clickPurchaseRequisition();
        EnterRequisitionPage enterRequisitionPage=requsitionPage.clickRequsition();
        EditRequisitionPage editRequisitionPage=enterRequisitionPage.addItemsToCart(itemName,quantity,price,deliveryDate,deliveryLocation);
        RequisitionsPage requisitionsPage=editRequisitionPage.clickSubmit();
        String reqNum=requisitionsPage.getRequsitionId();
        ManageRequisitionsPage manageRequisitionsPage=requisitionsPage.clickViewMore();
        manageRequisitionsPage.verifyAndApproveRequisition(reqNum);
        //Creating PO
        homePage.clickHome();
        homePage.clickNavigator();
        navigatorPage.setShowMore();
        navigatorPage.clickPO();
        PurchaseOrderPage purchaseOrderPage=new PurchaseOrderPage(driver);
        purchaseOrderPage.goToProcessRequsition();
        ProcessRequisitionsPage processRequisitionsPage=new ProcessRequisitionsPage(driver);
        processRequisitionsPage.searchRequisitionAndAddToDocumentBuilder(reqNum,supplier);
        String poNum=processRequisitionsPage.getPurchaseOrderId();
        EditPurchaseOrderPage editPurchaseOrderPage=new EditPurchaseOrderPage(driver);
        editPurchaseOrderPage.clickEdit();
        PoEditLinePage poEditLinePage=new PoEditLinePage(driver);
        poEditLinePage.poLineOperations();
        processRequisitionsPage.goToManageOrders();
        ManageOrdersPage manageOrdersPage=new ManageOrdersPage(driver);
        manageOrdersPage.verifyAndApprovePO(poNum);
        //Create Receive
        homePage.clickHome();
        homePage.clickNavigator();
        navigatorPage.setShowMore();
        navigatorPage.clickInventoryManagement();
        InventoryManagementPage inventoryManagementPage=new InventoryManagementPage(driver);
        inventoryManagementPage.goToReceiveExpectedShipments();
        ReceiveExpectedShipmentsPage receiveExpectedShipmentsPage=new ReceiveExpectedShipmentsPage(driver);
        receiveExpectedShipmentsPage.searchAndGoToReceiveLines(poNum);
        ReceiveLinesPage receiveLinesPage=new ReceiveLinesPage(driver);
        receiveLinesPage.performReceiveLinesOperation(quantity,subinventory);
        CreateReceiptPage createReceiptPage=new CreateReceiptPage(driver);
        createReceiptPage.createReceiptAndSubmit(shipment,waybill,packingSlip,billOfLading);
        String recNum=createReceiptPage.getReceiptId();
        //Create Invoice
        homePage.clickHome();
        homePage.clickNavigator();
        navigatorPage.setShowMore();
        navigatorPage.clickInvoice();
        InvoicesPage invoicesPage= new InvoicesPage(driver);
        invoicesPage.goToCreateInvoice();
        CreateInvoicePage createInvoicePage=new CreateInvoicePage(driver);
        createInvoicePage.setInvoiceFieldsWithPO(poNum,invoiceNumber,InvAmount,desc,invDate,pymtTerms,termDate);
        String invoiceNum= createInvoicePage.getInvoiceNumber();
        createInvoicePage.setLineDetails(linesAmount,distributionCombination);
        createInvoicePage.invoiceActions();
        createInvoicePage.accountingConfirmation();
        //create payment
        homePage.clickHome();
        homePage.clickNavigator();
        navigatorPage.clickPayments();
        invoicesPage.goToCreatePayment();
        CreatePaymentPage createPaymentPage=new CreatePaymentPage(driver);
        createPaymentPage.setPaymentDetails(businessUnit,supplier,paymentDate,description,bankAccount,paymentMethod,paymentProcessProfile);
        createPaymentPage.enterInvoiceDetails(invoiceNum);
        createPaymentPage.saveClose();
        String pymNumber=createPaymentPage.getPaymentNumber();
        createPaymentPage.confirmationMsgPopup();
        invoicesPage.goTOManagePayment();
        ManagePaymentPage managePaymentPage=new ManagePaymentPage(driver);
        managePaymentPage.setPaymentFields(paymentDate,pymNumber);
        //Invoice pdf viewer
        homePage.clickHome();
        homePage.clickNavigator();
        navigatorPage.clickScheduleProcesses();
        ScheduleProcessDetailsPage scheduleProcessDetailsPage=new ScheduleProcessDetailsPage(driver);
        scheduleProcessDetailsPage.setScheduleProcesses(scheduleProcessName);
        scheduleProcessDetailsPage.setScheduleDetails(businessUnit,supplier,fromEnteredDate,toEnteredDate);
        String processNum=scheduleProcessDetailsPage.getProcessId();
        scheduleProcessDetailsPage.searchNumber(processNum);
        scheduleProcessDetailsPage.statusSucceded();
        scheduleProcessDetailsPage.clickRepbulish();
        scheduleProcessDetailsPage.getPdf();

    }
}
