package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AppsPage extends AbstractComponent {

    WebDriver driver;

    public AppsPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[contains(@href, 'msdyn_FieldService')]")
    WebElement fieldService;


    By fieldServiceLoc=By.xpath("//a[contains(@href, 'msdyn_FieldService')]");

    public void clickFieldService()
    {
        driver.switchTo().frame("AppLandingPage");
        waitForElementVisible(fieldServiceLoc);
        clickElement(fieldServiceLoc);
    }
}
