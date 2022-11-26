package anhtester.com.common;

import anhtester.com.keyword.ActionKeyword;
import anhtester.com.manager.BrowserFactory;
import anhtester.com.manager.PageManager;
import anhtester.com.pom.pages.CommonPage;
import com.microsoft.playwright.*;
import org.testng.annotations.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class BaseTest {

    @BeforeMethod
    @Parameters({"browser"})
    public void createBrowser(@Optional("chrome") String browserName) {
        BrowserFactory.createBrowser(browserName);
    }

    @AfterMethod
    public void closePage() {
        PageManager.closePage();
        PageManager.closeBrowser();
    }

}
