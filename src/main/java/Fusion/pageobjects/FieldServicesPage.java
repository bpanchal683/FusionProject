package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FieldServicesPage extends AbstractComponent {

    WebDriver driver;

    public FieldServicesPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(text(),'Cases')]")
    WebElement cases;
    @FindBy(id = "defaultDialogChromeCloseIconButton-1")
    WebElement closeTab;


    By casesLoc=By.xpath("//*[contains(text(),'Cases')]");
    By closeTabLoc=By.xpath("//div[contains(@class, '___174bz0o')]//button[text()='Dismiss']");
    By dismissLoc=By.cssSelector(".fui-FluentProvider.___174bz0o");
    By newCaseLoc=By.xpath("//span[contains(text(),'New Case')]");

    public void closeTab() throws InterruptedException {
        Thread.sleep(10000);
        //waitForElementPresence(closeTabLoc);
        //clickElement(closeTabLoc);
        //jsClick(closeTabLoc);
    }

    public void clickCases()
    {
        waitForElementClick(casesLoc);
        clickElement(casesLoc);
    }

    public void createNewCase()
    {
        waitForElementClick(newCaseLoc);
        clickElement(newCaseLoc);
    }
}
