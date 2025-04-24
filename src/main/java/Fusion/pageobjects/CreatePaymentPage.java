package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatePaymentPage extends AbstractComponent {

    WebDriver driver;

    public CreatePaymentPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@id,'AP1:OrgUiId::content')]")
    WebElement businessUnit;
    @FindBy(xpath = "//*[contains(@id,'AP1:payeeNameId::content')]")
    WebElement supplier;
    @FindBy(xpath = "//*[contains(@id,'AP1:checkDate::content')]")
    WebElement paymentDate;
    @FindBy(xpath = "//*[contains(@id,'AP1:paymentTypeFlagUi::content')]")
    WebElement type;
    @FindBy(xpath = "//option[contains(text(),'Quick')]")
    WebElement paymentType;
    @FindBy(xpath = "//*[contains(@id,'AP1:inputText4::content')]")
    WebElement description;
    @FindBy(xpath = "//*[contains(@id,'AP1:bankAccountNameId::content')]")
    WebElement bankAccount;
    @FindBy(xpath = "//*[contains(@id,'paymentMethodNameUiId::content')]")
    WebElement paymentMethod;
    @FindBy(className = "AFAutoSuggestItem")
    WebElement paymentMethodAutoSuggestion;
    @FindBy(xpath = "//*[contains(@id,'AP1:paymentProfileUICompId::content')]")
    WebElement paymentProfileProcess;
    @FindBy(css = ".AFAutoSuggestItemsContainer .AFAutoSuggestItem")
    WebElement paymentProfileAutoSuggestion;
    @FindBy(xpath = "//img[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:1:AP1:AT1:_ATp:commandToolbarButton1::icon']")
    WebElement selectAddButton;
    @FindBy(xpath = "//button[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:1:AP1:cb5']")
    WebElement saveAndClose;
    @FindBy(xpath = "//button[@id='_FOd1::msgDlg::cancel']")
    WebElement confirmationMsg;
    @FindBy(xpath = "//div[@class='x1ph']")
    WebElement paymentNumber;
    @FindBy(xpath = "//input[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:1:coVOId:value00::content']")
    WebElement invoiceNum;
    @FindBy(xpath = "//button[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:1:coVOId::search']")
    WebElement clickSearch;
    @FindBy(xpath = "//*[contains(text(),'Standard')]")
    WebElement standard;
    @FindBy(xpath = "//*[contains(@id,'MAnt2:1:combinedVOCriteriaQueryResultId:_ATp:t1::db')]")
    WebElement selectInvoice;
    @FindBy(xpath = "//button[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:1:cb1']")
    WebElement applyButton;
    @FindBy(xpath = "//button[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:1:dialog1::ok']")
    WebElement okButton;

    By saveAndCloseWait=By.xpath("//button[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:1:AP1:cb5']");
    By confirmationMsgWait=By.xpath("//button[@id='_FOd1::msgDlg::cancel']");
    By bankAccountWait=By.xpath("//*[contains(@id,'AP1:bankAccountNameId::content')]");
    By standardWait=By.xpath("//*[contains(text(),'Standard')]");
    By selectInvoiceWait=By.xpath("//*[contains(@id,'MAnt2:1:combinedVOCriteriaQueryResultId:_ATp:t1::db')]");
    By paymentNumberWait=By.xpath("//div[@class='x1ph']");
    By businessUnitWait=By.xpath("//*[contains(@id,'AP1:OrgUiId::content')]");
    By paymentDateWait=By.xpath("//*[contains(@id,'AP1:checkDate::content')]");
    By invoiceNumWait=By.xpath("//input[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:1:coVOId:value00::content']");
    By clickSearchLoc=By.xpath("//button[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:1:coVOId::search']");

    public void setPaymentDetails(String bussiUnit, String supp, String pymtDate, String desc, String bankAcc, String pymtMeth, String pymtProfileProc) throws InterruptedException {
        waitForElementPresence(businessUnitWait);
        businessUnit.sendKeys(bussiUnit);
        businessUnit.sendKeys(Keys.TAB);
        Thread.sleep(2000);
        supplier.sendKeys(supp);
        waitForFieldToBeReady(paymentDateWait);
        paymentDate.clear();
        paymentDate.sendKeys(pymtDate);
        type.click();
        paymentType.click();
        description.sendKeys(desc);
        Thread.sleep(2000);
        waitForElementPresence(bankAccountWait);
        bankAccount.sendKeys(bankAcc);
        bankAccount.sendKeys(Keys.TAB);
        Thread.sleep(2000);
        paymentMethod.clear();
        paymentMethod.sendKeys(pymtMeth);
        paymentMethodAutoSuggestion.click();
        Thread.sleep(2000);
        paymentProfileProcess.sendKeys(pymtProfileProc);
        Thread.sleep(2000);
        paymentProfileAutoSuggestion.click();
        Thread.sleep(2000);
        selectAddButton.click();

    }

    public void saveClose() throws InterruptedException
    {
        //Thread.sleep(3000);
        waitForElementToDisappear(clickSearchLoc);
        clickElement(saveAndCloseWait);
        //saveAndClose.click();
    }

    public void confirmationMsgPopup() throws InterruptedException{
        //Thread.sleep(3000);
        waitForElementVisible(confirmationMsgWait);
        confirmationMsg.click();
    }


    public String getPaymentNumber() throws InterruptedException {
        Thread.sleep(3000);
        waitForElementPresence(paymentNumberWait);
        String paymentNo=paymentNumber.getText();
        String[] text1=paymentNo.split("for");
        String[] pm=text1[0].split(" ");
        String pyNum =pm[1];
        System.out.println(pyNum);
        return pyNum;
    }

    public void enterInvoiceDetails(String invNum) throws InterruptedException {
        //Thread.sleep(2000);
        waitForFieldToBeReady(invoiceNumWait);
        invoiceNum.sendKeys(invNum);
        Thread.sleep(1000);
        clickSearch.click();
        Thread.sleep(1000);
        waitForElementPresence(standardWait);
        standard.click();
        waitForElementPresence(selectInvoiceWait);
        selectInvoice.click();
        applyButton.click();
        okButton.click();
    }
}
