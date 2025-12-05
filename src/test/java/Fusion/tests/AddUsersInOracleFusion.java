package Fusion.tests;
import Fusion.pageobjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


    public class AddUsersInOracleFusion {


            WebDriver driver;
            LoginPage loginPage;
            SecurityConsolePage securityPage;
            HireAnEmployee Hire;
            PersonManagement PM;
            ScheduleServices SS;
            @BeforeClass

            public void setup() {
                // ------------------ WebDriver Setup ----------------------
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                // ------------------ Page Object Initialization -----------
                loginPage = new LoginPage(driver);
                securityPage = new SecurityConsolePage(driver);
                Hire = new HireAnEmployee(driver);
                PM = new PersonManagement(driver);
                SS = new ScheduleServices(driver);
            }
            @Test
            public void createFusionUser() throws InterruptedException {
                // ------------------ STEP 1 : Open Oracle Fusion --------
                driver.get("https://ekwm-dev1.login.us6.oraclecloud.com/");

                // ------------------ STEP 2 : Login ---------------------
                loginPage.login("JD", "Fusion@123");

                // ------------------ STEP 3 : Open Security Console -----


                // ------------------ STEP 4 : Click Users ----------------
                //securityPage.clickCreateUser();

                // ------------------ STEP 5 : Add New User ---------------
                //securityPage.clickAddUser();

                // ------------------ STEP 6 : Fill User Details ----------
                //securityPage.enterUserDetails(
                        //"test",
                        //"demouser4",
                        //"demouser4@test.com",
                        //"9876543210",
                        //"Oracle@123",
                        //"Oracle@123"

                //);

                // ------------------ STEP 7 : Add Role -------------------
                //securityPage.addRole("ORA_PER_EMPLOYEE_ABSTRACT");

                // ------------------ STEP 8 : Save User ------------------
                //securityPage.saveUser();



                Hire.setHireAnEmployee();
                String user = PM.searchByName();
                securityPage.openSecurityConsole();
                //PM.searchByName();
                PM.searchUserInUserAccounts(user);
                //PM.searchUser();
                System.out.println("User created successfully!");
//                  SS.sendLDP();
//                  SS.captureProcessId1();
//                  SS.searchID();
//                  SS.retrieveLDP();
//                  SS.captureProcessId2();
//                  SS.searchID();

            }



            @AfterClass
            public void tearDown() {
                driver.quit();
            }
        }



