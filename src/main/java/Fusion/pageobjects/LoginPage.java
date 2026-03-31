package Fusion.pageobjects;


import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class LoginPage extends AbstractComponent {
    WebDriver driver;
    WebDriverWait wait;

//    @FindBy(xpath = "//input[@id='userid']")
//    WebElement usernameField;
//    @FindBy(xpath = "//input[@id='password']")
//    WebElement passwordField;
//    @FindBy(xpath = "//button[@id='btnActive']")
//    WebElement signInBtn;

    By usernameField = By.xpath("//input[@id='userid']");
    By passwordField = By.xpath("//input[@id='password']");
    By signInBtnOld = By.xpath("//button[@id='btnActive1']");
    By signInBtnNew = By.xpath("//button[@id='btnActive']");
    By signInBtn = By.xpath("//button[@id='btnActive']");

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void login(String username, String password) {
        sendKeysToElement(usernameField, username);
        sendKeysToElement(passwordField, password);

        // Self-healing demo: try old locator, fallback to new
        WebElement loginBtn = findWithHealing(signInBtnOld, signInBtnNew);
        loginBtn.click();
    }
}

