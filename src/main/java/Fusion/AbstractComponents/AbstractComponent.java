package Fusion.AbstractComponents;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;
import Fusion.AbstractComponents.AbstractComponent;


public class AbstractComponent {

    WebDriver driver;

    public AbstractComponent(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void goTo(String url)
    {
        driver.get(url);
    }

    public void waitForElementPresence(By findBy)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
    }

    public void waitForElementVisible(By findBy)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForElementClick(By findBy)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(findBy));
    }

    public void waitForElementToDisappear(By findBy)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }

    public void clickElement(By findBy)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement element= wait.until(ExpectedConditions.presenceOfElementLocated(findBy));

        try {
            // Try interacting with the element
            element.click();  // For example, clicking the element
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is stale, re-locating...");

            // Re-locate the element after the exception is thrown
            element = wait.until(ExpectedConditions.presenceOfElementLocated(findBy));

            // Retry interacting with the element
            element.click();
        }

    }

    public void scrollIntoView(By locator) {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void jsClick(By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void waitForFieldToBeReady(By fieldLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(fieldLocator));
    }



    public static String fnRandomNum() {
        Random rand = new Random();
        // Generates a number between 1000 and 9999 (inclusive)
        int UID = rand.nextInt(9000) + 1000;
        // Return the string in the format "INV" followed by the random number
        return "T3INV" + UID;
    }

    public static  String getCurrentDateFormatted() {
        // Get the current date
        Date currentDate = new Date();

        // Define the desired date format
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");

        // Return the formatted date as a string
        return sdf.format(currentDate);
    }

}
