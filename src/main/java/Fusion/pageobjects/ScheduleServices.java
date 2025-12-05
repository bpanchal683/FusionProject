package Fusion.pageobjects;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
public class ScheduleServices {
    WebDriver driver;
    WebDriverWait wait;

    public String lastProcessId;
    By homeButton = By.xpath("//a[@title='Home']");
    By navigator = By.xpath("//a[@title='Navigator']");
    By Tools = By.xpath("//div[@title='Tools']");
    By Tool = By.xpath("//a[normalize-space()='Tools']");
    By scheduleProcess = By.xpath("//span[normalize-space()='Scheduled Processes']");
    By schedulePro = By.xpath("//a[contains(@id,'itemNode_tools_scheduled_processes')]");
    By scheduleNewProcess = By.xpath("//a[normalize-space()='Schedule New Process']");
    By okButton = By.xpath("//button[normalize-space()='OK' and not(ancestor::*[@style='display:none'])]");
    //By cokButton = By.xpath("(//button[normalize-space()='OK' and not(contains(@style,'display:none'))])[2]");
    By popupOK = By.xpath("//button[normalize-space()='OK' and not(ancestor-or-self::*[@style='display:none'])][1]");
    By submitButton = By.xpath("//div[contains(@id,'submitButton')]//a");
    By expendButton = By.xpath("//a[@role='button' and @aria-label='Expand Search']");
    By processID = By.xpath("(//label[normalize-space()='Process ID']/following::input[1])[1]");
    By searchButton = By.xpath("//button[normalize-space()='Search']");
    By refreshButton = By.xpath("//a[@role='button']//img[@title='Refresh']");
    By rightArrow = By.xpath("//div[contains(@class,'flat-tabs-overflow-right') and contains(@class,'svg-solid')]");


    public ScheduleServices(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void sendLDP() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(navigator)).click();
        wait.until(ExpectedConditions.elementToBeClickable(Tools)).click();
        wait.until(ExpectedConditions.elementToBeClickable(scheduleProcess)).click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(scheduleNewProcess)).click();
        Thread.sleep(5000);
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[normalize-space()='Name']/following::input[@role='combobox'][1]")
        ));
        nameField.clear();
        nameField.sendKeys("Send Pending LDAP Requests");
        Thread.sleep(10000);

        // --- PRESS ENTER to trigger LOV selection ---
        nameField.sendKeys(Keys.ENTER);

        // Or small extra wait for Fusion to bind data
        Thread.sleep(10000);

        // Ensure value is selected — Fusion adds this class when selection is valid
//        wait.until(ExpectedConditions.attributeContains(
//                nameField, "value", "Send Pending LDAP Requests"));

        // --- Now OK button will become clickable ---
        wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();

        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();

    }


    public String captureProcessId1() throws InterruptedException {

        // Wait for confirmation message like:
        // "Process 4556213 was submitted."
        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'Process') and contains(text(),'submitted')]")
                )
        );

        // Extract whole text
        String text = message.getText();

        // Extract only digits → Process ID
        lastProcessId = text.replaceAll("\\D+", "");
        System.out.println("Captured Process ID: " + lastProcessId);
        // Wait and click OK on confirmation popup
        Thread.sleep(3500);
        wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
        Thread.sleep(3500);
        // Return the extracted Process ID
        return lastProcessId;
    }

    public void searchID() throws InterruptedException {
        Thread.sleep(3500);
        wait.until(ExpectedConditions.elementToBeClickable(expendButton)).click();
        Thread.sleep(3000);
        WebElement processIdInput = wait.until(ExpectedConditions.visibilityOfElementLocated(processID));
        clearFusionInput(processIdInput);
        processIdInput.sendKeys(lastProcessId);
        Thread.sleep(1500);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        Thread.sleep(10000);
        wait.until(ExpectedConditions.elementToBeClickable(refreshButton)).click();
        Thread.sleep(5000);
        waitForSucceededStatus();

    }


    public void retrieveLDP() throws InterruptedException{
        Thread.sleep(3000);

        // Click Home
        wait.until(ExpectedConditions.elementToBeClickable(homeButton)).click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(rightArrow)).click();
        //wait.until(ExpectedConditions.elementToBeClickable(navigator)).click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(Tool)).click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(schedulePro)).click();
        Thread.sleep(10000);
        wait.until(ExpectedConditions.elementToBeClickable(scheduleNewProcess)).click();

        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[normalize-space()='Name']/following::input[@role='combobox'][1]")
        ));

        nameField.clear();
        nameField.sendKeys("Retrieve Latest LDAP Changes");

        // Select LOV item
        nameField.sendKeys(Keys.ENTER);

        // Wait until Fusion binds the LOV value
        wait.until(ExpectedConditions.attributeContains(nameField, "value",
                "Retrieve Latest LDAP Changes"));

        // Wait until loading overlays disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@class,'af_overlay')]")
        ));

        // CLICK OK USING JS (Fusion requires this)
        WebElement ok = wait.until(ExpectedConditions.visibilityOfElementLocated(popupOK));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ok);

        // Now click Submit
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);
    }

    public String captureProcessId2() throws InterruptedException {

        // Wait for confirmation message like:
        // "Process 4556213 was submitted."
        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'Process') and contains(text(),'submitted')]")
                )
        );

        // Extract whole text
        String text = message.getText();

        // Extract only digits → Process ID
        lastProcessId = text.replaceAll("\\D+", "");
        System.out.println("Captured Process ID: " + lastProcessId);
        // Wait and click OK on confirmation popup
        Thread.sleep(3500);
        wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
        Thread.sleep(3500);
        // Return the extracted Process ID
        return lastProcessId;
    }

    public void clearFusionInput(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='';", element);   // Clear UI value
        js.executeScript("arguments[0].dispatchEvent(new Event('input'));", element); // Trigger ADF binding
    }

    public void waitForSucceededStatus () throws InterruptedException {

        int maxAttempts = 20;   // Runs refresh 20 times (40–60 sec approx)
        int attempt = 0;

        By succeededLabel = By.xpath("(//*[contains(text(),'Succeeded')])[2]");

        while (attempt < maxAttempts) {

            try {
                // Check if succeeded is now visible
                List<WebElement> success = driver.findElements(succeededLabel);

                if (!success.isEmpty() && success.get(0).isDisplayed()) {
                    System.out.println("🎉 Status is now Succeeded!");
                    return;
                }
            } catch (Exception ignore) {}

            // Not succeeded yet → refresh and retry
            System.out.println("⟳ Status not Succeeded yet... refreshing (Attempt " + (attempt + 1) + ")");

            WebElement refresh = wait.until(ExpectedConditions.elementToBeClickable(refreshButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", refresh);

            Thread.sleep(3000); // give Fusion time to reload

            attempt++;
        }

        throw new RuntimeException(" Status did NOT become Succeeded after max attempts.");
    }


}