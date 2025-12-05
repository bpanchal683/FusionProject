package Fusion.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ServiceNowLoginPage {
    WebDriver driver;
    WebDriverWait wait;

    By SsoLogin = By.xpath("//a[normalize-space()='Login with SSO']");
    By usernameField = By.xpath("//input[@id='sso_selector_id']");
    By signInBtn = By.xpath("//button[@id='btnActive']");

    public ServiceNowLoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void login(String username, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(SsoLogin)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(signInBtn)).click();
    }
}

