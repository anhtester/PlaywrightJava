package com.anhtester.common;

import com.anhtester.constants.AppConfig;
import com.anhtester.keywords.WebKeyword;
import com.anhtester.listeners.TestListener;
import com.anhtester.managers.BrowserFactory;
import com.anhtester.managers.PageManager;
import com.anhtester.pom.pages.BasePage;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Listeners(TestListener.class)
public class BaseTest extends BasePage {

    @BeforeMethod
    @Parameters({"browser"})
    public void createBrowser(@Optional("chrome") String browserName) {
        if (AppConfig.BROWSER != null && !AppConfig.BROWSER.isEmpty()) {
            new BrowserFactory().createBrowser(AppConfig.BROWSER);
        } else {
            new BrowserFactory().createBrowser(browserName);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void closePage(ITestResult result) {
        WebKeyword.closeSoftAssert();

        Path videoPath = null;
        boolean hasVideo = false;
        try {
            // Lấy video path trước khi đóng context
            if (PageManager.getPage() != null && PageManager.getPage().video() != null) {
                videoPath = PageManager.getPage().video().path();
                hasVideo = true;
            }
        } catch (Exception e) {
            System.out.println("Không thể lấy path video: " + e.getMessage());
        }

        try {
            PageManager.closePage();
        } catch (Exception e) {
            System.out.println("closePage fail: " + e.getMessage());
        }
        try {
            PageManager.closeBrowserContext();
        } catch (Exception e) {
            System.out.println("closeContext fail: " + e.getMessage());
        }
        try {
            PageManager.closeBrowser();
        } catch (Exception e) {
            System.out.println("closeBrowser fail: " + e.getMessage());
        }
        try {
            PageManager.closePlaywright();
        } catch (Exception e) {
            System.out.println("closePlaywright fail: " + e.getMessage());
        }

        // Đổi tên video sau khi context đã đóng (file mới xuất hiện trên disk!)
        if (hasVideo && videoPath != null) {
            try {
                String status = result.isSuccess() ? "PASS" : "FAIL";
                String testName = result.getMethod().getMethodName();
                String newFileName = String.format("exports/VideoRecords/%s_%s_%d.webm", testName, status, System.currentTimeMillis());
                // Playwright chỉ ghi file khi context đã đóng, nên có thể chờ file tồn tại!
                for (int i = 0; i < 10; i++) { // Đợi tối đa 5 giây
                    if (Files.exists(videoPath)) break;
                    Thread.sleep(500);
                }
                Files.move(videoPath, Paths.get(newFileName), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Đã đổi tên video: " + newFileName);
            } catch (Exception e) {
                System.out.println("Không thể đổi tên video: " + e.getMessage());
            }
        } else {
            System.out.println("Không tìm thấy video.");
        }

//        try {
//            if (PageManager.getPage() != null && PageManager.getPage().video() != null) {
//                Path videoPath = PageManager.getPage().video().path();
//                String status = result.isSuccess() ? "PASS" : "FAIL";
//                String testName = result.getMethod().getMethodName();
//                String newFileName = String.format("exports/VideoRecords/%s_%s_%d.webm", testName, status, System.currentTimeMillis());
//                Files.move(videoPath, Paths.get(newFileName), StandardCopyOption.REPLACE_EXISTING);
//                System.out.println("Đã đổi tên video: " + newFileName);
//            } else {
//                System.out.println("Không tìm thấy video.");
//            }
//        } catch (Exception e) {
//            System.out.println("Không thể đổi tên video: " + e.getMessage());
//        }
    }

}
