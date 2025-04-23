package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReceiveExpectedShipmentsPage extends AbstractComponent {

    WebDriver driver;

    public ReceiveExpectedShipmentsPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@id,'pt1:ap1:rcvQry:value00::content')]")
    WebElement purchaseOrder;
    @FindBy(xpath = "//*[contains(@id,'1:pt1:ap1:rcvQry::search')]")
    WebElement search;
    @FindBy(css = ".xen:first-child")
    WebElement poLine;
    @FindBy(xpath = "//*[contains(@id,'1:pt1:ap1:AT1:_ATp:receive')]")
    WebElement receive;

    By purchaseOrderWait=By.xpath("//*[contains(@id,'pt1:ap1:rcvQry:value00::content')]");
    By searchWait=By.xpath("//*[contains(@id,'1:pt1:ap1:rcvQry::search')]");
    By poLineWait=By.cssSelector(".xen:first-child");
    By receiveWait=By.xpath("//*[contains(@id,'1:pt1:ap1:AT1:_ATp:receive')]");

    public ReceiveLinesPage searchAndGoToReceiveLines(String poNum)
    {
         waitForElementPresence(purchaseOrderWait);
         purchaseOrder.sendKeys(poNum);
         waitForElementPresence(searchWait);
         search.click();
         waitForElementPresence(poLineWait);
         poLine.click();
         waitForElementPresence(receiveWait);
         receive.click();
        ReceiveLinesPage receiveLinesPage=new ReceiveLinesPage(driver);
        return receiveLinesPage;
    }
}
