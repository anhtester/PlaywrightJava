package com.anhtester.listeners;

import com.anhtester.helpers.CaptureHelper;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebKeyword;
import com.anhtester.reports.AllureManager;
import com.anhtester.reports.ExtentReportManager;
import com.anhtester.reports.ExtentTestManager;
import com.anhtester.utils.LogUtils;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext result) {
        PropertiesHelper.loadAllFiles();
    }

    @Override
    public void onFinish(ITestContext result) {
        //Kết thúc và thực thi Extents Report
        ExtentReportManager.getExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("*********" + result.getName() + "*********");

        if (PropertiesHelper.getValue("VIDEO_RECORD").equals("true")) {
            CaptureHelper.startRecord(result.getName());
        }

        //Bắt đầu ghi 1 TCs mới vào Extent Report
        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("==> " + result.getName() + " is successfully.");

        if (PropertiesHelper.getValue("SCREENSHOT_PASS").equals("true")) {
            CaptureHelper.takeScreenshot(result.getName());
        }

        if (PropertiesHelper.getValue("VIDEO_RECORD").equals("true")) {
            WebKeyword.sleep(0.5);
            CaptureHelper.stopRecord();
        }

        //Extent Report
        ExtentTestManager.logMessage(Status.PASS, result.getName() + " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("==> " + result.getName() + " is FAIL.");

        if (PropertiesHelper.getValue("SCREENSHOT_FAIL").equals("true")) {
            CaptureHelper.takeScreenshot(result.getName());
        }

        if (PropertiesHelper.getValue("VIDEO_RECORD").equals("true")) {
            WebKeyword.sleep(0.5);
            CaptureHelper.stopRecord();
        }

        //Extent Report
        ExtentTestManager.addScreenShot(result.getName());
        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is failed.");

        //Allure Report
        //AllureManager.saveTextLog(result.getName() + " is failed.");
        AllureManager.saveScreenshotPNG();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn("*********" + result.getName() + " is SKIPPED *********");

        if (PropertiesHelper.getValue("VIDEO_RECORD").equals("true")) {
            WebKeyword.sleep(0.5);
            CaptureHelper.stopRecord();
        }

        //Extent Report
        ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LogUtils.info("onTestFailedButWithinSuccessPercentage: " + result.getName());
    }

}