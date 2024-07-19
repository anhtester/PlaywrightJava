package com.anhtester.common;

import com.anhtester.constants.AppConfig;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebKeyword;
import com.anhtester.listeners.TestListener;
import com.anhtester.managers.BrowserFactory;
import com.anhtester.managers.PageManager;
import com.anhtester.pom.pages.CommonPage;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public class BaseTest extends CommonPage {

    @BeforeMethod
    @Parameters({"BROWSER"})
    public void createBrowser(@Optional("chrome") String browserName) {
        if (AppConfig.BROWSER != null && !AppConfig.BROWSER.isEmpty()) {
            BrowserFactory.createBrowser(AppConfig.BROWSER);
        }else {
            BrowserFactory.createBrowser(browserName);
        }
    }

    @AfterMethod
    public void closePage() {
        PageManager.closePage();
        PageManager.closeBrowser();

        WebKeyword.closeSoftAssert();
    }

}
