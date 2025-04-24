package Fusion.TestComponent;

import Fusion.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

        if(browserName.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
        }
        else if (browserName.contains("edge"))
        {
            WebDriverManager.edgedriver().setup();
            driver=new EdgeDriver();
        }
        else if (browserName.contains("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver();
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

    public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
        TakesScreenshot ts=(TakesScreenshot)driver;
        File source=ts.getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String scrPath=System.getProperty("user.dir")+"/reports/"+ testCaseName+timestamp +".png";
        File file=new File(scrPath);
        FileUtils.copyFile(source,file);
        return scrPath;
    }
}
