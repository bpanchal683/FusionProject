package Fusion.tests;
import Fusion.pageobjects.DeleteUser;
import Fusion.pageobjects.LoginPage;
import Fusion.pageobjects.SecurityConsolePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class deleteUsersInOracleFusion {


        WebDriver driver;
        LoginPage loginPage;
        DeleteUser editUser;

        @BeforeClass

        public void setup() {
            // ------------------ WebDriver Setup ----------------------
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // ------------------ Page Object Initialization -----------
            loginPage = new LoginPage(driver);
            editUser = new DeleteUser(driver);
        }
        @Test
        public void deleteFusionUser() throws InterruptedException {
            // ------------------ STEP 1 : Open Oracle Fusion --------
            driver.get("https://ekwm-dev1.login.us6.oraclecloud.com/");

            // ------------------ STEP 2 : Login ---------------------
            loginPage.login("JD", "Fusion@123");

            // ------------------ STEP 3 : Open Security Console -----
            editUser.openSecurityConsole("dummy");

            // ------------------ STEP 4 : Click Users ----------------
            editUser.deactivateUser();

            editUser.verifyUserIsDeactivated("dummy");


        }

        @AfterClass
        public void tearDown() {
            driver.quit();
        }
    }



