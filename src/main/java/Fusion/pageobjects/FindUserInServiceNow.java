package Fusion.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class FindUserInServiceNow {

    WebDriver driver;
    WebDriverWait wait;

    // Correct constructor
    public FindUserInServiceNow(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Click ALL button in ServiceNow Polaris UI
    public void clickAllButton() {

        driver.get("https://itconvergencedev.service-now.com/");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Get shadow root of Polaris header
        WebElement shadow = (WebElement) js.executeScript(
                "return document.querySelector('now-polaris-header').shadowRoot"
        );

        // Find the ALL button inside shadow root
        WebElement allBtn = (WebElement) js.executeScript(
                "return arguments[0].querySelector(\"div[aria-label='All']\")",
                shadow
        );

        // Click ALL button
        js.executeScript("arguments[0].click();", allBtn);
    }
}
