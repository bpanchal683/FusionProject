package Fusion.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import Fusion.pageobjects.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class ServiceNowTestInactiveUser {

    public WebDriver driver;
    WebDriverWait wait;
    String usernameValue = "dummy71 test71";

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void verifyInactiveUserStatus() throws Exception {

        driver.get("https://itconvergencedev.service-now.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated( By.id ("user_name")));

        ServiceNowPage sn = new ServiceNowPage(driver);
        sn.login("JD", "Jitesh@123");

        Thread.sleep(3000);

        // Extract Last Name
        String lastName = usernameValue.split(" ")[1];

        // Open SNOW Users Page
        driver.get("https://itconvergencedev.service-now.com/nav_to.do?uri=sys_user_list.do?sysparm_query=nameLIKE"
                + lastName);

        wait.until(ExpectedConditions.urlContains("sys_user_list.do"));
        System.out.println("Navigated to Users Page UI");

        // API Call
        RestAssured.baseURI = "https://itconvergencedev.service-now.com";
        String query = "nameLIKE" + lastName;

        Response response = RestAssured.given()
                .auth().preemptive().basic("JD", "Jitesh@123")
                .queryParam("sysparm_query", query)
                .queryParam("sysparm_fields", "name,active")
                .queryParam("sysparm_limit", "1")
                .get("/api/now/table/sys_user")
                .then().extract().response();

        System.out.println("API Response: " + response.asString());
        JsonPath json = response.jsonPath();

        // Case 1: User Deleted → PASS
        if (json.getList("result").isEmpty()) {
            injectBanner(driver, "User Deleted / Not Found", false);
            System.out.println(" TEST PASS: User Not Found → Expected INACTIVE");
            Assert.assertTrue(true);
            return;
        }

        // Case 2: User Exists
        String fullName = json.getString("result[0].name");
        boolean isActive = json.getBoolean("result[0].active");

        System.out.println("API Verified → " + fullName + " : Active = " + isActive);

        // Expected -> inactive
        if (!isActive) {
            injectBanner(driver, "User: " + fullName + " | Status: INACTIVE ", false);
            System.out.println(" TEST PASS: User is INACTIVE as expected");
            Assert.assertTrue(true);
        } else {
            injectBanner(driver, "User: " + fullName + " | STILL ACTIVE ", true);
            Assert.fail(" User is still ACTIVE but expected INACTIVE");
        }

        Thread.sleep(3000);
    }

    public void injectBanner(WebDriver driver, String msg, boolean active) {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        String statusColor = active ? "#1FA53C" : "red";

        String banner =
                "let div=document.createElement('div');" +
                        "div.style.position='fixed';" +
                        " div.style.top ='90px';" +
                        "div.style.right='20px';" +
                        "div.style.padding='10px 20px';" +
                        "div.style.background='" + statusColor + "';" +
                        "div.style.color='white';" +
                        "div.style.fontSize='18px';" +
                        "div.style.fontWeight='700';" +
                        "div.style.borderRadius='8px';" +
                        "div.style.zIndex='99999';" +
                        "div.innerText='" + msg + "';" +
                        "document.body.appendChild(div);";

        jsExec.executeScript(banner);
    }

    /*
     * @AfterClass public void tearDown() { if (driver != null) driver.quit(); }
     */
}