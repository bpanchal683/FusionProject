package Fusion.TestComponent;

import Fusion.resources.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.annotations.IListeners;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    ExtentTest test;
    ExtentReports extent=ExtentReporterNG.getExtentReportObject();


    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("➡️ Test started: " + result.getMethod().getMethodName());
        test=extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        System.out.println("✅ Test passed: " + result.getMethod().getMethodName());
        test.log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        System.out.println("❌ Test failed: " + result.getMethod().getMethodName());
//        System.out.println("🔴 Reason: " + result.getThrowable());
        test.log(Status.FAIL,result.getThrowable());

        //life to driver
        try
        {
            driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        //Screenshot to Report
        String filePath=null;
        try {
             filePath=getScreenshot(result.getMethod().getMethodName(),driver);
             //test.fail(MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());
        } catch (IOException e) {

            e.printStackTrace();
        }

        test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
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
