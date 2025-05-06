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

    By casesLoc=By.xpath("//*[contains(text(),'Cases')]");

    public void clickCases()
    {
        waitForElementClick(casesLoc);
        clickElement(casesLoc);
    }
}
