package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
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

    public CreateInvoicePage goToCreateInvoice()
    {
        taskButton.click();
        createInvoice.click();
        CreateInvoicePage createInvoicePage=new CreateInvoicePage(driver);
        return createInvoicePage;
    }

    public void goToCreateAccounting()
    {
        taskButton.click();
        createAccounting.click();
    }

    public CreatePaymentPage goToCreatePayment(){
        taskButton.click();
        createPayment.click();
        CreatePaymentPage createPaymentPage=new CreatePaymentPage(driver);
        return createPaymentPage;
    }

    public ManagePaymentPage goTOManagePayment(){
        taskButton.click();
        managePayments.click();
        ManagePaymentPage managePaymentPage=new ManagePaymentPage(driver);
        return managePaymentPage;
    }


}
