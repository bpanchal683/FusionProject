package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubmitRequestPage extends AbstractComponent {

    WebDriver driver;

    public SubmitRequestPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@id,'SubledgerApplicationAttr::content')]")
    WebElement selectSubledger;
    @FindBy(xpath = "//option[contains(text(),'Payables')]")
    WebElement selectPayables;
    @FindBy(xpath = "//*[contains(@id,'LedgerAttr::content')]")
    WebElement selectLedger;
    @FindBy(xpath = "//*[contains(@id,'LedgerAttr::content')]")
    WebElement selectLedgerKey;
    @FindBy(xpath = "//*[contains(@id,'requestBtns:submitButton')]")
    WebElement submitBtn;
    @FindBy(xpath = "//*[contains(@id,'paramDynForm_ATTRIBUTE12_ATTRIBUTE12::content')]")
    WebElement postInLedger;
    @FindBy(xpath = "//*[contains(@id,'requestBtns:confirmationPopup:pt_ol1')]")
    WebElement processMessage;
    @FindBy(xpath = "//*[contains(@id,'confirmationPopup:confirmSubmitDialog::ok')]")
    WebElement processOk;
    @FindBy(xpath = "//*[@id=\"_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:2:ap1:r1:basicReqBody:paramDynForm_ATTRIBUTE12_ATTRIBUTE12::content\"]/option[2]")
    WebElement yes;
    @FindBy( css= "span[style='font-weight:normal'] label")
    WebElement procId;
    @FindBy( css = "path[d=\"M3 7H25M3 14H25M3 21H25\"]")
    WebElement navigator;

    By pilWait=By.xpath("//*[contains(@id,'paramDynForm_ATTRIBUTE12_ATTRIBUTE12::content')]");
    By yesWait=By.xpath("//*[@id=\"_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:2:ap1:r1:basicReqBody:paramDynForm_ATTRIBUTE12_ATTRIBUTE12::content\"]/option[2]");
    By selectSubledgerWait=By.xpath("//*[contains(@id,'SubledgerApplicationAttr::content')]");
    By navigatorWait=By.cssSelector("path[d=\"M3 7H25M3 14H25M3 21H25\"]");

    public void submitRequest(String ledger) throws InterruptedException {
        waitForElementPresence(selectSubledgerWait);
        selectSubledger.click();
        selectPayables.click();
        selectLedger.sendKeys(ledger);
        selectLedgerKey.sendKeys(Keys.TAB);
        postInLedger.click();
        Thread.sleep(1000);
        yes.click();
        submitBtn.click();
    }

    public String getProcessId()
    {
        String s=procId.getText();
        String processId=s.replaceAll("[^0-9]", "");
        System.out.println(processId);
        return processId;
    }

    public void clickOK()
    {
        processOk.click();
    }

    public void clickNavigator()
    {
        waitForElementPresence(navigatorWait);
         navigator.click();
    }
}
