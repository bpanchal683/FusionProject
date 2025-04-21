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

    public void goToCreateInvoice()
    {
        taskButton.click();
        createInvoice.click();
    }

    public void goToCreateAccounting()
    {
        taskButton.click();
        createAccounting.click();
    }

    public void goToCreatePayment(){
        taskButton.click();
        createPayment.click();
    }

    public void goTOManagePayment(){
        taskButton.click();
        managePayments.click();
    }


}
