package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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

//    @FindBy(xpath = "(//option[contains(text(),'Yes')])[3]")
//    WebElement yes;
    @FindBy(xpath = "//*[@id=\"_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:2:ap1:r1:basicReqBody:paramDynForm_ATTRIBUTE12_ATTRIBUTE12::content\"]/option[2]")
    WebElement yes;

    @FindBy( css= "span[style='font-weight:normal'] label")
    WebElement procId;

    By pilWait=By.xpath("//*[contains(@id,'paramDynForm_ATTRIBUTE12_ATTRIBUTE12::content')]");
    By yesWait=By.xpath("//*[@id=\"_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:2:ap1:r1:basicReqBody:paramDynForm_ATTRIBUTE12_ATTRIBUTE12::content\"]/option[2]");

    public void submitRequest(String ledger) throws InterruptedException {

        selectSubledger.click();
        selectPayables.click();
        selectLedger.sendKeys(ledger);
        selectLedgerKey.sendKeys(Keys.TAB);
        postInLedger.click();
        Thread.sleep(1000);
        yes.click();
        submitBtn.click();
        String s=procId.getText();
        String processId=s.replaceAll("[^0-9]", "");
        System.out.println(processId);
    }
}
