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

    By reqLineWait=By.xpath("(//td[@class='xen'])[4]");
    By documentBuilderWait=By.xpath("(//button[normalize-space()='Add to Document Builder'])[1]");
    By createLoc=By.xpath("//*[contains(@id,'MAt2:0:pt1:DocBF1:0:cb2')]");
    By poIdWait=By.cssSelector(".x1n3 .x1mw");
    By taskButtonWait=By.xpath("//*[contains(@id, 'FndTasksList::icon')]");
    By searchWait=By.xpath("//*[contains(@id,'pt1:r1:0:AP1:q1::search')]");
    By requisitionsBuWait=By.xpath("//*[contains(@id,'pt1:r1:0:AP1:q1:value00::content')]");
    By manageOrdersWait=By.linkText("Manage Orders");
    By supplierWait=By.xpath("//*[contains(@id,'r1:0:AP1:AT5:supplier1Id::content')]");


    public void searchRequisitionAndAddToDocumentBuilder(String req,String supl) throws InterruptedException {
        waitForElementPresence(requisitionsBuWait);
        requsitionBu.click();
        requsitionBuSelect.click();
        requsition.sendKeys(req);
        buyer.clear();
        Thread.sleep(3000);
        waitForElementPresence(searchWait);
        search.click();
        waitForElementPresence(reqLineWait);
        reqLine.click();
        Thread.sleep(2000);
        waitForElementVisible(documentBuilderWait);
        documentBuilder.click();
        waitForElementPresence(supplierWait);
        supplier.sendKeys(supl);
        supplierTab.sendKeys(Keys.TAB);
        ok.click();
        clickElement(createLoc);
    }

    public String getPurchaseOrderId() throws InterruptedException {
        //waitForElementPresence(poIdWait);
        waitForElementVisible(poIdWait);
        //Thread.sleep(2000);
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
        okk.click();
        EditPurchaseOrderPage editPurchaseOrderPage=new EditPurchaseOrderPage(driver);
        return editPurchaseOrderPage;
    }

    public ManageOrdersPage goToManageOrders()
    {
        waitForElementPresence(taskButtonWait);
        taskButton.click();
        waitForElementPresence(manageOrdersWait);
        manageOrders.click();
        ManageOrdersPage manageOrdersPage=new ManageOrdersPage(driver);
        return manageOrdersPage;
    }
}
