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
    By invoiceActionsLoc=By.xpath("//a[contains(text(),'Invoice Actions')]");
    By identifyPoLoc=By.xpath("//*[contains(@id,'ic1::content')]");
    By confirmationWait=By.xpath("//*[contains(@id, 'ap1:cb43')]");
    By saveAndCloseWait=By.xpath("//*[contains(@id, 'ap1:cb14')]");
    By invoiceNumberWait=By.xpath("//*[contains(@id, 'MAnt2:1:pm1:r1:0:ap1:r2:0:i2::content')]");
    By invAmountWait=By.xpath("//*[contains(@id,'ap1:r2:0:i3::content')]");
    By invDateWait=By.xpath("//*[contains(@id,'ap1:r2:0:id2::content')]");
    By pymtTermLoc=By.xpath("//*[contains(@id,'ap1:r2:0:so3::content')]");
    By expandLinesWait=By.xpath("//*[contains(@id, 'ap1:sh2::_afrDscl')]");
    By businessUnitWait=By.xpath("//*[contains(@id,'ic2::content')]");
    By searchWait=By.xpath("//*[contains(@id,'ap1:r2:0:ic3::_afrLovInternalQueryId::search')]");
    By accountingWait=By.linkText("Accounting");
    By accountingDateWait=By.xpath("//*[contains(@id, 'ap1:id4::content')]");
    By linesAmountLoc=By.xpath("//*[contains(@id, 'ATp:ta2:0:i26::content')]");
    By approvalLoc=By.xpath("//tr[contains(@id,'MAnt2:1:pm1:r1:0:ap1:me2')]");

    public void setInvoiceFields(String bUnit,String spl,String invoiceNum,String invAmount,String description,String invoiceDate,String paymentTerms,String termsDate) throws InterruptedException {
        waitForFieldToBeReady(businessUnitWait);
        businessUnit.sendKeys(bUnit);
        selectBU.click();
        supplier.sendKeys(spl);
        search.click();
        select.click();
        ok.click();
        waitForElementToDisappear(searchWait);
        key.sendKeys(Keys.TAB);
        //waitForElementVisible(invoiceNumberWait);
        invoiceNumber.sendKeys(invoiceNum);
        invoiceNumberKey.sendKeys(Keys.TAB);
        Thread.sleep(1000);
        waitForFieldToBeReady(invAmountWait);
        InvAmount.sendKeys(invAmount);
        InvAmount.sendKeys(Keys.TAB);
        desc.sendKeys(description);
        waitForFieldToBeReady(invDateWait);
        invDate.sendKeys(invoiceDate);
        waitForFieldToBeReady(pymtTermLoc);
        pymtTerms.clear();
        pymtTerms.sendKeys(paymentTerms);
        termDate.sendKeys(termsDate);
    }

    public void setAccounting(String accDate,String labilityDistribution)
    {
        waitForElementPresence(show_more);
        showMore.click();
        waitForElementPresence(accountingWait);
        accounting.click();
        waitForFieldToBeReady(accountingDateWait);
        accountingDate.clear();
        accountingDate.sendKeys(accDate);
        liabilityDistribution.clear();
        liabilityDistribution.sendKeys(labilityDistribution);
    }

    public void setLineDetails(String lineAmount,String distributionCombination) throws InterruptedException {
        //Thread.sleep(2000);
        waitForElementPresence(expandLinesWait);
        expandLines.click();
        //linesAmount.sendKeys(lineAmount);
        sendKeysToElement(linesAmountLoc,lineAmount);
        disbCombination.sendKeys(distributionCombination);
    }

    public void invoiceActions() throws InterruptedException {
        waitForElementPresence(invoiceActionsLoc);
         //invoiceActions.click();
        clickElement(invoiceActionsLoc);
         validate.click();
        Thread.sleep(4000);
         invoiceActions.click();
         //approval.click();
        clickElement(approvalLoc);
        Thread.sleep(3000);
         forceApproval.click();
        Thread.sleep(4000);
         invoiceActions.click();
         Thread.sleep(3000);
         postToLedger.click();
    }

    public void validateAndAccountingInvoice() throws InterruptedException {
        waitForElementPresence(invoiceActionsLoc);
        invoiceActions.click();
        validate.click();
        Thread.sleep(4000);
        invoiceActions.click();
        Thread.sleep(3000);
        postToLedger.click();
    }

    public void accountingConfirmation()
    {
        waitForElementPresence(confirmationWait);
        confirmation.click();
        waitForElementPresence(saveAndCloseWait);
        saveAndClose.click();
    }

    public void setInvoiceFieldsWithPO(String po,String invoiceNum,String invAmount,String description,String invoiceDate,String paymentTerms,String termsDate)
    {
        waitForElementPresence(identifyPoLoc);
        //identifyingPO.sendKeys(po);
        sendKeysToElement(identifyPoLoc,po);
        waitForFieldToBeReady(invoiceNumberWait);
        invoiceNumber.sendKeys(invoiceNum);
        invoiceNumberKey.sendKeys(Keys.TAB);
        waitForFieldToBeReady(invAmountWait);
        InvAmount.sendKeys(invAmount);
        desc.sendKeys(description);
        waitForFieldToBeReady(invDateWait);
        invDate.sendKeys(invoiceDate);
        waitForFieldToBeReady(pymtTermLoc);
        pymtTerms.clear();
        //pymtTerms.sendKeys(paymentTerms);
        sendKeysToElement(pymtTermLoc,paymentTerms);
        termDate.sendKeys(termsDate);
    }

    public String getInvoiceNumber() throws InterruptedException {
        String invNum = getInvNum.getDomAttribute("title");
        System.out.println(invNum);
        return invNum;
    }


}
