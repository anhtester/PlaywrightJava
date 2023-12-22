package com.anhtester.keywords;

import com.anhtester.managers.PageManager;
import com.anhtester.reports.AllureManager;
import com.anhtester.reports.ExtentTestManager;
import com.anhtester.utils.LogUtils;
import com.aventstack.extentreports.Status;
import io.qameta.allure.Step;

import java.awt.*;
import java.awt.event.KeyEvent;

public class WebKeyword {
    private static int TIMEOUT = 10;
    private static double STEP_TIME = 0;
    private static int PAGE_LOAD_TIMEOUT = 20;

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void maximizeBrowserOnWindow() {
        Robot rb = null;
        try {
            rb = new Robot();
            rb.keyPress(KeyEvent.VK_WINDOWS);
            rb.keyPress(KeyEvent.VK_UP);
            rb.keyRelease(KeyEvent.VK_UP);
            rb.keyRelease(KeyEvent.VK_WINDOWS);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Step("Navigate to URL: {0}")
    public static void navigate(String url) {
        PageManager.getPage().navigate(url);
        sleep(STEP_TIME);
        LogUtils.info("Navigate to URL: " + url);
        ExtentTestManager.logMessage(Status.INFO, "Navigate to URL: " + url);
    }

    @Step("Click element {0}")
    public static void click(String locator) {
        sleep(STEP_TIME);
        PageManager.getPage().locator(locator).click();
        LogUtils.info("Click element " + locator);
        ExtentTestManager.logMessage(Status.INFO, "Click element " + locator);
    }

    @Step("Fill text {1} on element {0}")
    public static void fill(String locator, String value) {
        sleep(STEP_TIME);
        PageManager.getPage().locator(locator).fill(value);
        LogUtils.info("Fill text " + value + " on element " + locator);
        ExtentTestManager.logMessage(Status.INFO, "Fill text " + value + " on element " + locator);
    }

    @Step("Get text of element {0}")
    public static String textContent(String locator) {
        sleep(STEP_TIME);
        String text = PageManager.getPage().locator(locator).textContent();
        LogUtils.info("Get text of element " + locator + " ==> " + text);
        ExtentTestManager.logMessage(Status.INFO, "Get text of element " + locator);
        ExtentTestManager.logMessage(Status.INFO, "==> Text: " + text);

        AllureManager.saveTextLog("==> " + text);

        return text;
    }
}
