package anhtester.com.manager;

import anhtester.com.keyword.ActionKeyword;
import com.microsoft.playwright.*;

import java.awt.*;

public class BrowserFactory {
    protected static Playwright playwright;
    protected static Browser browser;
    protected static BrowserContext browserContext;
    protected static Page page;

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int screenWidth = ((int) screenSize.getWidth());
    private static int screenHeight = ((int) screenSize.getHeight());

    //https://playwright.dev/java/docs/browsers

    public static Page createBrowser(String browserName) {
        playwright = Playwright.create();
        PageManager.setPlaywright(playwright);

        switch (browserName.toLowerCase().trim()) {
            case "chromium":
                System.out.println("Create Chromium browser...");
                browser = (PageManager.getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "chrome":
                System.out.println("Create Chrome browser...");
                browser = (PageManager.getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
                break;
            case "edge":
                System.out.println("Create Edge browser...");
                browser = (PageManager.getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false)));
                break;
            case "firefox":
                System.out.println("Create Firefox browser...");
                browser = (PageManager.getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "safari":
                System.out.println("Create Safari browser...");
                browser = (PageManager.getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            default:
                System.out.println("Set default Chromium browser...");
                browser = (PageManager.getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
        }

        PageManager.setBrowser(browser);
        browserContext = PageManager.getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(screenWidth, screenHeight));
        //browserContext = PageManager.getBrowser().newContext();
        PageManager.setBrowserContext(browserContext);
        page = PageManager.getBrowserContext().newPage();
        PageManager.setPage(page);
        //ActionKeyword.maximizeBrowserOnWindow();
        return PageManager.getPage();
    }
}
