package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class DeleteUser extends AbstractComponent {

    WebDriver driver;
    WebDriverWait wait;

    By homeButton = By.xpath("//a[@title='Home']");
    By navigator = By.xpath("//a[@title='Navigator']");
    By Tools = By.xpath("//div[@title='Tools'] | //a[normalize-space()='Tools']");
    By Tools1 = By.xpath("//div[@title='Tools'] | //a[normalize-space()='Tools']");
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
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public void openSecurityConsole(String username) throws InterruptedException {
        Thread.sleep(3000);
        clickElement(navigator);
        Thread.sleep(1000);
        clickElement(Tools);
        Thread.sleep(1000);
        clickElement(securityConsole);
        Thread.sleep(1000);
        clickElement(usersTab);
        Thread.sleep(1000);
        clickElement(ClickCombo);
        Thread.sleep(1000);
        clickElement(firstname);
        Thread.sleep(1000);
        sendKeysToElement(searchInput, username);
        Thread.sleep(1000);
        clickElement(searchButton);
        Thread.sleep(1000);
        clickElement(userLink);
    }

    public void deactivateUser() throws InterruptedException {
        highlightElement(driver.findElement(editUser));
        clickElement(editUser);
        Thread.sleep(3000);

        wait.until(driver1 -> {
            WebElement checkbox = driver.findElement(activeCheckbox);
            return checkbox.isEnabled();
        });

        WebElement activeInput = wait.until(ExpectedConditions.presenceOfElementLocated(activeCheckbox));

        if (activeInput.isSelected()) {
            WebElement activeLabel = driver.findElement(By.xpath("//label[normalize-space()='Active']"));
            highlightElement(activeLabel);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", activeLabel);
        }

        Thread.sleep(4000);
        clickElement(saveClose);
        System.out.println("User deactivated successfully!");
        Thread.sleep(4000);
        clickElement(done);
    }

    public boolean verifyUserIsDeactivated(String username) throws InterruptedException {
        clickElement(homeButton);

        WebElement rightArrow = driver.findElement(By.id("clusters-right-nav"));
        JavascriptExecutor js = (JavascriptExecutor) driver;

// Perform two forced clicks
        js.executeScript("arguments[0].click();", rightArrow);
        Thread.sleep(500);
        js.executeScript("arguments[0].click();", rightArrow);
        Thread.sleep(500);

// Wait for Tools to appear
        By toolsLocator = By.xpath("//div[@title='Tools'] | //a[normalize-space()='Tools']");
        WebElement toolsMenu = wait.until(ExpectedConditions.elementToBeClickable(toolsLocator));
        toolsMenu.click();
        clickElement(securityCon);
        clickElement(usersTab);
        clickElement(ClickCombo);
        clickElement(firstname);

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(username);
        clickElement(searchButton);

        clickElement(userLink);

        WebElement activeCheck = wait.until(ExpectedConditions.presenceOfElementLocated(activeCheckbox));
        boolean isActive = activeCheck.isSelected();

        // ⭐ NEW — Highlight the status field (ACTIVE checkbox) on page
        highlightElement(activeCheck);

        // ⭐ NEW — Also highlight the label for clearer visibility
        WebElement statusLabel = driver.findElement(By.xpath("//label[normalize-space()='Active']"));
        highlightElement(statusLabel);
        Thread.sleep(4000);
        System.out.println("User Active Status = " + isActive);

        return !isActive;
    }

}
