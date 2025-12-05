package Fusion.pageobjects;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class PersonManagement {

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
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public String searchByName() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(homeButton)).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(clientGroupButton)).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(personManage)).click();
        Thread.sleep(2000);
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(name));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input')); " +
                        "arguments[0].dispatchEvent(new Event('change')); " +
                        "arguments[0].dispatchEvent(new Event('blur'));",
                nameInput, "dummy71"
        );
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(firstRecord)).click();
        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(tasksButton)).click();
        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(userAccount)).click();
        Thread.sleep(10000);
        WebElement userEl = wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        String usernameValue = userEl.getAttribute("title").trim();

        wait.until(ExpectedConditions.elementToBeClickable(closeBtn)).click();
        Thread.sleep(5000);
        return usernameValue;

    }

    public void searchUserInUserAccounts(String usernameValue) throws InterruptedException {

        //By userSearchInput = By.xpath("//input[contains(@placeholder,'3 or more characters')]");
        By userSearchButton = By.xpath("//span[@title='Search']/parent::a");

        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(userSearchInput));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input')); " +
                        "arguments[0].dispatchEvent(new Event('change')); " +
                        "arguments[0].dispatchEvent(new Event('blur'));",
                searchInput, usernameValue
        );

        Thread.sleep(1500);

//        wait.until(ExpectedConditions.elementToBeClickable(userSearchButton)).click();
//        Thread.sleep(4000); // wait for results
//        waitForPageReady();
//        safeClick(getSearchButton);
//        waitForPageResults();
//       //wait.until(ExpectedConditions.elementToBeClickable(getSearchButton)).click();
//        searchUser();
        searchInput.sendKeys(Keys.ENTER);

        waitForPageReady();
        waitForPageResults();

        searchUser();

    }

        public void searchUser() throws InterruptedException {
        //wait.until(ExpectedConditions.elementToBeClickable(getSearchButton)).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(userLink)).click();
        Thread.sleep(10000);
        wait.until(ExpectedConditions.elementToBeClickable(doneButton)).click();
        Thread.sleep(2500);

    }
    public void waitForPageReady() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait.until(webDriver ->
                js.executeScript("return document.readyState").equals("complete")
        );

        // Wait until no active ADF requests
        wait.until(webDriver ->
                js.executeScript("return (window.requestCount === 0 || !window.requestCount)").equals(true)
        );
    }

    public void safeClick(By locator) {
        waitForOverlayToDisappear();

        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));

        try {
            el.click();
        } catch (Exception e) {
            // fallback: use JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }

        waitForOverlayToDisappear(); // before next interaction
    }
    public void waitForOverlayToDisappear() {
        try {
            WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(30));

            w.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector("div[id*='busy'], div[id*='overlay'], div.af_dialog_overlay, div.af_panelBusy, div.af_fe_pbusypopup")
            ));
        } catch (Exception ignored) {}
    }

    public void waitForPageResults() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.af_panelBusy, div.af_dialog_overlay")
        ));
    }

}