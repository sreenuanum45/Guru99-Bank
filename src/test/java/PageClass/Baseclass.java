package PageClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.awt.Desktop;
import java.io.File;

public class Baseclass {
    protected static ExtentReports extent;
    protected static ExtentTest test;
    private static ExtentSparkReporter sparkReporter;

    @BeforeSuite
    public void setUpReport() {
        sparkReporter = new ExtentSparkReporter("ExtentReport.html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Test Execution Report");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @AfterSuite
    public void tearDownReport() {
        if (extent != null) {
            extent.flush();
        }
        try {
            File htmlFile = new File("ExtentReport.html");
            if (htmlFile.exists()) {
                Desktop.getDesktop().browse(htmlFile.toURI());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
