package Fusion.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class DeleteUser {
    WebDriver driver;
    WebDriverWait wait;
    By homeButton = By.xpath("//a[@title='Home']");
    By navigator = By.xpath("//a[@title='Navigator']");
    By Tools = By.xpath("//div[@title='Tools']");
    By Tools1 = By.xpath("//a[normalize-space()='Tools']");
    By securityConsole = By.xpath("//a[@title='Security Console']");
    By securityCon = By.xpath("//div[contains(@id,'SECURITY_CONSOLE') or contains(@title,'Security Console')]");
    By usersTab = By.xpath("//div[normalize-space()='Users']");
    By ClickCombo = By.xpath("//input[@role='combobox' and @value='User Name']/following::a[contains(@class,'x1kt')][1]");
    By firstname = By.xpath("//li[normalize-space()='First Name']");
    By searchInput = By.xpath("//input[@placeholder='Enter 3 or more characters to search']");
    By searchButton = By.xpath("//img[@title='Search']");
    By userLink = By.xpath("//table[contains(@summary,'Users')]//a[contains(text(),'test')]");
    By editUser = By.xpath("//button[normalize-space()='Edit']");
    By activeCheckbox = By.xpath("//label[normalize-space()='Active']/preceding::input[@type='checkbox'][1]");
    By saveClose = By.xpath("//button[@title='Save and Close']");
    By done = By.xpath("//button[normalize-space()='Done']");
    By rightArrow = By.xpath("//div[contains(@class,'flat-tabs-overflow-right') and contains(@class,'svg-solid')]");
    public DeleteUser(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void openSecurityConsole(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(navigator)).click();
        wait.until(ExpectedConditions.elementToBeClickable(Tools)).click();
        wait.until(ExpectedConditions.elementToBeClickable(securityConsole)).click();
        wait.until(ExpectedConditions.elementToBeClickable(usersTab)).click();
        wait.until(ExpectedConditions.elementToBeClickable(ClickCombo)).click();
        wait.until(ExpectedConditions.elementToBeClickable(firstname)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput)).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(userLink)).click();
    }

    public void deactivateUser() throws InterruptedException {
        // 1. Click Edit
        wait.until(ExpectedConditions.elementToBeClickable(editUser)).click();
        // 2. Wait until the checkbox becomes enabled after Edit
        // 2. Wait until the checkbox becomes enabled after Edit
        wait.until(driver1 -> {
            WebElement checkbox = driver.findElement(activeCheckbox);
            return checkbox.isEnabled();
        });

        // 3. Now uncheck via label (ADF-compatible way)
        WebElement activeInput = wait.until(ExpectedConditions.presenceOfElementLocated(activeCheckbox));

        if (activeInput.isSelected()) {
            WebElement activeLabel = driver.findElement(By.xpath("//label[normalize-space()='Active']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", activeLabel);
        }
        // 4. Save and Close
        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(saveClose)).click();
        System.out.println("User deactivated successfully!");
        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(done)).click();
    }

    public boolean verifyUserIsDeactivated(String username) throws InterruptedException{
        wait.until(ExpectedConditions.elementToBeClickable(homeButton)).click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(rightArrow)).click();
        wait.until(ExpectedConditions.elementToBeClickable(Tools1)).click();
        wait.until(ExpectedConditions.elementToBeClickable(securityCon)).click();
        // 1. Go back to Users tab
        wait.until(ExpectedConditions.elementToBeClickable(usersTab)).click();

        // 2. Re-select “First Name” filter
        wait.until(ExpectedConditions.elementToBeClickable(ClickCombo)).click();
        wait.until(ExpectedConditions.elementToBeClickable(firstname)).click();

        // 3. Search again
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(username);

        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

        // 4. Open user link again
        wait.until(ExpectedConditions.elementToBeClickable(userLink)).click();

        // 5. Read the Active checkbox state
        WebElement activeCheck = wait.until(ExpectedConditions.presenceOfElementLocated(activeCheckbox));
        boolean isActive = activeCheck.isSelected();

        System.out.println("User Active Status = " + isActive);

        return !isActive;  // should be false if user is deactivated
    }

}