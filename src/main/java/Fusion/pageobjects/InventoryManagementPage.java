package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryManagementPage extends AbstractComponent {

    WebDriver driver;

    public InventoryManagementPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@id, '_itemNode_InvTasksList::disAcr')]")
    WebElement taskButton;
    @FindBy(xpath = "//*[contains(@id,':_FOTRaT:0:soc1::content')]")
    WebElement showTask;
    @FindBy(xpath = "//option[contains(text(),'Receipts')]")
    WebElement selectReceipt;
    @FindBy(linkText = "Receive Expected Shipments")
    WebElement receiveExpectedShipments;

    By taskButtonWait=By.xpath("//*[contains(@id, '_itemNode_InvTasksList::disAcr')]");
    By showTaskWait=By.xpath("//*[contains(@id,':_FOTRaT:0:soc1::content')]");
    By receiveExpectedShipmentsWait=By.linkText("Receive Expected Shipments");

    public ReceiveExpectedShipmentsPage goToReceiveExpectedShipments()
    {
        waitForElementToBeClickable(taskButtonWait);
        taskButton.click();
        showTask.click();
        selectReceipt.click();
        waitForElementToBeClickable(receiveExpectedShipmentsWait);
        receiveExpectedShipments.click();
        ReceiveExpectedShipmentsPage receiveExpectedShipmentsPage=new ReceiveExpectedShipmentsPage(driver);
        return receiveExpectedShipmentsPage;
    }
}
