package com.anhtester.listeners;

import com.anhtester.constants.AppConfig;
import com.anhtester.helpers.CaptureHelper;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.helpers.SystemHelper;
import com.anhtester.keywords.WebKeyword;
import com.anhtester.managers.PageManager;
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
        ExtentReportManager.getExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("➡\uFE0F " + result.getName());

        if (AppConfig.VIDEO_RECORD) {
            CaptureHelper.startRecord(result.getName());
        }

        PageManager.startTracing();

        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("✅ " + result.getName() + " is PASSED.");

        if (AppConfig.TRACE_VIEWER) {
            PageManager.closeTracing(AppConfig.TRACING_PATH + "trace_" + result.getName() + "_" + SystemHelper.getDateTimeNowAndMakeSlug() + ".zip");
        }

        if (AppConfig.SCREENSHOT_PASS) {
            CaptureHelper.takeScreenshot(result.getName());
        }

        if (AppConfig.VIDEO_RECORD) {
            WebKeyword.sleep(0.5);
            CaptureHelper.stopRecord();
        }

        ExtentTestManager.logMessage(Status.PASS, result.getName() + " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("❌ " + result.getName() + " is FAILED.");

        if (AppConfig.TRACE_VIEWER) {
            PageManager.closeTracing(AppConfig.TRACING_PATH + "trace_" + result.getName() + "_FAILED_" + SystemHelper.getDateTimeNowAndMakeSlug() + ".zip");
        }

        if (AppConfig.SCREENSHOT_FAIL) {
            CaptureHelper.takeScreenshot(result.getName());
        }

        if (AppConfig.VIDEO_RECORD) {
            WebKeyword.sleep(1);
            CaptureHelper.stopRecord();
        }

        //Extent Report
        ExtentTestManager.addScreenShot(result.getName());
        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is failed.");

        //Allure Report
        //AllureManager.saveTextLog(result.getName() + " is failed.");
        //AllureManager.saveScreenshotPNG();

//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
//        Page.ScreenshotOptions screenshotOptions = new Page.ScreenshotOptions();
//        if (result.getStatus() == 2 || result.getStatus() == 3) {
//            if (PageManager.getPage() != null) {
//                Allure.addAttachment(result.getName() + "_Failed_Screenshot", new ByteArrayInputStream(PageManager.getPage().screenshot(screenshotOptions.setPath(Paths.get(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("SCREENSHOT_PATH") + File.separator + dateFormat.format(new Date()) + ".png")))));
//            }
//        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn("*********" + result.getName() + " is SKIPPED *********");

        if (PropertiesHelper.getValue("VIDEO_RECORD").equals("true")) {
            WebKeyword.sleep(1);
            CaptureHelper.stopRecord();
        }

        //Extent Report
        ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
    }

}