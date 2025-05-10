package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyActiveCasesPage extends AbstractComponent {

    WebDriver driver;

    public MyActiveCasesPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@aria-label='Case Filter by keyword']")
    WebElement filterByKeyword;

    By filterByKeywordLoc=By.xpath("//input[@aria-label='Case Filter by keyword']");
    By caseLinkLoc=By.xpath("(//div[@role='gridcell' and @col-id='title']//a[@role='link'])[1]");
    By enterNoteLoc=By.xpath("//input[@aria-label='Enter a note...']");
    By enterNoteTitleLoc=By.xpath("//input[@aria-label='Create a Note title']");
    By addNoteAndCloseLoc=By.xpath("//button[@id='splitsave-button']");
    By resolveCaseLoc=By.xpath("//button[@aria-label='Resolve Case']");
    By resolutionLoc=By.cssSelector(".pa-sa .___1xu2kux .fui-Input");
    By enterResolutionLoc=By.xpath("//input[@aria-label='Resolution']");
    By resolveLoc=By.xpath("//button[@id='id-602fcc01-dd62-4886-8f07-042ed67ae299-8']");
    By profileLoc=By.xpath("//div[@id='mectrl_headerPicture']");
    By signOutLoc=By.xpath("//button[@id='mectrl_body_signOut']");





    public void filterCaseByKeyword(String caseId)
    {
        Actions actions=new Actions(driver);
        waitForFieldToBeReady(filterByKeywordLoc);
        sendKeysToElement(filterByKeywordLoc,caseId);
        actions.sendKeys(Keys.ENTER);
    }

    public void clickCaseTitle()
    {
        waitForElementClick(caseLinkLoc);
        clickElement(caseLinkLoc);
    }

    public void clickEnterNote()
    {
        waitForElementClick(enterNoteLoc);
        clickElement(enterNoteLoc);
    }

    public void enterNoteTitleAndClose(String title)
    {
        waitForElementClick(enterNoteTitleLoc);
        clickElement(enterNoteTitleLoc);
        sendKeysToElement(enterNoteTitleLoc,title);
        clickElement(addNoteAndCloseLoc);
    }

    public void clickResolveCase()
    {
        waitForElementClick(resolveCaseLoc);
        clickElement(resolveCaseLoc);
    }

    public void resolveCase(String res)
    {
        waitForElementClick(resolutionLoc);
        clickElement(resolutionLoc);
        sendKeysToElement(enterResolutionLoc,res);
        clickElement(resolveLoc);
    }

    public void logOut()
    {
        waitForElementClick(profileLoc);
        clickElement(profileLoc);
        waitForElementClick(signOutLoc);
        clickElement(signOutLoc);
    }



}
