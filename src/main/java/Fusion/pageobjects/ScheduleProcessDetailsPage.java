package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.Set;

public class ScheduleProcessDetailsPage extends AbstractComponent {

    WebDriver driver;

    public ScheduleProcessDetailsPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@id,'basicReqBody:paramDynForm_BusinessUnit::content')]")
    WebElement businessUnit;

    @FindBy(xpath = "//*[contains(@id,'basicReqBody:paramDynForm_SupplierName::content')]")
    WebElement supplier;

    @FindBy(xpath = "//*[contains(@id,'Form_FromEnteredDate::content')]")
    WebElement fromEnteredDate;

    @FindBy(xpath = "//*[contains(@id,'Form_ToEnteredDate::content')]")
    WebElement toEnteredDate;

    @FindBy(xpath = "//*[contains(@id,'requestBtns:submitButton')]")
    WebElement submit;

    @FindBy(xpath = "//*[contains(@id,'requestBtns:confirmationPopup:pt_ol1')]")
    WebElement confirmationmsg;

    @FindBy(xpath = "//*[contains(@id,'confirmationPopup:confirmSubmitDialog::ok')]")
    WebElement ok;

    @FindBy(xpath = "//*[contains(@id, 'pt1:srRssdfl::_afrDscl')]")
    WebElement expand;

    @FindBy(xpath = "//*[contains(@id,'pt1:srRssdfl:value10::content')]")
    WebElement processNumber;

    @FindBy(xpath = "//*[contains(@id,'_FONSr2:0:_FOTsr1:0:pt1:srRssdfl::search')]")
    WebElement searchButton;

    @FindBy(xpath = "//table[@summary='List of Processes Meeting Search Criteria']")
    WebElement processName;

    @FindBy(css = "td[style=\"width:150px;\"]")
    WebElement status;

    @FindBy(css = ".hdDeliveryInfo2 span[class='icon_cell']")
    WebElement rebulish;

    @FindBy(xpath = "//*[contains(@id,'pt1:panel:scheduleProcess')]")
    WebElement scheduleProcesses;

    @FindBy(xpath = "//*[contains(@id,'selectOneChoice2::content')]")
    WebElement scheduleProcessName;

    @FindBy(xpath = "//*[contains(@id,'FOTsr1:0:pt1:snpokbtnid')]")
    WebElement okButton;

    @FindBy(css = ".hdDeliveryInfo2 span[class='icon_cell']")
    WebElement republish;

    @FindBy(xpath = "//a[@id='reportViewMenu']")
    WebElement settings;

    @FindBy(xpath = "//a[@class='masterMenuItem item']")
    WebElement hoverExport;

    @FindBy(linkText = "PDF")
    WebElement getPdf;

    By processNameWait=By.xpath("//table[@summary='List of Processes Meeting Search Criteria']");
    By scheduleProcessesWait=By.xpath("//*[contains(@id,'pt1:panel:scheduleProcess')]");
    By businessUnitWait=By.xpath("//*[contains(@id,'basicReqBody:paramDynForm_BusinessUnit::content')]");


    public void setScheduleDetails(String buUnit, String supp, String fromDate, String toDate) throws InterruptedException {
        //Thread.sleep(2000);
        waitForElement(businessUnitWait);
        businessUnit.sendKeys(buUnit);
        businessUnit.sendKeys(Keys.TAB);
        Thread.sleep(1000);
        supplier.sendKeys(supp);
        supplier.sendKeys(Keys.TAB);
        Thread.sleep(1000);
        fromEnteredDate.sendKeys(fromDate);
        toEnteredDate.sendKeys(toDate);
        submit.click();

    }


    public String getProcessId() throws InterruptedException {
        Thread.sleep(2000);
        String Process = confirmationmsg.getText();
        String[] Process1 = Process.split("submitted");
        String[] Process2 = Process1[0].split("Process");
        String[] Process3 = Process2[1].split("was");
        String processNum = Process3[0].trim();
        System.out.println(processNum);
        Thread.sleep(1000);
        ok.click();
        return processNum;

    }

    public void searchNumber(String processNum) throws InterruptedException {
        expand.click();
        processNumber.sendKeys(processNum);
        searchButton.click();
        Thread.sleep(8000);

    }

    public void statusSucceded() throws InterruptedException {
        String s2 = status.getText();
        System.out.println(s2);
        if (s2.equalsIgnoreCase("Succeeded")) {
            // Code to execute if condition is true
            System.out.println(s2);
            System.out.println("Succeeded");
        } else {

            // Code to execute if condition is false
            while (!s2.equalsIgnoreCase("Succeeded")) {
                searchButton.click();
                System.out.println("refreshing the search");
                Thread.sleep(1000);
                s2 = status.getText();
            }
        }
    }

    public void setScheduleProcesses(String scheProcessName) throws InterruptedException {
        //Thread.sleep(2000);
        waitForElementToBeClickable(scheduleProcessesWait);
        scheduleProcesses.click();
        Thread.sleep(2000);
        scheduleProcessName.sendKeys(scheProcessName);
        scheduleProcessName.sendKeys(Keys.TAB);
        Thread.sleep(2000);
        okButton.click();
    }

    public void clickRepbulish() {
        waitForElementToBeClickable(processNameWait);
        processName.click();
        WebElement webFrame= driver.findElement( By.id ("_FOpt1:_FOr1:0:_FONSr2:0:_FOTsr1:0:pt1:processDetails:processDetails:r61:0:if1"));
        driver.switchTo().frame(webFrame);
        republish.click();

    }

    public void getPdf() throws InterruptedException {
        Set<String> windows=driver.getWindowHandles();
        Iterator<String> it=windows.iterator();
        String parentId=it.next();
        String childId=it.next();
        driver.switchTo().window(childId);
        Thread.sleep(6000);
        settings.click();
        WebElement export =hoverExport;
        Actions actions = new Actions(driver);
        actions.moveToElement(export).perform();
        getPdf.click();


    }
}
