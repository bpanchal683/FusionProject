package Fusion.TestComponent;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
//import io.appium.java_client.MobileElement;
import java.net.URL;
import java.time.Duration;


public class AppiumBaseTest {

    public AndroidDriver driver;


    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "IN_Note 1");
        desiredCapabilities.setCapability("appium:udid", "RWUCIRQ8R8CEZPIJ");
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appium:appPackage", "com.makemytrip");
        desiredCapabilities.setCapability("appium:appActivity","com.mmt.travel.app.home.ui.SplashActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), desiredCapabilities);
    }

    @Test
    public void test() throws InterruptedException {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));

         System.out.println("Application Started");
         Thread.sleep(7000);
        driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/skipTextView']")).click();
        //Thread.sleep(1000);
//        driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/inputFieldChild']")).sendKeys("6375187830");
//        Thread.sleep(1000);
//        driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/btn_continue']")).click();
//        Thread.sleep(1000);
//        driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/pwd_option']")).click();
//        Thread.sleep(10000);
//        driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/iv_close']")).click();
//        Thread.sleep(1000);
//        driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/container']")).click();
        //driver.findElement(By.xpath("//*[@id='til_password']")).click();
        //Thread.sleep(000);
//        driver.findElement(By.xpath("(//*[@index='3'])[1]")).sendKeys("MakeMyTrip@123");
//        Thread.sleep(1000);
//        driver.findElement(By.xpath("//*[@text='Submit']"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id='com.makemytrip:id/iv_socialButton']")));
        driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/iv_socialButton']")).click();
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@index='1'])[4]")));
        driver.findElement(By.xpath("(//*[@index='1'])[4]")).click();
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id='com.makemytrip:id/iv_close']")));
        driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/iv_close']")).click();
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id='com.makemytrip:id/container']")));
        driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/container']")).click();
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id='com.makemytrip:id/search_button_flat']")));
        driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/search_button_flat']")).click();
        //Thread.sleep(7000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id='com.makemytrip:id/snack_bar_footer_left']")));
        driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/snack_bar_footer_left']")).click();
        //driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/snack_bar_footer_middle']")).click();
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@resource-id='com.makemytrip:id/top_area'])[1]")));
        driver.findElement(By.xpath("(//*[@resource-id='com.makemytrip:id/top_area'])[1]")).click();
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@resource-id='com.makemytrip:id/btnBookNow'])[1]")));
        driver.findElement(By.xpath("(//*[@resource-id='com.makemytrip:id/btnBookNow'])[1]")).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id='com.makemytrip:id/fare_family_cont']")));
        driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/fare_family_cont']")).click();
        //Thread.sleep(12000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id='com.makemytrip:id/review_tv']")));
        driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/review_tv']")).click();
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='Add new adult']")));
        driver.findElement(By.xpath("//*[@text='Add new adult']")).click();
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='MALE']")));
        driver.findElement(By.xpath("//*[@text='MALE']")).click();
        driver.findElement(By.xpath("//*[@text='First & Middle Name']")).sendKeys("BK");
        driver.findElement(By.xpath("//*[@text='Last Name']")).sendKeys("Panchal");
        //Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id='com.makemytrip:id/confirm_button']")));
        driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/confirm_button']")).click();
        //Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id='com.makemytrip:id/review_tv']")));
        driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/review_tv']")).click();
        //Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='Mobile No']")));
        driver.findElement(By.xpath("//*[@text='Mobile No']")).sendKeys("6375187830");
        //Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='CONFIRM']")));
        driver.findElement(By.xpath("//*[@text='CONFIRM']")).click();
        //Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id='com.makemytrip:id/review_tv']")));
        driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/review_tv']")).click();
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='Book without Trip Secure']")));
        driver.findElement(By.xpath("//*[@text='Book without Trip Secure']")).click();
        //Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id='com.makemytrip:id/snack_bar_footer_right']")));
        driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/snack_bar_footer_right']")).click();
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id='com.makemytrip:id/review_tv']")));
        driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/review_tv']")).click();
        driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/review_tv']")).click();
        driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/review_tv']")).click();
        //driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/review_tv']")).click();
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='Confirm & continue']")));
        driver.findElement(By.xpath("//*[@text='Confirm & continue']")).click();
        //Thread.sleep(5000);
        //driver.findElement(By.xpath("//*[@resource-id='com.makemytrip:id/snack_bar_footer_right']")).click();
        //Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@index='8'])[1]")));
        driver.findElement(By.xpath("(//*[@index='8'])[1]")).click();
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='android.widget.EditText'])[1]")));
        driver.findElement(By.xpath("(//*[@class='android.widget.EditText'])[1]")).sendKeys("9848123697845231");
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='MM/YY']")));
        driver.findElement(By.xpath("//*[@text='MM/YY']")).sendKeys("0931");
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='CVV']")));
        driver.findElement(By.xpath("//*[@text='CVV']")).sendKeys("688");
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id='test_pay_name_on_card']")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id='test_pay_name_on_card']")));
        driver.findElement(By.xpath("//*[@resource-id='test_pay_name_on_card']")).sendKeys("Manoj");
        driver.findElement(By.xpath("//*[@resource-id='test_state']")).sendKeys("UP");
        driver.findElement(By.xpath("//*[@resource-id='test_city']")).sendKeys("Noida");
        driver.findElement(By.xpath("//*[@resource-id='test_pincode']")).sendKeys("321309");
        driver.findElement(By.xpath("//*[@resource-id='test_address']")).sendKeys("Noida");
        driver.findElement(By.xpath("//*[@text='SAVE CARD & PAY']")).click();

    }

//    @AfterTest
//    public void close()
//    {
//        driver.quit();
//    }
}
