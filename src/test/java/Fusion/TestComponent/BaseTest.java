package Fusion.TestComponent;

import Fusion.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage lp;
    public String browserName;
    public String url;
    public String userName;
    public String password;


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

    @BeforeMethod
    public LandingPage launchApplication() throws IOException {
        driver=initializeDriver();
        lp=new LandingPage(driver);
        lp.goTo(url);
        return lp;
    }

    @AfterMethod
    public void tearDown()
    {
        driver.close();
    }

//    public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
//        TakesScreenshot ts=(TakesScreenshot)driver;
//        File source=ts.getScreenshotAs(OutputType.FILE);
//        String timestamp = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
//        String scrPath=System.getProperty("user.dir")+"/reports/"+ testCaseName + timestamp +".png";
//        File file=new File(scrPath);
////        String relativePath = "./reports/" + testCaseName + timestamp + ".png";
////        File file = new File(relativePath);
//        FileUtils.copyFile(source,file);
//        return scrPath;
//    }

    public String getScreenshotASBase64(WebDriver driver) throws IOException {
        TakesScreenshot ts=(TakesScreenshot)driver;
        String base64Screenshot=ts.getScreenshotAs(OutputType.BASE64);
        return base64Screenshot;
    }
}
