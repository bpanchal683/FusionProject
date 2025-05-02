package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReceiveLinesPage extends AbstractComponent {

    WebDriver driver;

    public ReceiveLinesPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@id,'AT1:_ATp:table1:0:Quantityid::content')]")
    WebElement quantity;
    @FindBy(xpath = "//*[contains(@id,'AT1:_ATp:table1:0:subinventoryId::content')]")
    WebElement subinventory;
    @FindBy(xpath = "//*[contains(@id,'_FONSr2:0:MAnt2:2:appPanelid:cb3')]")
    WebElement submit;
    @FindBy(xpath = "(//tbody/tr[3]/td[2]/table/tbody/tr[1]/td[1]/button)[3]")
    WebElement warning;

    By quanityLoc=By.xpath("//*[contains(@id,'AT1:_ATp:table1:0:Quantityid::content')]");
    By warningLoc=By.xpath("(//tbody/tr[3]/td[2]/table/tbody/tr[1]/td[1]/button)[3]");
    By submitLoc=By.xpath("//*[contains(@id,'_FONSr2:0:MAnt2:2:appPanelid:cb3')]");

    public CreateReceiptPage performReceiveLinesOperation(String qty, String subInv)
    {
        //waitForElementPresence(quanityWait);
        waitForFieldToBeReady(quanityLoc);
        //quantity.sendKeys(qty);
        sendKeysToElement(quanityLoc,qty);
        subinventory.sendKeys(subInv);
        submit.click();
        waitForElementPresence(warningLoc);
        //warning.click();
        clickElement(warningLoc);
        //submit.click();
        clickElement(submitLoc);
        CreateReceiptPage createReceiptPage=new CreateReceiptPage(driver);
        return createReceiptPage;
    }

}
