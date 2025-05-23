package com.anhtester.keywords;

import com.anhtester.constants.AppConfig;
import com.anhtester.helpers.SystemHelper;
import com.anhtester.managers.PageManager;
import com.anhtester.reports.AllureManager;
import com.anhtester.reports.ExtentTestManager;
import com.anhtester.utils.LogUtils;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.event.KeyEvent;

public class WebKeyword {

    private static SoftAssert softAssert;

    private static double STEP_TIME = AppConfig.TIMEOUT_STEP;

    public static SoftAssert getSoftAssert() {
        if (softAssert == null) {
            softAssert = new SoftAssert();
        }
        return softAssert;
    }

    public static void closeSoftAssert() {
        if (softAssert != null) {
            softAssert.assertAll();
        }
    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void maximizeBrowserOnWindow() {
        System.out.println(SystemHelper.getOperatingSystem());
        Robot rb = null;
        try {
            rb = new Robot();
            if (SystemHelper.getOperatingSystem().toLowerCase().contains("window")) {
                rb.keyPress(KeyEvent.VK_WINDOWS);
                rb.keyPress(KeyEvent.VK_UP);
                rb.keyRelease(KeyEvent.VK_UP);
                rb.keyRelease(KeyEvent.VK_WINDOWS);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Step("Navigate to URL: {0}")
    public static void navigate(String url) {
        PageManager.getPage().navigate(url);
        PageManager.getPage().waitForLoadState();
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

    @Step("Get attribute {1} of element {0}")
    public static void getAttribute(String locator, String attributeName) {
        sleep(STEP_TIME);
        String text = PageManager.getPage().locator(locator).getAttribute(attributeName);
        LogUtils.info("Get attribute " + attributeName + " of element " + locator);
        ExtentTestManager.logMessage(Status.INFO, "Get attribute " + attributeName + " of element " + locator);
    }

    @Step("Clear text in element {0}")
    public static void clear(String locator) {
        sleep(STEP_TIME);
        PageManager.getPage().locator(locator).clear();
        LogUtils.info("Clear text in element " + locator);
        ExtentTestManager.logMessage(Status.INFO, "Clear text in element " + locator);
    }

    @Step("High light element {0}")
    public static void highlight(String locator) {
        sleep(STEP_TIME);
        PageManager.getPage().locator(locator).highlight();
        LogUtils.info("High light element " + locator);
        ExtentTestManager.logMessage(Status.INFO, "High light element " + locator);
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

    @Step("Get text of element {0}")
    public static String innerText(String locator) {
        sleep(STEP_TIME);
        String text = PageManager.getPage().locator(locator).innerText();
        LogUtils.info("Get text of element " + locator + " ==> " + text);
        ExtentTestManager.logMessage(Status.INFO, "Get text of element " + locator);
        ExtentTestManager.logMessage(Status.INFO, "==> Text: " + text);
        AllureManager.saveTextLog("==> " + text);
        return text;
    }

    @Step("Wait for page loaded")
    public static void waitForPageLoaded() {
        PageManager.getPage().waitForLoadState(LoadState.LOAD);
        LogUtils.info("Wait for page loaded");
    }

}
