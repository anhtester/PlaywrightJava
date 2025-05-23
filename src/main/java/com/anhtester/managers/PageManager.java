package com.anhtester.managers;

import com.anhtester.constants.AppConfig;
import com.anhtester.utils.LogUtils;
import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class PageManager {

    private static ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> browserContextThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    public static Browser getBrowser() {
        return browserThreadLocal.get();
    }

    public static BrowserContext getBrowserContext() {
        return browserContextThreadLocal.get();
    }

    public static Playwright getPlaywright() {
        return playwrightThreadLocal.get();
    }

    public static Page getPage() {
        return pageThreadLocal.get();
    }

    public static void setPlaywright(Playwright playwright) {
        playwrightThreadLocal.set(playwright);
    }

    public static void setBrowser(Browser browser) {
        browserThreadLocal.set(browser);
    }

    public static void setBrowserContext(BrowserContext browserContext) {
        browserContextThreadLocal.set(browserContext);
    }

    public static void setPage(Page page) {
        pageThreadLocal.set(page);
    }

    public static void closePage() {
        if (pageThreadLocal.get() != null) {
            pageThreadLocal.get().close();
            pageThreadLocal.remove();
            LogUtils.info("Closed page.");
        }
    }

    public static void closeBrowser() {
        if (browserThreadLocal.get() != null) {
            browserThreadLocal.get().close();
            browserThreadLocal.remove();
            LogUtils.info("Closed browser.");
        }
    }

    public static void closeBrowserContext() {
        if (browserContextThreadLocal.get() != null) {
            browserContextThreadLocal.get().close();
            browserContextThreadLocal.remove();
            LogUtils.info("Closed browser context.");
        }
    }

    public static void closePlaywright() {
        if (playwrightThreadLocal.get() != null) {
            playwrightThreadLocal.get().close();
            playwrightThreadLocal.remove();
            LogUtils.info("Closed Playwright.");
        }
    }

    public static void closeAll() {
        //closePage();
        closeBrowserContext();
        closeBrowser();
        closePlaywright();
    }

    public static void startTracing() {
        // Start tracing before performing actions.
        if (AppConfig.TRACE_VIEWER) {
            browserContextThreadLocal.get().tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));
            LogUtils.info("Started tracing.");
        }
    }

    public static void closeTracing() {
        // Stop tracing and export it into a zip archive.
        if (AppConfig.TRACE_VIEWER) {
            browserContextThreadLocal.get().tracing().stop();
            LogUtils.info("Closed tracing.");
        }
    }

    public static void closeTracing(String path) {
        // Stop tracing and export it into a zip archive.
        if (AppConfig.TRACE_VIEWER) {
            browserContextThreadLocal.get().tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get(path)));
            LogUtils.info("Closed tracing.");
        }
    }

}
