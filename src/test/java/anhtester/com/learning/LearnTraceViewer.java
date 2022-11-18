package anhtester.com.learning;

import anhtester.com.common.BaseTest;
import anhtester.com.constant.ConstantApp;
import anhtester.com.manager.PageManager;
import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Paths;

public class LearnTraceViewer {
    @Test
    public void testTraceViewer() {

        BrowserContext browserContext = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newContext();

        // Start tracing before creating / navigating a manager.
        browserContext.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        Page page = browserContext.newPage();
        page.navigate(ConstantApp.URL);
        page.locator("//input[@id='iusername']").fill(ConstantApp.USERNAME);
        page.locator("//input[@id='ipassword']").fill(ConstantApp.PASSWORD);
        page.locator("//button[@type='submit']").click();

        // Stop tracing and export it into a zip archive.
        browserContext.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get(System.getProperty("user.dir") + File.separator + "trace.zip")));

        //How to run
        //Open Terminal in IntelliJ
        //npx playwright show-trace trace.zip

    }
}
