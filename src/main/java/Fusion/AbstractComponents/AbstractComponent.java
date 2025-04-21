package Fusion.AbstractComponents;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;

import static java.lang.Thread.sleep;

public class AbstractComponent {

    WebDriver driver;

    public AbstractComponent(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void goTo()
    {
        driver.get("https://ekwm-test.login.us6.oraclecloud.com/");
    }

    public void waitForElement(By findBy)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
    }

    public void waitForElementToDisappear(By findBy)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }

    public void waitForElementToBeClickable(By findBy)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

        try {
            // Try interacting with the element
            element.click();  // For example, clicking the element
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is stale, re-locating...");

            // Re-locate the element after the exception is thrown
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

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

//    // ✅ Robust click method
//    public void click(By locator) {
//       // waitForADFToBeIdle(); // Optional: only if you're working with Oracle Fusion
//
//        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
//        int retries = 3;
//        for (int i = 0; i < retries; i++) {
//            try {
//                WebElement element = driver.findElement(locator);
//
//                // Scroll to center of viewport
//                ((JavascriptExecutor) driver).executeScript(
//                        "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
//
//                wait.until(ExpectedConditions.elementToBeClickable(locator));
//
//                sleep(300); // Short wait after scrolling
//                element.click();
//                return;
//
//            } catch (ElementClickInterceptedException e) {
//                System.out.println("Click intercepted. Retrying: " + (i + 1));
//                //sleep(1000);
//            } catch (StaleElementReferenceException e) {
//                System.out.println("Stale element. Retrying: " + (i + 1));
//            } catch (Exception e) {
//                System.out.println("Click failed. Using JS click as fallback: " + e.getMessage());
//                //jsClick(locator);
//                return;
//            }
//        }
//    }


    public static String fnRandomNum() {
        Random rand = new Random();
        // Generates a number between 1000 and 9999 (inclusive)
        int UID = rand.nextInt(9000) + 1000;
        // Return the string in the format "INV" followed by the random number
        return "T3INV" + UID;
    }

    public static String getCurrentDateFormatted() {
        // Get the current date
        Date currentDate = new Date();

        // Define the desired date format
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");

        // Return the formatted date as a string
        return sdf.format(currentDate);
    }
}
