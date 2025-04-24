package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagePaymentPage extends AbstractComponent {

    WebDriver driver;

    public ManagePaymentPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@id,'AP1:q1:value10::content')]")
    WebElement paymentDate;
    @FindBy(xpath = "//*[contains(@id,'AP1:q1:value20::content')]")
    WebElement paymentNumber;
    @FindBy(xpath = "(//button[normalize-space()='Search'])[1]")
    WebElement search;
    @FindBy(xpath = "(//td)[352]")
    WebElement selectPayment;
    @FindBy(xpath = "//a[contains(text(),'Actions')]")
    WebElement actions;
    @FindBy(xpath = "//td[contains(text(),'Post to Ledger')]")
    WebElement postToLedger;
    @FindBy(xpath = "//*[contains(@id, 'AP1:AT1:cb2')]")
    WebElement okButton;

    By selectPaymentWait=By.xpath("(//td)[352]");
    By postToLedgerWait=By.xpath("//td[contains(text(),'Post to Ledger')]");
    By okButtonWait=By.xpath("//*[contains(@id, 'AP1:AT1:cb2')]");


    public void setPaymentFields(String pymtDate,String payNumber) throws InterruptedException {
        paymentDate.sendKeys(pymtDate);
        paymentNumber.sendKeys(payNumber);
        search.click();
        //waitForElementPresence(selectPaymentWait);
        clickElement(selectPaymentWait);
        //selectPayment.click();
        Thread.sleep(2000);
        actions.click();
        Thread.sleep(2000);
        //waitForElementToBeClickable(postToLedgerWait);
        postToLedger.click();
        //Thread.sleep(1000);
        waitForElementPresence(okButtonWait);
        okButton.click();

    }
}
