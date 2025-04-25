package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageJournalsPage extends AbstractComponent {

    WebDriver driver;

    public ManageJournalsPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:1:pt1:ap1:queryP:value40::content']")
    WebElement category;
    @FindBy(xpath = "//button[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:1:pt1:ap1:queryP::search']")
    WebElement search;
    @FindBy(css = ".x1hx span[id*=\"_column18\"]")
    WebElement clickED;
    @FindBy(css = ".x1hx span[id*=\"_column18\"] input[id*=\"_column18::content\"]")
    WebElement enterED;
    @FindBy(css = ".xhs a[role='button']")
    WebElement expand;
    @FindBy(css = ".xen .x2hi a[id*=\"commandLink3\"]")
    WebElement paymentLink;

    By categoryWait=By.xpath("//input[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:1:pt1:ap1:queryP:value40::content']");
    By clickEDWait=By.cssSelector(".x1hx span[id*=\"_column18\"] input[id*=\"_column18::content\"]");
    By searchWait=By.xpath("//button[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:1:pt1:ap1:queryP::search']");
    By paymentLinkWait=By.cssSelector(".xen .x2hi a[id*=\"commandLink3\"]");

    public EditJournalPage searchPayment(String cat, String amount)
    {
        waitForElementPresence(categoryWait);
        category.sendKeys(cat);
        search.click();
        waitForElementPresence(clickEDWait);
        clickED.click();
        enterED.sendKeys(amount);
        expand.click();
        waitForElementPresence(searchWait);
        search.click();
        waitForElementPresence(paymentLinkWait);
        paymentLink.click();
        EditJournalPage editJournalPage=new EditJournalPage(driver);
        return editJournalPage;
    }
}
