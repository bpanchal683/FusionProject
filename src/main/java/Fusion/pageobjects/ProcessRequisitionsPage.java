package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProcessRequisitionsPage extends AbstractComponent {

    WebDriver driver;

    public ProcessRequisitionsPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@id,'pt1:r1:0:AP1:q1:value00::content')]")
    WebElement requsitionBu;
    @FindBy(xpath = "//option[contains(text(),'IT Convergence Singapore')]")
    WebElement requsitionBuSelect;
    @FindBy(xpath = "//*[contains(@id,'pt1:r1:0:AP1:q1:value10::content')]")
    WebElement requsition;
    @FindBy(xpath = "//*[contains(@id,'pt1:r1:0:AP1:q1:value20::content')]")
    WebElement buyer;
    @FindBy(xpath = "//*[contains(@id,'pt1:r1:0:AP1:q1::search')]")
    WebElement search;
    @FindBy(xpath = "(//td[@class='xen'])[4]")
    WebElement reqLine;
    @FindBy(xpath = "(//button[normalize-space()='Add to Document Builder'])[1]")
    WebElement documentBuilder;
    @FindBy(xpath = "//*[contains(@id,'r1:0:AP1:AT5:supplier1Id::content')]")
    WebElement supplier;
    @FindBy(xpath = "//*[contains(@id,'r1:0:AP1:AT5:supplier1Id::content')]")
    WebElement supplierTab;
    @FindBy(xpath = "//button[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAt2:0:pt1:r1:0:AP1:AT5:cb1']")
    WebElement ok;
    @FindBy(xpath = "//*[contains(@id,'MAt2:0:pt1:DocBF1:0:cb2')]")
    WebElement create;
    @FindBy(css = ".x1n3 .x1mw")
    WebElement poId;
    @FindBy(xpath = "//button[@id='_FOd1::msgDlg::cancel']")
    WebElement okk;
    @FindBy(xpath = "//*[contains(@id, 'FndTasksList::icon')]")
    WebElement taskButton;
    @FindBy(linkText = "Manage Orders")
    WebElement manageOrders;

    By reqLineLoc=By.xpath("(//td[@class='xen'])[4]");
    By documentBuilderLoc=By.xpath("(//button[normalize-space()='Add to Document Builder'])[1]");
    By createLoc=By.xpath("//*[contains(@id,'MAt2:0:pt1:DocBF1:0:cb2')]");
    By poIdWait=By.cssSelector(".x1n3 .x1mw");
    By taskButtonLoc=By.xpath("//*[contains(@id, 'FndTasksList::icon')]");
    By searchLoc=By.xpath("//*[contains(@id,'pt1:r1:0:AP1:q1::search')]");
    By requisitionsBuLoc=By.xpath("//*[contains(@id,'pt1:r1:0:AP1:q1:value00::content')]");
    By manageOrdersLoc=By.linkText("Manage Orders");
    By supplierLoc=By.xpath("//*[contains(@id,'r1:0:AP1:AT5:supplier1Id::content')]");
    By requisitionLoc=By.xpath("//*[contains(@id,'pt1:r1:0:AP1:q1:value10::content')]");
    By okkLoc=By.xpath("//button[@id='_FOd1::msgDlg::cancel']");


    public void searchRequisitionAndAddToDocumentBuilder(String req,String supl) throws InterruptedException {
        waitForElementPresence(requisitionsBuLoc);
        //requsitionBu.click();
        clickElement(requisitionsBuLoc);
        requsitionBuSelect.click();
        //requsition.sendKeys(req);
        sendKeysToElement(requisitionLoc,req);
        buyer.clear();
        Thread.sleep(3000);
        waitForElementPresence(searchLoc);
        //search.click();
        clickElement(searchLoc);
        waitForElementPresence(reqLineLoc);
        //reqLine.click();
        clickElement(reqLineLoc);
        Thread.sleep(2000);
        waitForElementVisible(documentBuilderLoc);
        //documentBuilder.click();
        clickElement(documentBuilderLoc);
        waitForElementPresence(supplierLoc);
        //supplier.sendKeys(supl);
        sendKeysToElement(supplierLoc,supl);
        supplierTab.sendKeys(Keys.TAB);
        ok.click();
        clickStaleElement(createLoc);
    }

    public String getPurchaseOrderId() throws InterruptedException {
        //waitForElementPresence(poIdWait);
        waitForElementVisible(poIdWait);
        Thread.sleep(2000);
        String purchase=poId.getText();
        String[] purchase1=purchase.split("was");
        String[] purchase2=purchase1[0].split("(Purchase Order)");
        String[] purchase3=purchase2[1].split(" ");
        String poNum=purchase3[1];
        System.out.println(poNum);
        return poNum;
    }

    public EditPurchaseOrderPage clickOk()
    {
        //okk.click();
        clickElement(okkLoc);
        EditPurchaseOrderPage editPurchaseOrderPage=new EditPurchaseOrderPage(driver);
        return editPurchaseOrderPage;
    }

    public ManageOrdersPage goToManageOrders()
    {
        waitForElementPresence(taskButtonLoc);
        //taskButton.click();
        clickElement(taskButtonLoc);
        waitForElementPresence(manageOrdersLoc);
        //manageOrders.click();
        clickElement(manageOrdersLoc);
        ManageOrdersPage manageOrdersPage=new ManageOrdersPage(driver);
        return manageOrdersPage;
    }
}
