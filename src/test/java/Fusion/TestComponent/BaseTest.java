package Fusion.TestComponent;

import Fusion.pageobjects.LandingPage;
import Fusion.pageobjects.MsdSignInPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
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

    public WebDriver initializeDriver() throws IOException {
        readConfig();

        if(browserName.contains("chrome"))
        {
            ChromeOptions options=new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if(browserName.contains("headless"))
            {
                options.addArguments("headless");
            }
            driver=new ChromeDriver(options);
        }
        else if (browserName.contains("edge"))
        {
            EdgeOptions options=new EdgeOptions();
            WebDriverManager.edgedriver().setup();
            if(browserName.contains("headless"))
            {
                options.addArguments("headless");
            }
            driver=new EdgeDriver(options);
        }
        else if (browserName.contains("firefox"))
        {
            FirefoxOptions options=new FirefoxOptions();
            WebDriverManager.firefoxdriver().setup();
            if(browserName.contains("headless"))
            {
                options.addArguments("headless");
            }
            driver=new FirefoxDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }


    public LandingPage launchApplication() throws IOException {
        driver=initializeDriver();
        lp=new LandingPage(driver);
        lp.goTo(url);
        return lp;
    }

//    @AfterMethod
//    public void tearDown()
//    {
//        driver.close();
//    }

    @BeforeMethod
    public MsdSignInPage launchMsdApplication() throws IOException {
        driver=initializeDriver();
        msdSignInPage=new MsdSignInPage(driver);
        msdSignInPage.goTo(url);
        return msdSignInPage;
    }

    public String getScreenshotASBase64(WebDriver driver) throws IOException {
        TakesScreenshot ts=(TakesScreenshot)driver;
        String base64Screenshot=ts.getScreenshotAs(OutputType.BASE64);
        return base64Screenshot;
    }
}
