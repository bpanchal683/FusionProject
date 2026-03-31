package Fusion.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import Fusion.pageobjects.ServiceNowPage;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.MediaEntityBuilder;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

public class ServiceNowTestActiveUser {

    public WebDriver driver;
    WebDriverWait wait;

    ExtentReports extent;
    ExtentTest test;

    String usernameValue = "dummy219 test219=";

    @BeforeClass
    public void setup() {

        // ---------------- Extent Report Setup -----------------
        ExtentSparkReporter reporter = new ExtentSparkReporter("./SNOW_UserStatus_Report.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);

        test = extent.createTest("Verify Active User Status in ServiceNow");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void verifyActiveUserStatus() throws Exception {
        System.out.println("STEP 1: Opening ServiceNow login page");

        driver.get("https://itconvergencedev.service-now.com/");
        test.info("Opened SNOW Login Page");

        wrappedWait(By.id("user_name"));
        System.out.println("STEP 2: Logging into ServiceNow");

        ServiceNowPage sn = new ServiceNowPage(driver);
        sn.login("JD", "Jitesh@123");
        test.pass("Logged into ServiceNow");

        Thread.sleep(4000);
        System.out.println("STEP 3: Navigating to user list for " + usernameValue);

        // Move directly to user list URL
        driver.get("https://itconvergencedev.service-now.com/nav_to.do?uri=sys_user_list.do?sysparm_query=nameLIKE"
                + usernameValue.replace(" ", "%20"));

        test.info("Navigated to SNOW User List Page");

        wait.until(ExpectedConditions.urlContains("sys_user_list.do"));
        System.out.println("STEP 4: Making API call to fetch user status");

        //------------------- API CALL -------------------
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

        test.info("Fetched User Status via API");

        JsonPath json = response.jsonPath();

        if (json.getList("result").isEmpty()) {
            System.out.println("STEP 5: User not found in ServiceNow!");
            injectBanner(driver, "User Not Found", false);
            test.fail("User NOT FOUND in system!");
            Assert.fail("User not found in SNOW!");
        }

        String fullName = json.getString("result[0].name");
        boolean isActive = json.getBoolean("result[0].active");
        System.out.println("STEP 6: API verified user → " + fullName + " | Active = " + isActive);

        test.pass("API Verified User → " + fullName + " | Active = " + isActive);

        injectBanner(driver, "User: " + fullName + " | Status: ACTIVE", isActive);

        Assert.assertTrue(isActive, "User is NOT Active");
        System.out.println(" Test completed successfully");

        Thread.sleep(3000);
    }

    // -----------------------------------------------------------
    // ✔ HIGHLIGHT ELEMENT
    // -----------------------------------------------------------
    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].style.border='3px solid red'", element);
        js.executeScript(
                "arguments[0].style.border='3px solid #1FA53C';" +
                        "arguments[0].style.boxShadow='0 0 8px #1FA53C';",
                element
        );

        try { Thread.sleep(200); } catch (Exception e) {}

        js.executeScript("arguments[0].style.border=''", element);
    }

    // -----------------------------------------------------------
    // ✔ WRAPPED CLICK (Highlight + Extent Screenshot)
    // -----------------------------------------------------------
    public void wrappedClick(By locator) {
        try {
            WebElement el = driver.findElement(locator);

            highlightElement(el);
            el.click();

            String screenshot = getScreenshot();

            test.pass("Clicked element: " + locator.toString(),
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());

        } catch (Exception e) {
            test.fail("FAILED to click: " + locator + " | Reason: " + e.getMessage());
        }
    }

    // -----------------------------------------------------------
    // ✔ WRAPPED WAIT (wait + highlight)
    // -----------------------------------------------------------
    public WebElement wrappedWait(By locator) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        highlightElement(el);
        return el;
    }

    // -----------------------------------------------------------
    // ✔ GENERATE SCREENSHOT FOR REPORT
    // -----------------------------------------------------------
    public String getScreenshot() throws IOException {
        String path = "./SNOW_" + System.currentTimeMillis() + ".png";
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] data = ts.getScreenshotAs(OutputType.BYTES);

        java.nio.file.Files.write(java.nio.file.Paths.get(path), data);
        return path;
    }

    // -----------------------------------------------------------
    // ✔ YOUR EXISTING GREEN/RED STATUS BANNER
    // -----------------------------------------------------------
    public void injectBanner(WebDriver driver, String msg, boolean active) {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        String statusColor = active ? "#1FA53C" : "red";

        String banner =
                "let div=document.createElement('div');" +
                        "div.style.position='fixed';" +
                        "div.style.top='90px';" +
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

    @AfterClass
    public void tearDown() {
        extent.flush();
        driver.quit();
    }
}
