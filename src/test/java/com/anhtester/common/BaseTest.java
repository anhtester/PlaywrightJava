package com.anhtester.common;

import com.anhtester.listeners.TestListener;
import com.anhtester.managers.BrowserFactory;
import com.anhtester.managers.PageManager;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeMethod
    @Parameters({"BROWSER"})
    public void createBrowser(@Optional("chrome") String browserName) {
        BrowserFactory.createBrowser(browserName);
    }

    @AfterMethod
    public void closePage() {
        PageManager.closePage();
        PageManager.closeBrowser();
    }

}
