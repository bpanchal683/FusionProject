package Fusion.tests;
import Fusion.AbstractComponents.AbstractComponent;
import Fusion.TestComponent.BaseTest;
import Fusion.pageobjects.DeleteUser;
import Fusion.pageobjects.LoginPage;
import Fusion.pageobjects.SecurityConsolePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;


public class deleteUsersInOracleFusion extends BaseTest {



    LoginPage loginPage;
    DeleteUser editUser;

    @BeforeClass
    public void setup() throws IOException {



        // Page Object Initialization
        loginPage = new LoginPage(getDriver());
        editUser = new DeleteUser(getDriver());
    }

    @Test
    public void deleteFusionUser() throws InterruptedException {
        System.out.println("STEP 1: Opening Oracle Fusion login page");
        getDriver().get("https://ekwm-dev1.login.us6.oraclecloud.com/");

        System.out.println("STEP 2: Logging in as JD");
        loginPage.login("JD", "Fusion@123");

        System.out.println("STEP 3: Opening Security Console");
        editUser.openSecurityConsole("dummy222");

        System.out.println("STEP 4: Deactivating user");
        editUser.deactivateUser();

        System.out.println("STEP 5: Verifying user deactivation");
        editUser.verifyUserIsDeactivated("dummy222");

        System.out.println("✅ Test completed successfully");
    }


    }



