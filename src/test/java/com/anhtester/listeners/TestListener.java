package com.anhtester.listeners;

import com.anhtester.helpers.CaptureHelper;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.helpers.SystemHelper;
import com.anhtester.keywords.WebKeyword;
import com.anhtester.managers.PageManager;
import com.anhtester.reports.AllureManager;
import com.anhtester.reports.ExtentReportManager;
import com.anhtester.reports.ExtentTestManager;
import com.anhtester.utils.LogUtils;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        ExtentReportManager.getExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("➡\uFE0F " + result.getName());

        if (PropertiesHelper.getValue("VIDEO_RECORD").equals("true")) {
            CaptureHelper.startRecord(result.getName());
        }

        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("⭐\uFE0F " + result.getName() + " is successfully.");

        if (PropertiesHelper.getValue("SCREENSHOT_PASS").equals("true")) {
            CaptureHelper.takeScreenshot(result.getName());
        }

        if (PropertiesHelper.getValue("VIDEO_RECORD").equals("true")) {
            WebKeyword.sleep(0.5);
            CaptureHelper.stopRecord();
        }

        ExtentTestManager.logMessage(Status.PASS, result.getName() + " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("❌ " + result.getName() + " is FAIL.");

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
        //AllureManager.saveScreenshotPNG();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        Page.ScreenshotOptions screenshotOptions = new Page.ScreenshotOptions();
        if (result.getStatus() == 2 || result.getStatus() == 3) {
            if (PageManager.getPage() != null) {
                Allure.addAttachment(result.getName() + "_Failed_Screenshot", new ByteArrayInputStream(PageManager.getPage().screenshot(screenshotOptions.setPath(Paths.get(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("SCREENSHOT_PATH") + File.separator + dateFormat.format(new Date()) + ".png")))));
            }
        }
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