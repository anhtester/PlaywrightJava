package com.anhtester.managers;

import com.anhtester.constants.AppConfig;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

import java.awt.*;
import java.nio.file.Paths;
import java.util.Arrays;

public class BrowserFactory {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;
    //https://playwright.dev/java/docs/browsers

    public void createBrowser(String browserName) {
        playwright = Playwright.create();
        PageManager.setPlaywright(playwright);

        switch (browserName.toLowerCase().trim()) {
            case "chromium":
                System.out.println("Create Chromium browser...");
                browser = (PageManager.getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(AppConfig.HEADLESS).setArgs(Arrays.asList("--start-maximized"))));
                break;
            case "chrome":
                System.out.println("Create Chrome browser...");
                browser = (PageManager.getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(AppConfig.HEADLESS).setArgs(Arrays.asList("--start-maximized"))));
                break;
            case "edge":
                System.out.println("Create Edge browser...");
                browser = (PageManager.getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(AppConfig.HEADLESS).setArgs(Arrays.asList("--start-maximized"))));
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
                browser = (PageManager.getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(AppConfig.HEADLESS).setArgs(Arrays.asList("--start-maximized"))));
                break;
        }

        PageManager.setBrowser(browser);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

//        System.out.println("Screen Browser Width (customize): " + AppConfig.VIEWPORT_WIDTH);
//        System.out.println("Screen Browser Height (customize): " + AppConfig.VIEWPORT_HEIGHT);
        System.out.println("Screen Browser Width (default): " + width);
        System.out.println("Screen Browser Height (default): " + height);

        if(AppConfig.HEADLESS) {
            browserContext = PageManager.getBrowser().newContext(new Browser.NewContextOptions()
                            .setViewportSize(AppConfig.VIEWPORT_WIDTH, AppConfig.VIEWPORT_HEIGHT)
                            .setRecordVideoDir(Paths.get(AppConfig.VIDEO_RECORD_PATH))
                            .setRecordVideoSize(AppConfig.VIEWPORT_WIDTH, AppConfig.VIEWPORT_HEIGHT)
                    //.setDeviceScaleFactor(1)
            );
        }else {
            browserContext = PageManager.getBrowser().newContext(new Browser.NewContextOptions()
                            .setViewportSize(null)
                            .setRecordVideoDir(Paths.get(AppConfig.VIDEO_RECORD_PATH))
                            .setRecordVideoSize(width, height)
                    //.setDeviceScaleFactor(1)
            );
        }

        PageManager.setBrowserContext(browserContext);
        page = PageManager.getBrowserContext().newPage();
        page.waitForLoadState(LoadState.LOAD, new Page.WaitForLoadStateOptions().setTimeout(AppConfig.TIMEOUT_PAGE_LOAD));

        PageManager.setPage(page);

    }

}
