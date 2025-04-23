package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchaseOrderPage extends AbstractComponent {

    WebDriver driver;

    public PurchaseOrderPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@id, 'FndTasksList::icon')]")
    WebElement taskButton;
    @FindBy(linkText = "Process Requisitions")
    WebElement processRequsition;

    By taskButtonWait=By.xpath("//*[contains(@id, 'FndTasksList::icon')]");
    By processRequisitionWait=By.linkText("Process Requisitions");

    public ProcessRequisitionsPage goToProcessRequsition()
    {
        waitForElementPresence(taskButtonWait);
        taskButton.click();
        waitForElementPresence(processRequisitionWait);
        processRequsition.click();
        ProcessRequisitionsPage processRequisitionsPage=new ProcessRequisitionsPage(driver);
        return processRequisitionsPage;
    }
}
