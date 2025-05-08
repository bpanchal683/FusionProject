package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.Set;

public class WorkOrderNumberPage extends AbstractComponent {

    String parentId;
    WebDriver driver;

    public WorkOrderNumberPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@aria-label='Work Order Number']")
    WebElement won;

    By wonLoc=By.xpath("//input[@aria-label='Work Order Number']");
    By settingsLoc=By.xpath("//li[@aria-label='Settings']");
    By timeFromLoc=By.xpath("//input[@aria-label='Date of Time From Promised']");
    By timeToLoc=By.xpath("//input[@aria-label='Date of Time To Promised']");
    By saveAndCloseLoc=By.xpath("//button[@aria-label='Save & Close']");




    public String getWorkOrderNumber()
    {
        Set<String> windows=driver.getWindowHandles();
        Iterator<String> it=windows.iterator();
        parentId=it.next();
        String childId=it.next();
        driver.switchTo().window(childId);
        waitForElementVisible(wonLoc);
        String woNum=won.getDomAttribute("title");
        System.out.println("won is:" + woNum);
        return woNum;
    }

    public void clickSettings()
    {
        waitForElementClick(settingsLoc);
        clickElement(settingsLoc);
    }

    public void EnterPromisedTimeDetails(String timefrom,String timeto) throws InterruptedException {
        waitForFieldToBeReady(timeFromLoc);
        sendKeysToElement(timeFromLoc,timefrom);
        Thread.sleep(2000);
        sendKeysToElement(timeToLoc,timeto);
        Thread.sleep(2000);
    }

    public void clickSaveAndClose() throws InterruptedException {
        waitForElementClick(saveAndCloseLoc);
        clickElement(saveAndCloseLoc);
        driver.switchTo().window(parentId);
    }
}
