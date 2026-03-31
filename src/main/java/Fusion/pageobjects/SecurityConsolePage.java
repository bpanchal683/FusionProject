package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class SecurityConsolePage extends AbstractComponent {
    WebDriver driver;
    WebDriverWait wait;

    By navigator = By.xpath("//a[@title='Navigator']");
    By Tools = By.xpath("//div[@title='Tools']");
    By securityConsole = By.xpath("//a[@title='Security Console']");
    By OkButton = By.xpath("//button[normalize-space()='OK']");
    By usersTab = By.xpath("//div[normalize-space()='Users']");

    By AddUserButton = By.xpath("//button[@title='Add User Account']");
    // Fields on Create User Page

    By ClickCombo = By.xpath("//label[text()='Associated Person Type']/following::input[@role='combobox'][1]");
    By AssociatedPerson = By.xpath("//li[normalize-space()='Worker']");
    By firstName = By.xpath("//label[normalize-space()='First Name']/following::input[1]");
    By lastName = By.xpath("//input[@aria-label='Last Name']");
    By emailId = By.xpath("//input[@aria-label='Email']");
    By phone = By.xpath("//input[@aria-label='Phone']");
    //By userName = By.xpath("//input[contains(@id,'userName')]");
    By password = By.xpath("//input[@aria-label='Password']");
    By ConfirmPassword = By.xpath("//input[@aria-label='Confirm Password']");
    By addRoleBtn = By.xpath("//button[normalize-space(text())='Add Role']");
    By searchRoleInput = By.xpath("//input[contains(@id,'urSrcBx') and @type='text']");
    By searchRoleBtn = By.xpath("//input[contains(@placeholder,'Enter 3')]/following::img[@title='Search'][1]");
    //By img =
    //By searchRoleBtn = By.xpath("(//img[contains(@src,'search') or @title='Search'])[2]");
    By addRoleButton = By.xpath("//button[contains(text(),'Add Role Membership')]");
    By doneButton = By.xpath("//button[@title='Done']");
    //By selectRole = By.xpath("(//input[@type='checkbox'])[1]");
    //By okRoleBtn = By.xpath("//button[contains(text(),'OK')]");

    By saveBtn = By.xpath("//button[@title='Save and Close']");

    public SecurityConsolePage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void openSecurityConsole() throws InterruptedException {
        clickElement(navigator);
        //wait.until(ExpectedConditions.elementToBeClickable(navigator)).click();
        clickElement(Tools);
        //wait.until(ExpectedConditions.elementToBeClickable(Tools)).click();
        clickElement(securityConsole);
        //wait.until(ExpectedConditions.elementToBeClickable(securityConsole)).click();
        Thread.sleep(5000);
        clickElement(usersTab);
        //wait.until(ExpectedConditions.elementToBeClickable(usersTab)).click();
        Thread.sleep(5000);
        //handleWarningPopup();

    }

    public void clickCreateUser() {
        clickElement(AddUserButton);
        //wait.until(ExpectedConditions.elementToBeClickable(AddUserButton)).click();
    }

    public void enterUserDetails(String fname, String lname, String mail, String ph, String pwd, String cpwd) {
        clickElement(ClickCombo);
        //wait.until(ExpectedConditions.elementToBeClickable(ClickCombo)).click();
        clickElement(AssociatedPerson);
        //wait.until(ExpectedConditions.elementToBeClickable(AssociatedPerson)).click();
        //sendKeysToElement();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fname);
        driver.findElement(lastName).sendKeys(lname);
        driver.findElement(emailId).sendKeys(mail);
        driver.findElement(phone).sendKeys(ph);
        driver.findElement(password).sendKeys(pwd);
        driver.findElement(ConfirmPassword).sendKeys(cpwd);
        //driver.findElement(userName).sendKeys(uname);
    }

    public void addRole(String roleName) throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(addRoleBtn)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchRoleInput)).sendKeys(roleName);
        //WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchRoleBtn));
        //btn.click();
        Thread.sleep(2000);
        WebElement img = driver.findElement(By.xpath("//img[@title='Search']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(img).click().perform();
        //driver.findElement(searchRoleBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(addRoleButton)).click();
        driver.findElement(doneButton).click();
    }

    public void saveUser() throws InterruptedException {
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
    }

    public void handleWarningPopup() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Wait for popup OK button - BUT without throwing exception if not found
            //wait.until(ExpectedConditions.elementToBeClickable(OkButton)).click();
            List<WebElement> okButtons = driver.findElements(By.xpath("//button[normalize-space()='OK']"));

            if (!okButtons.isEmpty()) {
                okButtons.get(0).click();
                System.out.println("Popup appeared → Clicked OK");
            } else {
                System.out.println("Popup NOT found → Continuing...");
            }

        } catch (Exception e) {
            System.out.println("No popup detected, continuing...");
        }
    }

}
