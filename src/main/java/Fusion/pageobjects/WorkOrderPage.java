package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WorkOrderPage extends AbstractComponent {

    WebDriver driver;

    public WorkOrderPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//div[@class='mainContent-494']//span[@class='headerText-499']//div[contains(@class, 'ms-TooltipHost')])[1]")
    WebElement workOrderNumber;
    @FindBy(xpath = "//div[@aria-label='Scheduled']")
    WebElement bookingStatus;

    By workOrderNumberLoc=By.xpath("(//div[starts-with(@class, 'detailsContainer-')])[2]");
    By bookingStatusLoc=By.xpath("//input[@aria-label='Booking Status, Lookup']");
    By deleteScheduleLoc=By.xpath("//span[@data-id='bookingstatus.fieldControl-LookupResultsDropdown_bookingstatus_microsoftIcon_cancelButton']");
    By saveLoc=By.xpath("//button[@aria-label='Save (CTRL+S)']");
    By goBackLoc=By.xpath("//button[@aria-label='Press Enter to go back.']");
    By enterNoteLoc=By.xpath("//button[.//span[text()='Enter a note...']]");
    By titleLoc=By.xpath("//input[@aria-label='Create a Note title']");
    By addNoteAndCloseLoc=By.xpath("//button[@id='splitsave-button']");





    public void clickOrderNumber()
    {
        waitForElementClick(workOrderNumberLoc);
        clickElement(workOrderNumberLoc);
    }

    public void changeBookingStatus(String status) throws InterruptedException {
        Actions actions=new Actions(driver);
        clickElement(deleteScheduleLoc);
        Thread.sleep(2000);
        sendKeysToElement(bookingStatusLoc,status);
        Thread.sleep(2000);
        actions.sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER).build().perform();
    }

    public void clickSave()
    {
        waitForElementClick(saveLoc);
        clickElement(saveLoc);
    }

    public void clickBack() throws InterruptedException {
        waitForElementClick(goBackLoc);
        Thread.sleep(1000);
        clickElement(goBackLoc);
    }

    public void addTitleAndClose(String title) throws InterruptedException {
        waitForFieldToBeReady(enterNoteLoc);
        clickElement(enterNoteLoc);
        Thread.sleep(2000);
        sendKeysToElement(titleLoc,title);
        clickElement(addNoteAndCloseLoc);
    }

}
