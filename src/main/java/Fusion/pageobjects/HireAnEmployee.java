package Fusion.pageobjects;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;


public class HireAnEmployee {
    WebDriver driver;
    WebDriverWait wait;

    By homeButton = By.xpath("//a[@title='Home']");
    By clientGroupButton = By.xpath("//a[normalize-space()='My Client Groups']");
    By newPersonButton = By.xpath("//a[normalize-space()='New Person']");
    By hireAnEmployee = By.xpath("//a[normalize-space(text())='Hire an Employee']");
    By hireReason = By.xpath("//label[normalize-space()='Hire Reason']/following::input[@role='combobox'][1]");
    By legalEmployer = By.xpath("//label[normalize-space()='Legal Employer']/following::input[@role='combobox'][1]");
    By lastName = By.xpath("//label[normalize-space()='Last Name']/following::input[1]");
    By firstName = By.xpath("//label[normalize-space()='First Name']/following::input[1]");
    By nextButton = By.xpath("//span[normalize-space()='Next']/ancestor::div[contains(@id,'next')]");
    By addressLine1 = By.xpath("//label[normalize-space()='Address Line 1']/following::input[1]");
    By addressLine2 = By.xpath("//label[normalize-space()='Address Line 2']/following::input[1]");
    By city = By.xpath("//label[normalize-space()='City or Town']/following::input[1]");
    By pinCode = By.xpath("//label[normalize-space()='Pin Code']/following::input[1]");
    By businessUnit = By.xpath("//input[contains(@id,'businessUnitId')]");
    By jobID = By.xpath("//input[contains(@id,'jobId')]");
    By department = By.xpath("//input[contains(@id,'department')]");
    By timeCard = By.xpath("//input[contains(@id,'timecardRequired')]");
    By managerName = By.xpath("//input[contains(@id,'ManagerNameId')]");
    By submitButton = By.xpath("//span[normalize-space()='Submit']/ancestor::a[1]");
    By yesButton = By.xpath("//button[.//span[normalize-space()='Yes'] or contains(normalize-space(),'Yes')]");
    By okButton = By.xpath("//button[contains(@id,'okConfirmationDialog')]");
    By personManage = By.xpath("//div[@title='Person Management']");


    public HireAnEmployee(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void setHireAnEmployee() throws InterruptedException {
        Thread.sleep(3000);

        // Click Home
        wait.until(ExpectedConditions.elementToBeClickable(homeButton)).click();
        Thread.sleep(2000);
        // Click My Client Groups
        wait.until(ExpectedConditions.elementToBeClickable(clientGroupButton)).click();
        Thread.sleep(2000);
        // Click New Person
        wait.until(ExpectedConditions.elementToBeClickable(newPersonButton)).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(hireAnEmployee)).click();
        Thread.sleep(3000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(hireReason)).sendKeys("Hire to fill vacant position");
        Thread.sleep(3000);
        WebElement legalEmployerField = wait.until(ExpectedConditions.visibilityOfElementLocated(legalEmployer));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input')); " +
                        "arguments[0].dispatchEvent(new Event('change')); " +
                        "arguments[0].dispatchEvent(new Event('blur'));",
                legalEmployerField, "IT CONVERGENCE PROFESSIONAL SERVICES PRIVATE LIMITED"
        );

// force popup close using TAB
        legalEmployerField.sendKeys(Keys.TAB);

        Thread.sleep(4000);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys("testing");
        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));
        //JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input'));" +
                        "arguments[0].dispatchEvent(new Event('change'));",
                lastNameField, "test71"
        );

        Thread.sleep(2000);

        //------ ENTER FIRST NAME ------
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        js.executeScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input'));" +
                        "arguments[0].dispatchEvent(new Event('change'));",
                firstNameField, "dummy71"
        );

        Thread.sleep(3000);

        //------ CLICK NEXT ------
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        Thread.sleep(5000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(addressLine1)).sendKeys("ITC Testing Center");
        Thread.sleep(3000);

        //wait.until(ExpectedConditions.visibilityOfElementLocated(city)).sendKeys("Hyderabad");
        WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(city));

        //JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input')); " +
                        "arguments[0].dispatchEvent(new Event('change')); " +
                        "arguments[0].dispatchEvent(new Event('blur'));",
                cityField, "Hyderabad"
        );
//        js.executeScript(
//                "var ev = new KeyboardEvent('keydown', {key: 'Enter', keyCode: 13, which: 13});" +
//                        "arguments[0].dispatchEvent(ev);",
//                cityField
//        );
        js.executeScript(
                "document.activeElement.dispatchEvent(new KeyboardEvent('keydown', {key: 'Enter', keyCode: 13}));"
        );
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addressLine2)).sendKeys("DLF Cyber City");
        Thread.sleep(3000);
        WebElement pinCodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(pinCode));
        js.executeScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input'));" +
                        "arguments[0].dispatchEvent(new Event('change'));" +
                        "arguments[0].dispatchEvent(new Event('blur'));",
                pinCodeField, "500081"
        );

        Thread.sleep(3000);
        js.executeScript(
                "document.activeElement.dispatchEvent(new KeyboardEvent('keydown', {key: 'Enter', keyCode: 13}));"
        );
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        Thread.sleep(5000);
        System.out.println("Next button_1 clicked successfully!");
        //wait.until(ExpectedConditions.visibilityOfElementLocated(businessUnit)).sendKeys("IT Convergence India - Hyderabad");
        WebElement businessUnitField = wait.until(ExpectedConditions.visibilityOfElementLocated(businessUnit));

        //JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input')); " +
                        "arguments[0].dispatchEvent(new Event('change')); " +
                        "arguments[0].dispatchEvent(new Event('blur'));",
                businessUnitField, "IT Convergence India - Hyderabad"
        );

        Thread.sleep(3000);
        // Scroll to Position field after Business Unit
        WebElement positionField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[normalize-space()='Position']/following::input[1]")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", positionField);

        Thread.sleep(5000);

//        WebElement jobIdField = wait.until(ExpectedConditions.visibilityOfElementLocated(jobID));
//        js.executeScript(
//                "arguments[0].value = arguments[1];" +
//                        "arguments[0].dispatchEvent(new Event('input'));" +
//                        "arguments[0].dispatchEvent(new Event('change'));" +
//                        "arguments[0].dispatchEvent(new Event('blur'));",
//                jobIdField, "Consultant"
//        );
        WebElement jobIdField = wait.until(ExpectedConditions.elementToBeClickable(jobID));
        jobIdField.click();
        jobIdField.sendKeys("Consult");   // minimum 3 chars
        Thread.sleep(2000);

// select the correct LOV item
        WebElement jobSelect = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[normalize-space(.)='Consultant Consultant' and not(ancestor::*[contains(@style,'display:none')])]")
        ));
        jobSelect.click();

        Thread.sleep(3000);

//        WebElement departmentField = wait.until(ExpectedConditions.visibilityOfElementLocated(department));
//        js.executeScript(
//                "arguments[0].value = arguments[1];" +
//                        "arguments[0].dispatchEvent(new Event('input'));" +
//                        "arguments[0].dispatchEvent(new Event('change'));" +
//                        "arguments[0].dispatchEvent(new Event('blur'));",
//                departmentField, "Application Services-Development-IND"
//        );
        WebElement departmentField = wait.until(ExpectedConditions.elementToBeClickable(department));
        departmentField.click();
        departmentField.sendKeys("Application");  // partial text to trigger LOV
        Thread.sleep(3000);

        WebElement deptSelect = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[normalize-space(.)='Application Services-Development-IND' and not(ancestor::*[contains(@style,'display:none')])]")

        ));
        deptSelect.click();




        Thread.sleep(3000);
        WebElement managerField = wait.until(ExpectedConditions.visibilityOfElementLocated(managerName));

        //JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].focus();" +
                        "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));" +
                        "arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));",
                managerField, "Kalsi, Sharan"
        );
        Thread.sleep(3000);
        WebElement timeCardField = wait.until(ExpectedConditions.visibilityOfElementLocated(timeCard));
        js.executeScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input')); " +
                        "arguments[0].dispatchEvent(new Event('change')); " +
                        "arguments[0].dispatchEvent(new Event('blur'));",
                timeCardField, "Yes"
        );
        Thread.sleep(3000);
        //wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        waitForPageReady();
        safeClick(nextButton);

        System.out.println("Next button_2 clicked successfully!");
        Thread.sleep(3000);
        //wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        waitForPageReady();
        safeClick(nextButton);
        System.out.println("Next button_3 clicked successfully!");
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
        Thread.sleep(10000);

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

}




