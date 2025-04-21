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

    public void clickPurchaseRequisition() {
        scrollIntoView(purchase);
        //click(purchase);
        purchaseRequisition.click();
    }

    public void clickPO() throws InterruptedException {
        //Thread.sleep(3000);
        //waitForElementToBeClickable(po);
        scrollIntoView(poWait);
        purchaseOrder.click();
    }

    public void clickInventoryManagement() throws InterruptedException {
        //Thread.sleep(1000);
        //waitForElementToBeClickable(iM);
        scrollIntoView(iMWait);
        inventoryManagement.click();
    }

    public void clickPayments() {
        //waitForElement(pay);
        scrollIntoView(payWait);
        payments.click();
    }

    public void clickScheduleProcesses(){
        //waitForElement(schProcess);
        scrollIntoView(schProcessWait);
        scheduledProcesses.click();

    }


}
