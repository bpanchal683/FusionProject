package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InvoicesPage extends AbstractComponent {

    WebDriver driver;

    public InvoicesPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@id, 'FndTasksList::icon')]")
    WebElement taskButton;
    @FindBy(linkText = "Create Invoice")
    WebElement createInvoice;
    @FindBy(linkText = "Create Accounting")
    WebElement createAccounting;
    @FindBy(linkText = "Create Payment")
    WebElement createPayment;
    @FindBy(linkText = "Manage Payments")
    WebElement managePayments;

    By taskButtonLoc=By.xpath("//*[contains(@id, 'FndTasksList::icon')]");
    By createInvoiceLoc=By.linkText("Create Invoice");
    By createPaymentWait=By.linkText("Create Payment");
    By createAccountingWait=By.linkText("Create Accounting");

    public CreateInvoicePage goToCreateInvoice()
    {
        waitForElementPresence(taskButtonLoc);
        //taskButton.click();
        clickElement(taskButtonLoc);
        waitForElementPresence(createInvoiceLoc);
        createInvoice.click();
        CreateInvoicePage createInvoicePage=new CreateInvoicePage(driver);
        return createInvoicePage;
    }

    public SubmitRequestPage goToCreateAccounting()
    {
        waitForElementPresence(taskButtonLoc);
        taskButton.click();
        waitForElementPresence(createAccountingWait);
        createAccounting.click();
        SubmitRequestPage submitRequestPage=new SubmitRequestPage(driver);
        return submitRequestPage;
    }

    public CreatePaymentPage goToCreatePayment(){
        waitForElementPresence(taskButtonLoc);
        taskButton.click();
        waitForElementPresence(createPaymentWait);
        createPayment.click();
        CreatePaymentPage createPaymentPage=new CreatePaymentPage(driver);
        return createPaymentPage;
    }

    public ManagePaymentPage goTOManagePayment(){
        waitForElementPresence(taskButtonLoc);
        taskButton.click();
        managePayments.click();
        ManagePaymentPage managePaymentPage=new ManagePaymentPage(driver);
        return managePaymentPage;
    }


}
