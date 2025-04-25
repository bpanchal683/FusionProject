package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditJournalPage extends AbstractComponent {

    WebDriver driver;

    public EditJournalPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".xem .xen .x2hi a[id*=\"_ATp:t3:0:cl1\"]")
    WebElement amount;

    By amountWait=By.cssSelector(".xem .xen .x2hi a[id*=\"_ATp:t3:0:cl1\"]");

    public void clickAmount()
    {
        waitForElementPresence(amountWait);
        amount.click();
    }
}
