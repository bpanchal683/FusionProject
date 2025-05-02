package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PoEditLinePage extends AbstractComponent {

    WebDriver driver;

    public PoEditLinePage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@id,'LPan:DestinationType::content')]")
    WebElement destination;
    @FindBy(xpath = "//option[contains(text(),'Inventory')]")
    WebElement destinationSelect;
    @FindBy(xpath = "//*[contains(@id,'LPan:MatchApprovalLevel::content')]")
    WebElement matchApproval;
    @FindBy(xpath = "//option[contains(text(),'3 Way')]")
    WebElement matchApprovalSelect;
    @FindBy(css = ".callToActionSubmit")
    WebElement ok;
    @FindBy(xpath = "(//a[@role='button'])[4]")
    WebElement save;
    @FindBy(css = ".x1id .callToActionSubmit")
    WebElement submit;
    @FindBy(xpath = "//button[@id='_FOd1::msgDlg::cancel']")
    WebElement poOk;

    By saveLoc=By.xpath("(//a[@role='button'])[4]");
    By okLoc=By.cssSelector(".x1id .callToActionSubmit");
    By destinationLoc=By.xpath("//*[contains(@id,'LPan:DestinationType::content')]");
    By matchApprovalLoc=By.xpath("//*[contains(@id,'LPan:MatchApprovalLevel::content')]");
    By submitLoc=By.cssSelector(".x1id .callToActionSubmit");
    By poOkLoc=By.xpath("//button[@id='_FOd1::msgDlg::cancel']");

    public void poLineOperations() throws InterruptedException {
        waitForElementPresence(destinationLoc);
        //destination.click();
        clickElement(destinationLoc);
        destinationSelect.click();
        //matchApproval.click();
        clickElement(matchApprovalLoc);
        matchApprovalSelect.click();
        Thread.sleep(2000);
        waitForElementPresence(okLoc);
        //ok.click();
        clickElement(okLoc);
        clickStaleElement(saveLoc);
        //submit.click();
        clickElement(submitLoc);
        Thread.sleep(2000);
        //poOk.click();
        clickElement(poOkLoc);
    }

}
