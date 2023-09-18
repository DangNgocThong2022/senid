package utils.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.extentreports.ExtentManager;
import utils.logs.Log;


public class TestListener implements ITestListener {


    //Extent Report Declarations
    private static ExtentReports extent = ExtentManager.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public synchronized void onStart(ITestContext context) {
        Log.info("onStart method: " + context.getName());
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        Log.info("onFinish method: " + context.getName());
        extent.flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        Log.info(getTestMethodName(result) + " test is starting.");
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
        test.set(extentTest);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        Log.info(getTestMethodName(result) + " test is succeed.");
        test.get().pass("Test passed");
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        Log.error(getTestMethodName(result) + " test is failed.");
        test.get().fail(result.getThrowable());
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        Log.warn(getTestMethodName(result) + " test is skipped.");
        test.get().skip(result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Log.info("Test failed but it is in defined success ratio " + getTestMethodName(result));
    }
}
