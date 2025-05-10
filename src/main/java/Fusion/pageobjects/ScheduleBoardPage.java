package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


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
    @FindBy(xpath = "//label[text()='Start Time']/following::input[1]")
    WebElement startTime;
    @FindBy(xpath = "//label[text()='End Time']/following::input[1]")
    WebElement endTime;

    By bookLoc=By.xpath("//button[@title='Manually choose a requirement and a resource to create a booking']");
    By requirementLoc=By.xpath("//input[@aria-label='Requirement']");
    By resourceLoc=By.xpath("//input[@aria-label='Resource']");
    By startTimeLoc=By.xpath("//label[text()='Start Time']/following::input[1]");
    By endTimeLoc=By.xpath("//label[text()='End Time']/following::input[1]");
    By bookOrderLoc= By.cssSelector("button[class='ms-Button ms-Button--primary root-675'] span[class='ms-Button-flexContainer flexContainer-272']");
    By workOrderLoc=By.xpath("//li[@aria-label='Work Orders']");


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
        actions.sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(2000);
        sendKeysToElement(resourceLoc,req);
        Thread.sleep(2000);
        actions.sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(2000);
        clickElement(startTimeLoc);
        startTime.clear();
        Thread.sleep(1000);
        sendKeysToElement(startTimeLoc,sttime);
        Thread.sleep(2000);
        clickElement(endTimeLoc);
        endTime.clear();
        Thread.sleep(1000);
        sendKeysToElement(endTimeLoc,etime);
    }

    public void bookOrder()
    {
         waitForElementClick(bookOrderLoc);
         clickElement(bookOrderLoc);
    }

    public void clickWorkOrder()
    {
        waitForElementClick(workOrderLoc);
        clickElement(workOrderLoc);
    }
}
