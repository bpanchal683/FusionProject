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
    By bookingStatusLoc=By.xpath("//div[@aria-label='Scheduled']");
    By deleteScheduleLoc=By.xpath("//div[@aria-label='Delete Scheduled']");
    By saveLoc=By.xpath("//button[@aria-label='Save (CTRL+S)']");
    By goBackLoc=By.xpath("//button[@aria-label='Press Enter to go back.']");
    By titleLoc=By.xpath("//div[@aria-label='Create a Note title']");
    By addNoteAndCloseLoc=By.xpath("//button[@id='splitsave-button']");




    public void clickOrderNumber()
    {
        waitForElementClick(workOrderNumberLoc);
        clickElement(workOrderNumberLoc);
    }

    public void changeBookingStatus(String status)
    {
        Actions actions=new Actions(driver);
        clickElement(deleteScheduleLoc);
        sendKeysToElement(bookingStatusLoc,status);
        actions.sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER).build().perform();
    }

    public void clickSave()
    {
        waitForElementClick(saveLoc);
        clickElement(saveLoc);
    }

    public void clickBack()
    {
        waitForElementClick(goBackLoc);
        clickElement(goBackLoc);
    }

    public void addTitleAndClose(String title)
    {
        waitForFieldToBeReady(titleLoc);
        sendKeysToElement(titleLoc,title);
        clickElement(addNoteAndCloseLoc);
    }

}
