package Fusion.pageobjects;
import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class PersonManagement extends AbstractComponent {

    WebDriver driver;
    WebDriverWait wait;

    By name = By.xpath("//label[normalize-space()='Name']/following::input[1]");
    By clientGroupButton = By.xpath("//a[normalize-space()='My Client Groups']");
    By searchButton = By.xpath("//button[.='Search' or @title='Search']");
    By homeButton = By.xpath("//a[@title='Home']");
    By personManage = By.xpath("//div[@title='Person Management']");
    By firstRecord = By.xpath("(//table[contains(@summary,'Search Results') or contains(@class,'x2pn')]//tr[1]//a)[1]");
    By tasksButton = By.xpath("//a[.//img[@alt='Tasks']]");
    By userAccount = By.xpath("//a[normalize-space()='User Account']");
    By username = By.xpath(
            "((//span[@title and string-length(normalize-space(@title))>0])[5] "
                    + "| (//span[@title])[5] "
                    + "| (//*[normalize-space(text())='User Name']/following::span[@title][1]))[1]"
    );
    By closeBtn = By.xpath("//button[@title='Close person management']");
    By userSearchInput = By.xpath("//input[contains(@placeholder,'3 or more characters')]");
    By getSearchButton = By.xpath("//a[@title='Search']");
    By userLink = By.xpath("//table[contains(@summary,'Users')]//a[contains(text(),'test')]");
    By doneButton = By.xpath("//button[@title='Done']");

    public PersonManagement(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public String searchByName(String namevalue) throws InterruptedException {
        clickElement(homeButton);
        //wait.until(ExpectedConditions.elementToBeClickable(homeButton)).click();
        Thread.sleep(2000);
        clickElement(clientGroupButton);
        //wait.until(ExpectedConditions.elementToBeClickable(clientGroupButton)).click();
        Thread.sleep(2000);
        clickElement(personManage);
        //wait.until(ExpectedConditions.elementToBeClickable(personManage)).click();
        Thread.sleep(2000);
        sendKeysToElement(name,namevalue);
//        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(name));
//
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//
//        js.executeScript(
//                "arguments[0].value = arguments[1];" +
//                        "arguments[0].dispatchEvent(new Event('input')); " +
//                        "arguments[0].dispatchEvent(new Event('change')); " +
//                        "arguments[0].dispatchEvent(new Event('blur'));",
//                nameInput, "dummy22"
//        );
        Thread.sleep(2000);
        clickElement(searchButton);
        //wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        Thread.sleep(5000);
        clickElement(firstRecord);
        //wait.until(ExpectedConditions.elementToBeClickable(firstRecord)).click();
        Thread.sleep(4000);
        clickElement(tasksButton);
        //wait.until(ExpectedConditions.elementToBeClickable(tasksButton)).click();
        Thread.sleep(4000);
        clickElement(userAccount);
        //wait.until(ExpectedConditions.elementToBeClickable(userAccount)).click();
        Thread.sleep(10000);

        String usernameValue = getTextFromElement(username);
        //WebElement userEl = wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        //String usernameValue = userEl.getAttribute("title").trim();
        clickElement(closeBtn);
        //wait.until(ExpectedConditions.elementToBeClickable(closeBtn)).click();
        Thread.sleep(5000);
        return usernameValue;

    }

    public void searchUserInUserAccounts(String usernameValue) throws InterruptedException {

        By userSearchInput = By.xpath("//input[contains(@placeholder,'3 or more characters')]");
        By userSearchButton = By.xpath("//span[@title='Search']/parent::a");
        sendKeysToElement(userSearchInput,usernameValue);
        keyAction("Enter",userSearchButton);
//        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(userSearchInput));
//
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//
//        js.executeScript(
//                "arguments[0].value = arguments[1];" +
//                        "arguments[0].dispatchEvent(new Event('input')); " +
//                        "arguments[0].dispatchEvent(new Event('change')); " +
//                        "arguments[0].dispatchEvent(new Event('blur'));",
//                searchInput, usernameValue
//        );

        Thread.sleep(1500);

//        wait.until(ExpectedConditions.elementToBeClickable(userSearchButton)).click();
//        Thread.sleep(4000); // wait for results
//        waitForPageReady();
//        safeClick(getSearchButton);
//        waitForPageResults();
//       //wait.until(ExpectedConditions.elementToBeClickable(getSearchButton)).click();
//        searchUser();
        //searchInput.sendKeys(Keys.ENTER);

        waitForPageReady();
        waitForPageResults();

        searchUser();

    }

        public void searchUser() throws InterruptedException {
        //wait.until(ExpectedConditions.elementToBeClickable(getSearchButton)).click();
        Thread.sleep(1500);
        clickElement(userLink);
        //wait.until(ExpectedConditions.elementToBeClickable(userLink)).click();
        Thread.sleep(10000);
            clickElement(doneButton);
        //wait.until(ExpectedConditions.elementToBeClickable(doneButton)).click();
        Thread.sleep(2500);

    }




    }

