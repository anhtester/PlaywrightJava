package anhtester.com.manager;

import anhtester.com.keyword.ActionKeyword;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

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
        }
    }

    public static void closeBrowser() {
        if (browserThreadLocal.get() != null) {
            browserThreadLocal.get().close();
            browserThreadLocal.remove();
            System.out.println("Closed browser.");
        }
        if (playwrightThreadLocal.get() != null) {
            playwrightThreadLocal.get().close();
            playwrightThreadLocal.remove();
        }
    }

}
