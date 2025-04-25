package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JournalsPage extends AbstractComponent {

    WebDriver driver;

    public JournalsPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@id, 'FndTasksList::icon')]")
    WebElement taskButton;
    @FindBy(linkText = "Manage Journals")
    WebElement manageJournals;

    By taskButtonWait=By.xpath("//*[contains(@id, 'FndTasksList::icon')]");
    By manageJournalsWait=By.linkText("Manage Journals");

    public ManageJournalsPage goToManageJournals()
    {
          waitForElementPresence(taskButtonWait);
          taskButton.click();
          waitForElementPresence(manageJournalsWait);
          manageJournals.click();
          ManageJournalsPage manageJournalsPage=new ManageJournalsPage(driver);
          return manageJournalsPage;
    }


}
