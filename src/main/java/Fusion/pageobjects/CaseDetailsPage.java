package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CaseDetailsPage extends AbstractComponent {

    WebDriver driver;

    public CaseDetailsPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@aria-label='Case Title']")
    WebElement caseTitle;
    @FindBy(xpath = "//input[@aria-label='Customer, Lookup']")
    WebElement custField;
    @FindBy(xpath = "//button[@aria-label='Origin']")
    WebElement originField;
    @FindBy(xpath = "//input[@aria-label='ID']")
    WebElement id;

    By caseTitleLoc=By.xpath("//input[@aria-label='Case Title']");
    By customerFieldLoc=By.xpath("//input[@aria-label='Customer, Lookup']");
    By orignFieldLoc=By.xpath("//button[@aria-label='Origin']");
    By originSelectLoc=By.xpath("//div[@role='listbox']//span[text()='Phone']");
    By contactFieldLoc=By.xpath("//input[@aria-label='Contact, Lookup']");
    By productFieldLoc=By.xpath("//input[@aria-label='Product, Lookup']");
    By saveLoc=By.xpath("//button[@aria-label='Save (CTRL+S)']");
    By idLoc=By.xpath("//input[@aria-label='ID']");
    By fieldServiceLoc=By.xpath("//li[@aria-label='Field Service']");






    public void addCaseDetails(String title,String cust,String contact,String product) throws InterruptedException {
        Actions action=new Actions(driver);

        waitForElementClick(caseTitleLoc);
        clickElement(caseTitleLoc);
        sendKeysToElement(caseTitleLoc,title);
        Thread.sleep(2000);
        clickElement(customerFieldLoc);
        sendKeysToElement(customerFieldLoc,cust);
        Thread.sleep(5000);
        action.sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .build().perform();
        action.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        clickElement(orignFieldLoc);
        action.sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .build().perform();
        Thread.sleep(2000);
        clickElement(contactFieldLoc);
        sendKeysToElement(contactFieldLoc,contact);
        Thread.sleep(2000);
        action.sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .build().perform();
        Thread.sleep(2000);
        clickElement(productFieldLoc);
        sendKeysToElement(productFieldLoc,product);
        Thread.sleep(2000);
        action.sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .build().perform();
    }

    public void clickSave()
    {
        waitForElementClick(saveLoc);
        clickElement(saveLoc);
    }

    public String getCaseId() throws InterruptedException {
        waitForElementClick(idLoc);
        Thread.sleep(8000);
        String caseId=id.getDomAttribute("title");
        System.out.println("Id is:" + caseId);
        return caseId;
    }

    public void clickFieldService()
    {
        waitForElementClick(fieldServiceLoc);
        clickElement(fieldServiceLoc);
    }
}
