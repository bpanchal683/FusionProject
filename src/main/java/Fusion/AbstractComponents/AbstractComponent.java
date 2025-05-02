package Fusion.AbstractComponents;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.UUID;


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

    public void waitForFieldToBeReady(By fieldLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(fieldLocator));
    }


    public void clickStaleElement(By findBy)
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

        // Get current test instance from ExtentTestManager
        ExtentTest test = ExtentTestManager.getExtentTest();

        try {
            String screenshotPath = AbstractComponent.getScreenshot(driver); // or your method name
            test.pass("Clicked on locator: " + findBy.toString(),
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (IOException io) {
            test.pass("Clicked element, but screenshot could not be attached.");
        }

    }

    public void clickElement(By findBy) {
        WebElement element;

        try {
            element = driver.findElement(findBy);
            element.click();
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is stale, re-locating and retrying...");
            element = driver.findElement(findBy); // Retry without wait
            element.click();
        }

        // Logging with ExtentReports
        ExtentTest test = ExtentTestManager.getExtentTest();

        try {
            String screenshotPath = AbstractComponent.getScreenshot(driver);
            test.pass("Clicked on locator: " + findBy.toString(),
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (IOException io) {
            test.pass("Clicked element, but screenshot could not be attached.");
        }
    }

    public void sendKeysToElement(By findBy, String value) {
        ExtentTest test = ExtentTestManager.getExtentTest();

        try {
            WebElement element = driver.findElement(findBy);
            //element.clear();
            element.sendKeys(value);

            try {
                String screenshotPath = AbstractComponent.getScreenshot(driver);
                test.pass("Entered value into element: " + findBy.toString(),
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } catch (IOException io) {
                test.pass("Entered value into element: " + findBy.toString() + ", but screenshot could not be attached.");
            }

        } catch (Exception e) {
            test.fail("Failed to send keys to element: " + findBy.toString() + " - " + e.getMessage());
        }
    }


    public String getTextFromElement(By findBy) {
        String text = "";
        try {
            WebElement element = driver.findElement(findBy);
            text = element.getText();

            // Log to ExtentReports with screenshot
            ExtentTest test = ExtentTestManager.getExtentTest();
            String screenshotPath = AbstractComponent.getScreenshot(driver);
            test.pass("Fetched text from element: \"" + text + "\"",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            ExtentTestManager.getExtentTest().fail("Failed to get text: " + e.getMessage());
        }
        return text;
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
        // Logging with ExtentReports
        ExtentTest test = ExtentTestManager.getExtentTest();

        try {
            String screenshotPath = AbstractComponent.getScreenshot(driver);
            test.pass("Clicked on locator: " + locator.toString(),
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (IOException io) {
            test.pass("Clicked element, but screenshot could not be attached.");
        }
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

    public static String getScreenshot(WebDriver driver) throws IOException {
        TakesScreenshot ts=(TakesScreenshot)driver;
        File source=ts.getScreenshotAs(OutputType.FILE);
        //String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS").format(new Date());
        String uniqueId = UUID.randomUUID().toString();
        String scrPath = System.getProperty("user.dir") + "/reports/" + "SS_" + uniqueId + ".png";
        String fileName = "SS_" + uniqueId + ".png";
        File file=new File(scrPath);
//        String relativePath = "./reports/" + testCaseName + timestamp + ".png";
//        File file = new File(relativePath);
        FileUtils.copyFile(source,file);
        return fileName;
    }



}
