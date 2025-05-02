package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractComponent {

    WebDriver driver;

    public HomePage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "pt1:_UISmmLink::icon")
    WebElement navigator;
    @FindBy(xpath = "//*[contains(@id, '_UIShome')]")
    WebElement home;

    By navigatorLoc=By.id("pt1:_UISmmLink::icon");
    By homeLoc=By.xpath("//*[contains(@id, '_UIShome')]");

    public NavigatorPage clickNavigator() throws InterruptedException {
        clickStaleElement(navigatorLoc);
        NavigatorPage navigatorPage=new NavigatorPage(driver);
        return navigatorPage;
    }

    public void clickHome()
    {
        waitForElementPresence(homeLoc);
        //home.click();
        clickElement(homeLoc);
    }
}
