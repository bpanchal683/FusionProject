package Fusion.resources;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReporterNG {

       public static ExtentReports getExtentReportObject()
       {
            String path=System.getProperty("user.dir")+"/reports/extent.html";
            ExtentSparkReporter reporter=new ExtentSparkReporter(path);
            reporter.config().setDocumentTitle("Test Results");
            reporter.config().setReportName("Test Execution Results");

            ExtentReports extent=new ExtentReports();
            extent.attachReporter(reporter);
            return extent;
       }
}
