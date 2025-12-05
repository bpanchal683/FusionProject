package Fusion.tests;
import Fusion.TestComponent.ExcelReader;
import Fusion.pageobjects.LoginPage;
import Fusion.pageobjects.SecurityConsolePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class AddUsersInOracleFusionv2 {


        WebDriver driver;
        LoginPage loginPage;
        SecurityConsolePage securityPage;

        @BeforeClass

        public void setup() {
            // ------------------ WebDriver Setup ----------------------
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // ------------------ Page Object Initialization -----------
            loginPage = new LoginPage(driver);
            securityPage = new SecurityConsolePage(driver);
        }
        @Test(dataProvider = "FusionUserData")
        public void createFusionUser() throws InterruptedException {
            // ------------------ STEP 1 : Open Oracle Fusion --------
            driver.get("https://ekwm-dev1.login.us6.oraclecloud.com/");

            // ------------------ STEP 2 : Login ---------------------
            loginPage.login("JD", "Fusion@123");

            // ------------------ STEP 3 : Open Security Console -----
            securityPage.openSecurityConsole();

            // ------------------ STEP 4 : Click Users ----------------
            securityPage.clickCreateUser();

            // ------------------ STEP 5 : Add New User ---------------
            //securityPage.clickAddUser();

            // ------------------ STEP 6 : Fill User Details ----------
            securityPage.enterUserDetails(
                    "test",
                    "demouser4",
                    "demouser4@test.com",
                    "9876543210",
                    "Oracle@123",
                    "Oracle@123"

            );

            // ------------------ STEP 7 : Add Role -------------------
            securityPage.addRole("ORA_PER_EMPLOYEE_ABSTRACT");

            // ------------------ STEP 8 : Save User ------------------
            securityPage.saveUser();

            System.out.println("User created successfully!");

        }

    @DataProvider(name = "FusionUserData")
    public Iterator<Object[]> getFusionUserData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/src/test/java/resources/CreateUser_TestData.xlsx";
        List<Map<String, String>> testData = ExcelReader.getTestData(filePath, "CreateUser_TestData");

        List<Object[]> dataProvider = new ArrayList<>();
        for (Map<String, String> row : testData) {
            dataProvider.add(new Object[]{row});
        }

        return dataProvider.iterator();
    }

        @AfterClass
        public void tearDown() {
            driver.quit();
        }
    }



