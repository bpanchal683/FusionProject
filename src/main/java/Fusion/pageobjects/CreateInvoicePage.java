package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateInvoicePage extends AbstractComponent {

    WebDriver driver;

    public CreateInvoicePage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@id,'ic2::content')]")
    WebElement businessUnit;
    @FindBy(xpath = "//*[contains(@id,'ap1:r2:0:ic3::lovIconId')]")
    WebElement selectBU;
    @FindBy(xpath = "//*[contains(@id,'ap1:r2:0:ic3::_afrLovInternalQueryId:value00::content')]")
    WebElement supplier;
    @FindBy(xpath = "//*[contains(@id,'ap1:r2:0:ic3::_afrLovInternalQueryId::search')]")
    WebElement search;
    @FindBy(className = "xen")
    WebElement select;
    @FindBy(xpath = "//*[contains(@id,'ap1:r2:0:ic3::lovDialogId::ok')]")
    WebElement ok;
    @FindBy(xpath = "//*[contains(@id, 'MAnt2:1:pm1:r1:0:ap1:r2:0:ic3::content')]")
    WebElement key;
    @FindBy(xpath = "//*[contains(@id, 'MAnt2:1:pm1:r1:0:ap1:r2:0:i2::content')]")
    WebElement invoiceNumber;
    @FindBy(xpath = "//*[contains(@id,'ap1:r2:0:i2::content')]")
    WebElement invoiceNumberKey;
    @FindBy(xpath = "//input[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:1:pm1:r1:0:ap1:r2:0:i2::content']")
    WebElement getInvNum;
    @FindBy(xpath = "//*[contains(@id,'ap1:r2:0:i3::content')]")
    WebElement InvAmount;
    @FindBy(xpath = "//*[contains(@id,'ap1:r2:0:i4::content')]")
    WebElement desc;
    @FindBy(xpath = "//*[contains(@id,'ap1:r2:0:id2::content')]")
    WebElement invDate;
    @FindBy(xpath = "//*[contains(@id,'ap1:r2:0:so3::content')]")
    WebElement pymtTerms;
    @FindBy(xpath = "//*[contains(@id,'ap1:r2:0:id5::content')]")
    WebElement termDate;
    @FindBy(xpath = "//*[contains(@id,'ap1:r2:0:id5::content')]")
    WebElement termsDateKey;
    @FindBy(linkText = "Show More")
    WebElement showMore;
    @FindBy(linkText = "Accounting")
    WebElement accounting;
    @FindBy(xpath = "//*[contains(@id, 'ap1:id4::content')]")
    WebElement accountingDate;
    @FindBy(xpath = "//*[contains(@id, 'ap1:kf2CS::content')]")
    WebElement liabilityDistribution;
    @FindBy(xpath = "//*[contains(@id, 'ap1:sh2::_afrDscl')]")
    WebElement expandLines;
    @FindBy(xpath = "//*[contains(@id, 'ATp:ta2:0:i26::content')]")
    WebElement linesAmount;
    @FindBy(xpath = "//*[contains(@id, 'ATp:ta2:0:kf1CS::content')]")
    WebElement disbCombination;
    @FindBy(xpath = "//a[contains(text(),'Invoice Actions')]")
    WebElement invoiceActions;
    @FindBy(xpath = "//td[contains(text(),'Validate')]")
    WebElement validate;
    @FindBy(xpath = "//tr[contains(@id,'MAnt2:1:pm1:r1:0:ap1:me2')]")
    WebElement approval;
    @FindBy(xpath = "//tr[contains(@id,'MAnt2:1:pm1:r1:0:ap1:cm11')]")
    WebElement forceApproval;
    @FindBy(xpath = "//td[contains(text(),'Post to Ledger')]")
    WebElement postToLedger;
    @FindBy(xpath = "//*[contains(@id, 'ap1:cb43')]")
    WebElement confirmation;
    @FindBy(xpath = "//*[contains(@id, 'ap1:cb14')]")
    WebElement saveAndClose;
    @FindBy(xpath = "//*[contains(@id,'ic1::content')]")
    WebElement identifyingPO;



    By show_more=By.linkText("Show More");
    By liabDist=By.xpath("//*[contains(@id, 'ap1:kf2CS::content')]");
    By forceApprovalWait=By.xpath("//tr[contains(@id,'MAnt2:1:pm1:r1:0:ap1:cm11')]");
    By invoiceActionsWait=By.xpath("//a[contains(text(),'Invoice Actions')]");
    By identifyPoWait=By.xpath("//*[contains(@id,'ic1::content')]");
    By confirmationWait=By.xpath("//*[contains(@id, 'ap1:cb43')]");

    public void setInvoiceFields(String bUnit,String spl,String invoiceNum,String invAmount,String description,String invoiceDate,String paymentTerms,String termsDate)
    {
        businessUnit.sendKeys(bUnit);
        selectBU.click();
        supplier.sendKeys(spl);
        search.click();
        select.click();
        ok.click();
        key.sendKeys(Keys.TAB);
        invoiceNumber.sendKeys(invoiceNum);
        invoiceNumberKey.sendKeys(Keys.TAB);
        InvAmount.sendKeys(invAmount);
        desc.sendKeys(description);
        invDate.sendKeys(invoiceDate);
        pymtTerms.clear();
        pymtTerms.sendKeys(paymentTerms);
        termDate.sendKeys(termsDate);
    }

    public void setAccounting(String accDate,String labilityDistribution)
    {
        waitForElementToBeClickable(show_more);
        showMore.click();
        accounting.click();
        accountingDate.clear();
        accountingDate.sendKeys(accDate);
        liabilityDistribution.clear();
        liabilityDistribution.sendKeys(labilityDistribution);
    }

    public void setLineDetails(String lineAmount,String distributionCombination)
    {
        expandLines.click();
        linesAmount.sendKeys(lineAmount);
        disbCombination.sendKeys(distributionCombination);
    }

    public void invoiceActions() throws InterruptedException {
         invoiceActions.click();
         validate.click();
        Thread.sleep(4000);
         invoiceActions.click();
         approval.click();
        Thread.sleep(3000);
         //waitForElementToBeClickable(forceApprovalWait);
         forceApproval.click();
        Thread.sleep(4000);
        //waitForElementToBeClickable(invoiceActionsWait);
         invoiceActions.click();
         Thread.sleep(2000);
         postToLedger.click();
    }

    public void accountingConfirmation()
    {
        waitForElement(confirmationWait);
        confirmation.click();
        saveAndClose.click();
    }

    public void setInvoiceFieldsWithPO(String po,String invoiceNum,String invAmount,String description,String invoiceDate,String paymentTerms,String termsDate)
    {
        waitForElement(identifyPoWait);
        identifyingPO.sendKeys(po);
        invoiceNumber.sendKeys(invoiceNum);
        invoiceNumberKey.sendKeys(Keys.TAB);
        InvAmount.sendKeys(invAmount);
        desc.sendKeys(description);
        invDate.sendKeys(invoiceDate);
        pymtTerms.clear();
        pymtTerms.sendKeys(paymentTerms);
        termDate.sendKeys(termsDate);
    }

    public String getInvoiceNumber() throws InterruptedException {
        String invNum = getInvNum.getDomAttribute("title");
        System.out.println(invNum);
        return invNum;
    }


}
