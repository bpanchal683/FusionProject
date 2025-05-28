package Fusion.TestComponent;

import Fusion.AbstractComponents.ExtentTestManager;
import Fusion.resources.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.IOException;
import Fusion.AbstractComponents.AbstractComponent;

import static Fusion.AbstractComponents.ExtentTestManager.getExtentTest;

public class Listeners extends BaseTest implements ITestListener {

    ExtentTest test;
    ExtentReports extent=ExtentReporterNG.getExtentReportObject();
    //private static ThreadLocal<ExtentTest> extentTestThreadLocal=new ThreadLocal<ExtentTest>();


    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("➡️ Test started: " + result.getMethod().getMethodName());
        test=extent.createTest(result.getMethod().getMethodName());
       // extentTestThreadLocal.set(test);
        ExtentTestManager.setExtentTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        System.out.println("✅ Test passed: " + result.getMethod().getMethodName());
        //test.log(Status.PASS,"Test Passed");
        //extentTestThreadLocal.get().log(Status.PASS,"Test Passed");
        getExtentTest().log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest test = getExtentTest();

        if (test == null) {
            System.err.println("❌ ExtentTest is null in onTestFailure for: " + result.getMethod().getMethodName());
            return; // Avoid NullPointerException
        }

        //test.log(Status.FAIL,result.getThrowable());
        //extentTestThreadLocal.get().log(Status.FAIL,result.getMethod().getMethodName());
        getExtentTest().log(Status.FAIL,result.getMethod().getMethodName());
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        //life to driver
        try
        {
            driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        //Screenshot to Report
        String base64Image=null;
        String filePath=null;
        try {
            //base64Image = getScreenshotASBase64(driver);
            filePath=AbstractComponent.getScreenshot(driver);
        } catch (IOException e) {

            e.printStackTrace();
        }

        //test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
        //extentTestThreadLocal.get().addScreenCaptureFromBase64String(base64Image);
        //extentTestThreadLocal.get().addScreenCaptureFromPath(filePath);
        getExtentTest().addScreenCaptureFromPath(filePath);

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠️ Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("⚠️ Test failed but within success percentage: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        System.out.println("⏰ Test failed due to timeout: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("🔷 Suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("✅ Suite finished: " + context.getName());
        extent.flush();
    }

}
