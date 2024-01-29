package com.anhtester.listeners;

import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.helpers.SystemHelper;
import com.anhtester.managers.PageManager;
import com.anhtester.reports.AllureManager;
import com.microsoft.playwright.Page.ScreenshotOptions;
import io.qameta.allure.Allure;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AllureListener implements TestLifecycleListener {

    @Override
    public void beforeTestSchedule(TestResult result) {
    }

    @Override
    public void afterTestSchedule(TestResult result) {
    }

    @Override
    public void beforeTestUpdate(TestResult result) {
    }

    @Override
    public void afterTestUpdate(TestResult result) {
    }

    @Override
    public void beforeTestStart(TestResult result) {
    }

    @Override
    public void afterTestStart(TestResult result) {
    }

    @Override
    public void beforeTestStop(TestResult result) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

        ScreenshotOptions screenshotOptions = new ScreenshotOptions();
        if (result.getStatus() == Status.FAILED || result.getStatus() == Status.SKIPPED || result.getStatus() == Status.BROKEN) {
            if (PageManager.getPage() != null) {
                Allure.addAttachment(result.getName() + "_Failed_Screenshot", new ByteArrayInputStream(PageManager.getPage().screenshot(screenshotOptions.setPath(Paths.get(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("SCREENSHOT_PATH") + File.separator + dateFormat.format(new Date()) + ".png")))));
            }
        }
    }

    @Override
    public void afterTestStop(TestResult result) {
    }

    @Override
    public void beforeTestWrite(TestResult result) {
    }

    @Override
    public void afterTestWrite(TestResult result) {
    }

}
