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

    By quanityWait=By.xpath("//*[contains(@id,'AT1:_ATp:table1:0:Quantityid::content')]");

    public void performReceiveLinesOperation(String qty,String subInv)
    {
        waitForElement(quanityWait);
        quantity.sendKeys(qty);
        subinventory.sendKeys(subInv);
        submit.click();
        warning.click();
        submit.click();
    }

}
