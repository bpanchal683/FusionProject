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

public class ServiceNowTestActiveUser {

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
    public void verifyActiveUserStatus() throws Exception {

        driver.get("https://itconvergencedev.service-now.com/");

        wait.until(ExpectedConditions.visibilityOfElementLocated(  By.id  ("user_name")));
        ServiceNowPage sn = new ServiceNowPage(driver);
        sn.login("JD", "Jitesh@123");

        Thread.sleep(4000);

        driver.get("https://itconvergencedev.service-now.com/nav_to.do?uri=sys_user_list.do?sysparm_query=nameLIKE"
                + usernameValue.replace(" ", "%20"));

        wait.until(ExpectedConditions.urlContains("sys_user_list.do"));
        System.out.println("Navigated to Users Page UI");


        RestAssured.baseURI = "https://itconvergencedev.service-now.com";

        String lastName = usernameValue.split(" ")[1];
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

        if (json.getList("result").isEmpty()) {
            injectBanner(driver, "User Not Found ", false);
            Assert.fail(" Expected ACTIVE user but not found in SNOW!");
        }

        String fullName = json.getString("result[0].name");
        boolean isActive = json.getBoolean("result[0].active");

        System.out.println("API Verified → " + fullName + " : Active = " + isActive);

        injectBanner(driver, "User: " + fullName + " | Status: ACTIVE", isActive);
        Assert.assertTrue(isActive, " User is NOT active but expected active");

        Thread.sleep(3000);
    }

    public void injectBanner(WebDriver driver, String msg, boolean active) {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        String statusColor = active ? "#1FA53C" : "red";

        String banner =
                "let div=document.createElement('div');" +
                        "div.style.position='fixed';" +
                        "  div.style.top  ='90px';" +
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