package Fusion.tests;
import Fusion.TestComponent.BaseTest;
import Fusion.pageobjects.*;
import Fusion.resources.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Fusion.TestComponent.ExcelReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.testng.annotations.DataProvider;

public class AddUsersInOracleFusion extends BaseTest {



            @Test(dataProvider = "CreateUserData")
            public void createFusionUser(Map<String, String> data) throws InterruptedException {

                LoginPage loginPage=new LoginPage(getDriver());
                HireAnEmployee Hire=new HireAnEmployee(getDriver());
                PersonManagement PM=new PersonManagement(getDriver());
                SecurityConsolePage securityPage = new SecurityConsolePage(getDriver());
                System.out.println("STEP 1: Logging in to Fusion");

                loginPage.login("JD", "Fusion@123");

                System.out.println("STEP 2: Hiring employee with data: " + data);

                Hire.setHireAnEmployee(data.get("hireReason"),data.get("legalEmployerField"),data.get("lastNameField"),data.get("firstNameField"),data.get("addressLine1"),data.get("cityField"),data.get("addressLine2"),data.get("pinCodeField"),data.get("businessUnitField"),data.get("assignment"),data.get("manager"),data.get("timecardvalue"));
                System.out.println("STEP 3: Searching for newly created user");
                String user = PM.searchByName(data.get("firstNameField"));
                System.out.println("User found: " + user);
                System.out.println("STEP 4: Opening Security Console");
                securityPage.openSecurityConsole();
                System.out.println("STEP 5: Searching user in User Accounts");
                PM.searchUserInUserAccounts(user);

                System.out.println("User created successfully!");

            }


    @DataProvider(name = "CreateUserData")
    public Iterator<Object[]> getMSDData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/src/test/java/resources/CreateUser_TestData.xlsx";
        List<Map<String, String>> testData = ExcelReader.getTestData(filePath, "Sheet1");

        List<Object[]> dataProvider = new ArrayList<>();
        for (Map<String, String> row : testData) {
            dataProvider.add(new Object[]{row});
        }

        return dataProvider.iterator();
    }

        }



