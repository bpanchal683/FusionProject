package Fusion.TestComponent;

import Fusion.pageobjects.LandingPage;
import Fusion.pageobjects.MsdSignInPage;

import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage lp;
    public MsdSignInPage msdSignInPage;

    public String browserName;
    public String url;
    public String userName;
    public String password;

    // ---------------------- READ CONFIG ----------------------
    public void readConfig() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/main/java/Fusion/resources/config.properties"
        );
        prop.load(fis);

        browserName = prop.getProperty("browser");
        url = prop.getProperty("url");
        userName = prop.getProperty("user");
        password = prop.getProperty("password");
    }

    // ---------------------- WEB DRIVER INIT ----------------------
    public WebDriver initializeDriver(String browser) throws IOException {
        readConfig();

        if (browser == null || browser.isEmpty()) {
            browser = browserName;
        }

        if (browser.toLowerCase().contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if (browser.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
        }
        else if (browser.toLowerCase().contains("edge")) {
            EdgeOptions options = new EdgeOptions();
            WebDriverManager.edgedriver().setup();
            if (browser.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new EdgeDriver(options);
        }
        else if (browser.toLowerCase().contains("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            WebDriverManager.firefoxdriver().setup();
            if (browser.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new FirefoxDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        return driver;
    }

    // ---------------------- MOBILE (APPIUM) INIT ----------------------
    @BeforeTest
    public void setUp() throws IOException {

        // If running Mobile-Appium Test
        if (browserName != null && browserName.equalsIgnoreCase("mobile")) {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("appium:platformName", "Android");
            capabilities.setCapability("appium:deviceName", "SM-A325F");
            capabilities.setCapability("appium:udid", "RZ8TA0SENMP");
            capabilities.setCapability("appium:automationName", "UiAutomator2");
            capabilities.setCapability("appium:browserName", "Chrome");

            capabilities.setCapability("appium:chromedriverExecutable",
                    "C:\\Users\\jitesh\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
        } else {

            // If running normal Web Test
            driver = initializeDriver(browserName);
        }

        readConfig();

        // Launch application
        lp = new LandingPage(driver);
        lp.goTo(url);
    }

    // ---------------------- CLEANUP ----------------------
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignored) {}
        }
    }
}
