package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.MessageFormat;
import java.time.Duration;

public class HireAnEmployee extends AbstractComponent {

    WebDriver driver;
    WebDriverWait wait;

    By homeButton = By.xpath("//a[@title='Home']");
    By clientGroupButton = By.xpath("//a[normalize-space()='My Client Groups']");
    By newPersonButton = By.xpath("//a[normalize-space()='New Person']");
    By hireAnEmployee = By.xpath("//a[normalize-space(text())='Hire an Employee']");
    By hireReason = By.xpath("//label[normalize-space()='Hire Reason']/following::input[@role='combobox'][1]");
    By legalEmployer = By.xpath("//label[normalize-space()='Legal Employer']/following::input[@role='combobox'][1]");
    String legalEmployerOption = "//li[contains(text(),''{0}'')]";
    By lastName = By.xpath("//label[normalize-space()='Last Name']/following::input[1]");
    By firstName = By.xpath("//label[normalize-space()='First Name']/following::input[1]");
    By nextButton = By.xpath("//span[normalize-space()='Next']/ancestor::div[contains(@id,'next')]");
    By addressLine1 = By.xpath("//label[normalize-space()='Address Line 1']/following::input[1]");
    By addressLine2 = By.xpath("//label[normalize-space()='Address Line 2']/following::input[1]");
    By city = By.xpath("//label[normalize-space()='City or Town']/following::input[1]");
    By pinCode = By.xpath("//label[normalize-space()='Pin Code']/following::input[1]");
    By businessUnit = By.xpath("//input[contains(@id,'businessUnitId')]");
    By assignmentfield = By.xpath("(//label[contains(text(),'Assignment')]/following::input)[1]");
    By jobID = By.xpath("//input[contains(@id,'jobId')]");
    By department = By.xpath("//input[contains(@id,'department')]");
    By timeCard = By.xpath("//input[contains(@id,'timecardRequired')]");
    By managerName = By.xpath("//input[contains(@id,'ManagerNameId')]");
    By submitButton = By.xpath("//span[normalize-space()='Submit']/ancestor::a[1]");
    By yesButton = By.xpath("//button[normalize-space(.)='Yes']");
    By okButton = By.xpath("//button[contains(@id,'okConfirmationDialog')]");

    public By getLegalEmployerOption(String value) {
        return By.xpath(MessageFormat.format(legalEmployerOption, value));
    }

    public HireAnEmployee(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public void setHireAnEmployee(String hireReasonvalue, String leaglEmp, String lastname, String firstname,
                                  String address1, String cityValue, String address2, String pincode, String bu, String assignmentValue, String managerValue, String timecardValue)
            throws InterruptedException {

        Thread.sleep(2000);
        clickElement(homeButton);
        Thread.sleep(2000);

        clickElement(clientGroupButton);
        Thread.sleep(2000);

        clickElement(newPersonButton);
        Thread.sleep(3000);

        clickElement(hireAnEmployee);
        Thread.sleep(3000);

        sendKeysToElement(hireReason, hireReasonvalue);
        Thread.sleep(2000);

        sendKeysToElement(legalEmployer, leaglEmp);
        Thread.sleep(2000);
        clickElement(getLegalEmployerOption(leaglEmp));

        Thread.sleep(3000);

        sendKeysUsingAction(lastName, lastname);
        clickElement(lastName);
        Thread.sleep(1500);

        sendKeysUsingAction(firstName, firstname);
        clickElement(firstName);
        Thread.sleep(2000);

        clickElement(nextButton);
        Thread.sleep(4000);

        sendKeysToElement(addressLine1, address1);
        Thread.sleep(2000);

        sendKeysToElement(city, cityValue);
        Thread.sleep(2000);


        waitForFieldToBeReady(pinCode);
        scrollIntoView(pinCode);

// RE-FIND element every time to avoid stale reference
        WebElement pin;

        for (int i = 0; i < 3; i++) {     // retry mechanism
            try {
                pin = wait.until(ExpectedConditions.elementToBeClickable(pinCode));

                pin.click();
                pin.clear();
                Thread.sleep(300);

                // TYPE FULL VALUE AT ONCE (Fusion prefers this)
                pin.sendKeys(pincode);
                Thread.sleep(300);

                // TAB is REQUIRED to confirm & lock value
                pin.sendKeys(Keys.TAB);
                Thread.sleep(800);

                break; // success — exit retry loop
            }
            catch (StaleElementReferenceException e) {
                if (i == 2) throw e; // fail only after 3 attempts
                Thread.sleep(500);   // wait for DOM re-render
            }
        }



        waitForFieldToBeReady(addressLine2);
        scrollIntoView(addressLine2);

// Slow typing with re-locate logic (avoids stale element)
        driver.findElement(addressLine2).clear();
        Thread.sleep(300);

        for (char c : address2.toCharArray()) {
            driver.findElement(addressLine2).sendKeys(Character.toString(c));
            Thread.sleep(150);
        }

// Commit value
        driver.findElement(addressLine2).sendKeys(Keys.TAB);
        Thread.sleep(600);



        clickElement(nextButton);
        Thread.sleep(4000);

        waitForFieldToBeReady(businessUnit);
        scrollIntoView(businessUnit);
        sendKeysToElement(businessUnit, bu);
        Thread.sleep(3000);

        waitForFieldToBeReady(assignmentfield);
        scrollIntoView(assignmentfield);

        driver.findElement(assignmentfield).clear();
        Thread.sleep(300);

        for (char c : assignmentValue.toCharArray()) {
            driver.findElement(assignmentfield).sendKeys(Character.toString(c));
            Thread.sleep(150);
        }

        driver.findElement(assignmentfield).sendKeys(Keys.TAB);
        Thread.sleep(600);



        moveToElement(jobID);
        sendKeysToElement(jobID, "Consult");
        Thread.sleep(2000);
        clickElement(By.xpath("//li[text()='Consultant Consultant']"));
        Thread.sleep(2000);

        moveToElement(department);
        sendKeysToElement(department, "Application");
        Thread.sleep(2000);
        clickElement(By.xpath("//li[text()='Application Services-Development-IND']"));

        Thread.sleep(2000);
        moveToElement(managerName);
        sendKeysToElement(managerName, managerValue);
        Thread.sleep(2000);
        clickElement(managerName);

        Thread.sleep(2000);
        moveToElement(timeCard);
        sendKeysToElement(timeCard, timecardValue);
        Thread.sleep(2000);
        clickElement(timeCard);

        waitForPageReady();
        safeClick(nextButton);
        Thread.sleep(2000);

        waitForPageReady();
        safeClick(nextButton);
        Thread.sleep(2000);

        clickElement(submitButton);
        Thread.sleep(4000);

        // YES button – wait until clickable, scroll, JS click fallback
        wait.until(ExpectedConditions.visibilityOfElementLocated(yesButton));
        wait.until(ExpectedConditions.elementToBeClickable(yesButton));

        scrollIntoView(yesButton);
        jsClick(yesButton);   // Your AbstractComponent has jsClick()
        Thread.sleep(3000);

        // OK button
        wait.until(ExpectedConditions.visibilityOfElementLocated(okButton));
        wait.until(ExpectedConditions.elementToBeClickable(okButton));

        scrollIntoView(okButton);
        jsClick(okButton);
        Thread.sleep(3000);

    }
}
