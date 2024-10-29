package com.anhtester.managers;

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

    public static void closeTracing(String path) {
        // Stop tracing and export it into a zip archive.
        browserContextThreadLocal.get().tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get(path)));
        LogUtils.info("Closed Tracing.");
    }

    public static void closeBrowser() {
        if (browserThreadLocal.get() != null) {
            browserThreadLocal.get().close();
            browserThreadLocal.remove();
            LogUtils.info("Closed browser.");
        }
    }

}
