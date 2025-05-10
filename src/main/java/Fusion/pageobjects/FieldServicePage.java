package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FieldServicePage extends AbstractComponent {

    WebDriver driver;

    public FieldServicePage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@aria-label='Incident Type, Lookup']")
    WebElement incidentType;

    By incidentTypeLoc=By.xpath("//input[@aria-label='Incident Type, Lookup']");
    By functionalLocationLoc=By.xpath("//input[@aria-label='Functional Location, Lookup']");
    By saveLoc=By.xpath("//button[@aria-label='Save (CTRL+S)']");
    By convertToWorkOrderLoc=By.xpath("//button[@aria-label='Convert to Work Order']");
    By okLoc=By.xpath("//button[@title='OK']");
    By scheduleBoardLoc=By.xpath("//li[@aria-label='Schedule Board']");
    By continueAnywayLoc=By.xpath("//button[@title='Continue anyway']");






    public void addFieldServiceDetails(String inc,String funcLoc) throws InterruptedException {
        Actions actions=new Actions(driver);
        waitForFieldToBeReady(incidentTypeLoc);
        clickElement(incidentTypeLoc);
        sendKeysToElement(incidentTypeLoc,inc);
        Thread.sleep(2000);
        actions.sendKeys(Keys.ARROW_DOWN)
                        .sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(1000);
        clickElement(functionalLocationLoc);
        sendKeysToElement(functionalLocationLoc,funcLoc);
        Thread.sleep(2000);
        actions.sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER).build().perform();
    }

    public void clickSave()
    {
        waitForElementClick(saveLoc);
        clickElement(saveLoc);
    }

    public void clickConvertToWorkOrder()
    {
        waitForElementClick(convertToWorkOrderLoc);
        clickElement(convertToWorkOrderLoc);
    }

    public void clickOk()
    {
        waitForElementClick(okLoc);
        clickElement(okLoc);
    }

    public void clickScheduleBoard()
    {
        waitForElementClick(scheduleBoardLoc);
        clickElement(scheduleBoardLoc);
    }

    public void clickContinueAnyway()
    {
        waitForElementClick(continueAnywayLoc);
        clickElement(continueAnywayLoc);
    }



}
