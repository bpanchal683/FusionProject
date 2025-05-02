package Fusion.AbstractComponents;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    // ThreadLocal to hold the ExtentTest instance for each thread
    private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    // Set the current ExtentTest
    public static void setExtentTest(ExtentTest test) {
        extentTestThreadLocal.set(test);
    }

    // Get the current ExtentTest
    public static ExtentTest getExtentTest() {
        return extentTestThreadLocal.get();
    }

    // Remove the current ExtentTest from ThreadLocal
    public static void removeExtentTest() {
        extentTestThreadLocal.remove();
    }
}
