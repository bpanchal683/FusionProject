package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateReceiptPage extends AbstractComponent {

    WebDriver driver;

    public CreateReceiptPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@id,'0:MAnt2:3:ap1:it2::content')]")
    WebElement shipment;
    @FindBy(xpath = "//*[contains(@id,'0:MAnt2:3:ap1:it6::content')]")
    WebElement waybill;
    @FindBy(xpath = "//*[contains(@id,'0:MAnt2:3:ap1:it3::content')]")
    WebElement packingSlip;
    @FindBy(xpath = "//*[contains(@id,'0:MAnt2:3:ap1:it7::content')]")
    WebElement billOfLading;
    @FindBy(xpath = "//*[contains(@id,'_FONSr2:0:MAnt2:3:ap1:SPsb2')]")
    WebElement submit;
    @FindBy(css = ".x1o span[style='white-space:nowrap']")
    WebElement reqId;
    @FindBy(xpath = "(//tbody/tr[3]/td[2]/table/tbody/tr[1]/td[1]/button)[9]")
    WebElement ok;

    By reqIdWait=By.cssSelector(".x1o span[style='white-space:nowrap']");
    By shipmentLoc=By.xpath("//*[contains(@id,'0:MAnt2:3:ap1:it2::content')]");
    By submitLoc=By.xpath("//*[contains(@id,'_FONSr2:0:MAnt2:3:ap1:SPsb2')]");

    public void createReceiptAndSubmit(String shpt,String wbill,String packSlip,String billlad)
    {
        waitForElementPresence(shipmentLoc);
        //shipment.sendKeys(shpt);
        sendKeysToElement(shipmentLoc,shpt);
        waybill.sendKeys(wbill);
        packingSlip.sendKeys(packSlip);
        billOfLading.sendKeys(billlad);
        waitForElementPresence(submitLoc);
        //submit.click();
        clickElement(submitLoc);
    }

    public String getReceiptId() throws InterruptedException {
        //Thread.sleep(2000);
        waitForElementVisible(reqIdWait);
        String receipt=reqId.getText();
        String[] receipt1=receipt.split("created");
        String[] receipt2=receipt1[0].split("Receipt");
        String[] receipt3=receipt2[1].split("was");
        String receiptNum=receipt3[0].trim();
        System.out.println(receiptNum);
        ok.click();
        return receiptNum;
    }
}
