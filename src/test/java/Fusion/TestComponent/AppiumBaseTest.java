package Fusion.TestComponent;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumBaseTest {

    public AndroidDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
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
    }

    @Test
    public void test()
    {
        driver.get("https://ekwm-test.login.us6.oraclecloud.com/");
    }

//    @AfterTest
//    public void close()
//    {
//        driver.quit();
//    }
}
