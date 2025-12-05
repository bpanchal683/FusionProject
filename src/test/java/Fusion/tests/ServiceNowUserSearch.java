package Fusion.tests;

import Fusion.pageobjects.FindUserInServiceNow;
import Fusion.pageobjects.LoginPage;
import Fusion.pageobjects.SecurityConsolePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ServiceNowUserSearch {

    WebDriver driver;
    FindUserInServiceNow VerifyInSN;

    @BeforeClass

    public void setup() {
        // ------------------ WebDriver Setup ----------------------
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // ------------------ Page Object Initialization -----------
        VerifyInSN = new FindUserInServiceNow(driver);

    }

    @Test
    public void FindFusionUser() throws InterruptedException {
        VerifyInSN.clickAllButton();
    }
}
