package PageClass;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class ReportListener implements ITestListener {
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReports extent = Baseclass.extent;
        if (extent != null) {
            ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
            test.set(extentTest);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (extentTest != null) {
            extentTest.log(Status.PASS, "Test passed");
        }
        Allure.addAttachment("Test Passed", new ByteArrayInputStream("Test passed".getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (extentTest != null) {
            extentTest.log(Status.FAIL, result.getThrowable());
        }
        Allure.addAttachment("Test Failure", new ByteArrayInputStream(result.getThrowable().toString().getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (extentTest != null) {
            extentTest.log(Status.SKIP, "Test skipped");
        }
        Allure.addAttachment("Test Skipped", new ByteArrayInputStream("Test skipped".getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public void onStart(ITestContext context) {
        // No action needed
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReports extent = Baseclass.extent;
        if (extent != null) {
            extent.flush();
        }
    }
}
