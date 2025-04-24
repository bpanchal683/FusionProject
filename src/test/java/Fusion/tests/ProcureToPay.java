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

public class ProcureToPay extends BaseTest {


    @Test(dataProvider = "P2PData")
    public void ProcureToPay(Map<String, String> data) throws Exception {

        String itemName=data.get("itemName");
        String quantity=data.get("quantity");
        String price=data.get("price");
        String deliveryDate=getCurrentDateFormatted();
        String deliveryLocation=data.get("deliveryLocation");
        String supplier=data.get("supplier");
        String subinventory=data.get("subinventory");
        String shipment=data.get("shipment");
        String waybill=data.get("waybill");
        String packingSlip=data.get("packingSlip");
        String billOfLading=data.get("billOfLading");
        String businessUnit=data.get("businessUnit");
        String invoiceNumber=fnRandomNum();
        String InvAmount=data.get("InvAmount");
        String desc=data.get("desc");
        String invDate=getCurrentDateFormatted();
        String pymtTerms=data.get("pymtTerms");
        String termDate=getCurrentDateFormatted();
        String linesAmount=data.get("linesAmount");
        String distributionCombination=data.get("distributionCombination");
        String paymentDate=getCurrentDateFormatted();
        String description=data.get("description");
        String bankAccount=data.get("bankAccount");
        String paymentMethod=data.get("paymentMethod");
        String paymentProcessProfile=data.get("paymentProcessProfile");
        String scheduleProcessName=data.get("scheduleProcessName");
        String fromEnteredDate=getCurrentDateFormatted();
        String toEnteredDate=getCurrentDateFormatted();

        //Start Testhn
        //Creating Requsitions
        HomePage homePage=lp.login("Llongxi","Oracle123");
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
        PurchaseOrderPage purchaseOrderPage=navigatorPage.clickPO();
        ProcessRequisitionsPage processRequisitionsPage=purchaseOrderPage.goToProcessRequsition();
        processRequisitionsPage.searchRequisitionAndAddToDocumentBuilder(reqNum,supplier);
        String poNum=processRequisitionsPage.getPurchaseOrderId();
        EditPurchaseOrderPage editPurchaseOrderPage=processRequisitionsPage.clickOk();
        PoEditLinePage poEditLinePage=editPurchaseOrderPage.clickEdit();
        poEditLinePage.poLineOperations();
        ManageOrdersPage manageOrdersPage=processRequisitionsPage.goToManageOrders();
        manageOrdersPage.verifyAndApprovePO(poNum);
        //Create Receive
        homePage.clickHome();
        homePage.clickNavigator();
        navigatorPage.setShowMore();
        InventoryManagementPage inventoryManagementPage=navigatorPage.clickInventoryManagement();
        ReceiveExpectedShipmentsPage receiveExpectedShipmentsPage=inventoryManagementPage.goToReceiveExpectedShipments();
        ReceiveLinesPage receiveLinesPage=receiveExpectedShipmentsPage.searchAndGoToReceiveLines(poNum);
        CreateReceiptPage createReceiptPage=receiveLinesPage.performReceiveLinesOperation(quantity,subinventory);
        createReceiptPage.createReceiptAndSubmit(shipment,waybill,packingSlip,billOfLading);
        String recNum=createReceiptPage.getReceiptId();
        //Create Invoice
        homePage.clickHome();
        homePage.clickNavigator();
        navigatorPage.setShowMore();
        InvoicesPage invoicesPage=navigatorPage.clickInvoice();
        CreateInvoicePage createInvoicePage=invoicesPage.goToCreateInvoice();
        createInvoicePage.setInvoiceFieldsWithPO(poNum,invoiceNumber,InvAmount,desc,invDate,pymtTerms,termDate);
        String invoiceNum= createInvoicePage.getInvoiceNumber();
        createInvoicePage.setLineDetails(linesAmount,distributionCombination);
        createInvoicePage.invoiceActions();
        createInvoicePage.accountingConfirmation();
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
        //Invoice pdf viewer
        homePage.clickHome();
        homePage.clickNavigator();
        ScheduleProcessDetailsPage scheduleProcessDetailsPage=navigatorPage.clickScheduleProcesses();
        scheduleProcessDetailsPage.setScheduleProcesses(scheduleProcessName);
        scheduleProcessDetailsPage.setScheduleDetails(businessUnit,supplier,fromEnteredDate,toEnteredDate);
        String processNum=scheduleProcessDetailsPage.getProcessId();
        scheduleProcessDetailsPage.searchNumber(processNum);
        scheduleProcessDetailsPage.statusSucceded();
        scheduleProcessDetailsPage.clickRepbulish();
        scheduleProcessDetailsPage.getPdf();

    }

    @DataProvider(name = "P2PData")
    public Iterator<Object[]> getP2PData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/src/test/java/resources/AP_TestData.xlsx";
        List<Map<String, String>> testData = ExcelReader.getTestData(filePath, "P2P");

        List<Object[]> dataProvider = new ArrayList<>();
        for (Map<String, String> row : testData) {
            dataProvider.add(new Object[]{row});
        }

        return dataProvider.iterator();
    }

}
