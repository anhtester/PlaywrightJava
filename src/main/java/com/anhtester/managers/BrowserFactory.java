package com.anhtester.managers;

import com.anhtester.constants.AppConfig;
import com.anhtester.keywords.WebKeyword;
import com.microsoft.playwright.*;

import java.awt.*;

public class BrowserFactory {
    protected static Playwright playwright;
    protected static Browser browser;
    protected static BrowserContext browserContext;
    protected static Page page;
    //https://playwright.dev/java/docs/browsers

    public static void createBrowser(String browserName) {
        playwright = Playwright.create();
        PageManager.setPlaywright(playwright);

        switch (browserName.toLowerCase().trim()) {
            case "chromium":
                System.out.println("Create Chromium browser...");
                browser = (PageManager.getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(AppConfig.HEADLESS)));
                break;
            case "chrome":
                System.out.println("Create Chrome browser...");
                browser = (PageManager.getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(AppConfig.HEADLESS)));
                break;
            case "edge":
                System.out.println("Create Edge browser...");
                browser = (PageManager.getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(AppConfig.HEADLESS)));
                break;
            case "firefox":
                System.out.println("Create Firefox browser...");
                browser = (PageManager.getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(AppConfig.HEADLESS)));
                break;
            case "safari":
                System.out.println("Create Safari browser...");
                browser = (PageManager.getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(AppConfig.HEADLESS)));
                break;
            default:
                System.out.println("Set default Chromium browser...");
                browser = (PageManager.getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(AppConfig.HEADLESS)));
                break;
        }

        PageManager.setBrowser(browser);

//        System.out.println("Screen Browser Width (customize): " + AppConfig.VIEWPORT_WIDTH);
//        System.out.println("Screen Browser Height (customize): " + AppConfig.VIEWPORT_HEIGHT);

        browserContext = PageManager.getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(AppConfig.VIEWPORT_WIDTH, AppConfig.VIEWPORT_HEIGHT).setDeviceScaleFactor(1));

        PageManager.setBrowserContext(browserContext);
        page = PageManager.getBrowserContext().newPage();

        PageManager.setPage(page);
        WebKeyword.maximizeBrowserOnWindow();
    }
}
