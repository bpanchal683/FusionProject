package Fusion.TestComponent;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

// 🔥 Disable whole class properly
@Test(enabled = false)
public class AppiumBaseTest {

    public AndroidDriver driver;

    @BeforeTest(enabled = false)
    public void setUp() throws MalformedURLException {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "IN_Note 1");
        desiredCapabilities.setCapability("udid", "RWUCIRQ8R8CEZPIJ");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("browserName", "Chrome");

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"),
                desiredCapabilities
        );
    }

    @Test(enabled = false)
    public void test() {
        driver.get("https://ekwm-test.login.us6.oraclecloud.com/");
    }
}