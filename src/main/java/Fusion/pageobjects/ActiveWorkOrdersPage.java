package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ActiveWorkOrdersPage extends AbstractComponent {

    WebDriver driver;

    public ActiveWorkOrdersPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//div[@class='ms-Stack root-417']//a[@role='link'])[1]")
    WebElement workOrderNumber;

    By workOrderNumberLoc= By.xpath("(//div[@class='ms-Stack root-417']//a[@role='link'])[1]\n");

    public void clickWorkOrderNumber()
    {
        waitForElementClick(workOrderNumberLoc);
        clickElement(workOrderNumberLoc);
    }
}
