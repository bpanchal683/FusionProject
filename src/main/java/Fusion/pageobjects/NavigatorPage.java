package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigatorPage extends AbstractComponent {

    WebDriver driver;

    public NavigatorPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(linkText = "Show More")
    WebElement showMore;
    @FindBy(xpath = "//a[@id='pt1:_UISnvr:0:nv_itemNode_payables_payables_invoices']//span")
    WebElement invoices;
    @FindBy(linkText = "Purchase Requisitions")
    WebElement purchaseRequisition;
    @FindBy(linkText = "Purchase Orders")
    WebElement purchaseOrder;
    @FindBy(linkText = "Inventory Management")
    WebElement inventoryManagement;
    @FindBy(linkText = "Payments")
    WebElement payments;
    @FindBy(xpath = "//span[contains(text(),'Scheduled Processes')]")
    WebElement scheduledProcesses;


    By invWait=By.linkText("Invoices");
    By purchase=By.linkText("Purchase Requisitions");
    By poWait=By.linkText("Purchase Orders");
    By iMWait=By.linkText("Inventory Management");
    By payWait = By.linkText("Payments");
    By schProcessWait = By.xpath("//span[contains(text(),'Scheduled Processes')]");

    public void setShowMore()
    {
        showMore.click();
    }

    public void clickInvoice()
    {
        //waitForElement(inv);
        scrollIntoView(invWait);
        invoices.click();
    }

    public RequsitionPage clickPurchaseRequisition() {
        scrollIntoView(purchase);
        purchaseRequisition.click();
        RequsitionPage requsitionPage=new RequsitionPage(driver);
        return requsitionPage;
    }

    public PurchaseOrderPage clickPO() throws InterruptedException {
        scrollIntoView(poWait);
        purchaseOrder.click();
        PurchaseOrderPage purchaseOrderPage=new PurchaseOrderPage(driver);
        return purchaseOrderPage;
    }

    public InventoryManagementPage clickInventoryManagement() throws InterruptedException {
        scrollIntoView(iMWait);
        inventoryManagement.click();
        InventoryManagementPage inventoryManagementPage=new InventoryManagementPage(driver);
        return inventoryManagementPage;
    }

    public void clickPayments() {
        scrollIntoView(payWait);
        payments.click();
    }

    public void clickScheduleProcesses(){
        scrollIntoView(schProcessWait);
        scheduledProcesses.click();

    }


}
