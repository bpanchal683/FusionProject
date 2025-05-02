package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditPurchaseOrderPage extends AbstractComponent {

    WebDriver driver;

    public EditPurchaseOrderPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@id,'MAt3:0:AP1:AT1:_ATp:edit::icon')]")
    WebElement edit;

    By editLoc=By.xpath("//*[contains(@id,'MAt3:0:AP1:AT1:_ATp:edit::icon')]");

    public PoEditLinePage clickEdit()
    {
        waitForElementPresence(editLoc);
        //edit.click();
        clickElement(editLoc);
        PoEditLinePage poEditLinePage=new PoEditLinePage(driver);
        return poEditLinePage;
    }
}
