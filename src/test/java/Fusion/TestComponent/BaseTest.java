package Fusion.TestComponent;

import Fusion.pageobjects.LandingPage;
import Fusion.pageobjects.MsdSignInPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

import java.awt.*;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    // ThreadLocal driver ensures each thread has its own instance
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public LandingPage lp;
    public MsdSignInPage msdSignInPage;

    public String browserName;
    public String url;
    public String userName;
    public String password;
    public boolean isCrossBrowserEnabled;

    public WebDriver getDriver() {
        return driver.get();
    }

    public void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

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
        isCrossBrowserEnabled = Boolean.parseBoolean(prop.getProperty("crossbrowser", "false"));
    }

    public WebDriver initializeDriver(String browser) throws IOException {
        readConfig();

        if (browser == null || browser.isEmpty()) {
            browser = browserName;
        }

        WebDriver localDriver = null;

        if (browser.toLowerCase().contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if (browser.contains("headless")) {
                options.addArguments("--headless=new");
            }
            localDriver = new ChromeDriver(options);

        } else if (browser.toLowerCase().contains("edge")) {
            EdgeOptions options = new EdgeOptions();
            try {
                // Try WebDriverManager first
                WebDriverManager.edgedriver().setup();
            } catch (Exception e) {
                // Fallback to local driver if download fails
                System.setProperty("webdriver.edge.driver", "C:\\drivers\\msedgedriver.exe");
            }
            if (browser.contains("headless")) {
                options.addArguments("headless");
            }
            localDriver = new EdgeDriver(options);

        } else if (browser.toLowerCase().contains("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            WebDriverManager.firefoxdriver().setup();
            if (browser.contains("headless")) {
                options.addArguments("-headless");
            }
            localDriver = new FirefoxDriver(options);
        }

        localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // ✅ Dynamic split screen setup for parallel runs
        try {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = (int) screenSize.getWidth();
            int screenHeight = (int) screenSize.getHeight();
            int halfWidth = screenWidth / 2;

            localDriver.manage().window().setSize(new org.openqa.selenium.Dimension(halfWidth, screenHeight));

            if (Thread.currentThread().getId() % 2 == 0) {
                localDriver.manage().window().setPosition(new Point(0, 0)); // left half
            } else {
                localDriver.manage().window().setPosition(new Point(halfWidth, 0)); // right half
            }
        } catch (Exception e) {
            System.out.println("Could not set window position: " + e.getMessage());
        }

        return localDriver;
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional String browser) throws IOException {
        readConfig();
        WebDriver localDriver;

        if (isCrossBrowserEnabled && browser != null) {
            // Cross-browser mode: browser passed from TestNG XML or Jenkins pipeline
            localDriver = initializeDriver(browser);
        } else {
            // Single-browser mode: use config.properties value
            localDriver = initializeDriver(browserName);
        }

        setDriver(localDriver);
        lp = new LandingPage(getDriver());
        lp.goTo(url);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove(); // ✅ clean up thread-local
        }
    }
}
