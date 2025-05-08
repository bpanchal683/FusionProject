package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;

public class ScheduleBoardPage extends AbstractComponent {

    WebDriver driver;

    public ScheduleBoardPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@aria-label='Incident Type, Lookup']")
    WebElement incidentType;

    By bookLoc=By.xpath("//button[@title='Manually choose a requirement and a resource to create a booking']");
    By requirementLoc=By.xpath("//input[@aria-label='Requirement']");
    By resourceLoc=By.xpath("//input[@aria-label='Resource']");
    By startTimeLoc=By.xpath("//input[@id='ComboBox941-input']");
    By endTimeLoc=By.xpath("//input[@id='ComboBox941-input']");

    public void clickBook() throws InterruptedException {
        WebElement webFrame=driver.findElement(By.id("ScheduleBoardFrame"));
        driver.switchTo().frame(webFrame);
        waitForElementClick(bookLoc);
        Thread.sleep(8000);
        clickElement(bookLoc);
    }

    public void enterCreateBookingDeatails(String resc,String req,String sttime,String etime) throws InterruptedException {
        Actions actions=new Actions(driver);
        waitForFieldToBeReady(resourceLoc);
        sendKeysToElement(requirementLoc,resc);
        Thread.sleep(2000);
        actions.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        sendKeysToElement(resourceLoc,req);
        Thread.sleep(2000);
        actions.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        sendKeysToElement(startTimeLoc,sttime);
        Thread.sleep(1000);
        sendKeysToElement(endTimeLoc,etime);
    }
}
