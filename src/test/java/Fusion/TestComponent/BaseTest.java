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
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage lp;
    public String browserName;
    public String url;
    public String userName;
    public String password;
    public MsdSignInPage msdSignInPage;


    public void readConfig() throws IOException {
        Properties prop=new Properties();
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/Fusion/resources/config.properties");
        prop.load(fis);
        browserName=prop.getProperty("browser");
        url=prop.getProperty("url");
        userName=prop.getProperty("user");
        password=prop.getProperty("password");
    }

    public WebDriver initializeDriver(String browser) throws IOException {
        readConfig();

        if(browser==null || browser.isEmpty())
        {
            browser=browserName;
        }

        if(browser.contains("chrome"))
        {
            ChromeOptions options=new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if(browser.contains("headless"))
            {
                options.addArguments("headless");
            }
            driver=new ChromeDriver(options);
        }
        else if (browser.contains("edge"))
        {
            EdgeOptions options=new EdgeOptions();
            WebDriverManager.edgedriver().setup();
            if(browser.contains("headless"))
            {
                options.addArguments("headless");
            }
            driver=new EdgeDriver(options);
        }
        else if (browser.contains("firefox"))
        {
            FirefoxOptions options=new FirefoxOptions();
            WebDriverManager.firefoxdriver().setup();
            if(browser.contains("headless"))
            {
                options.addArguments("headless");
            }
            driver=new FirefoxDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

//    @Parameters("browser")
//    @BeforeMethod
//    public LandingPage launchApplication(@Optional("") String browser) throws IOException {
//        driver=initializeDriver(browser);
//        lp=new LandingPage(driver);
//        lp.goTo(url);
//        return lp;
//    }
//
//    @AfterMethod
//    public void tearDown()
//    {
//        driver.close();
//    }

//    @BeforeMethod
//    public MsdSignInPage launchMsdApplication() throws IOException {
//        driver=initializeDriver();
//        msdSignInPage=new MsdSignInPage(driver);
//        msdSignInPage.goTo(url);
//        return msdSignInPage;
//    }

//    public String getScreenshotASBase64(WebDriver driver) throws IOException {
//        TakesScreenshot ts=(TakesScreenshot)driver;
//        String base64Screenshot=ts.getScreenshotAs(OutputType.BASE64);
//        return base64Screenshot;
//    }

    @BeforeTest
    public void setUp() throws IOException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "IN_Note 1");
        desiredCapabilities.setCapability("appium:udid", "RWUCIRQ8R8CEZPIJ");
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appium:browserName", "Chrome");
        desiredCapabilities.setCapability("appium:chromedriverExecutable", "C:\\Users\\bpanchal\\Downloads\\chromedriver-win64 (2)\\chromedriver-win64\\chromedriver.exe");
//        desiredCapabilities.setCapability("appPackage","com.android.chrome");
//        desiredCapabilities.setCapability("appActivity","com.google.android.apps.chrome.Main");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), desiredCapabilities);
        driver.manage().window().setSize(new Dimension(1280, 720));
        readConfig();
        lp=new LandingPage(driver);
        lp.goTo(url);
    }
}
