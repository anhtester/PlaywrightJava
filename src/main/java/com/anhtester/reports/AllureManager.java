package com.anhtester.reports;

import com.anhtester.helpers.FileHelper;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.helpers.SystemHelper;
import com.anhtester.managers.PageManager;
import com.anhtester.utils.LogUtils;
import com.microsoft.playwright.Page.ScreenshotOptions;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AllureManager {
    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    //Screenshot attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

        ScreenshotOptions screenshotOptions = new ScreenshotOptions();
        return PageManager.getPage().screenshot(screenshotOptions.setPath(Paths.get(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("SCREENSHOT_PATH") + File.separator + dateFormat.format(new Date()) + ".png")));
    }

    public static void addAttachmentVideoAVI() {
        if (PropertiesHelper.getValue("VIDEO_RECORD").equals("true")) {
            try {
                //Get file Last Modified in folder
                File video = FileHelper.getFileLastModified(PropertiesHelper.getValue("VIDEO_RECORD_PATH"));
                if (video != null) {
                    Allure.addAttachment("Failed test Video record AVI", "video/avi", Files.asByteSource(video).openStream(), ".avi");
                } else {
                    LogUtils.warn("Video record not found.");
                    LogUtils.warn("Can not attachment Video in Allure report");
                }

            } catch (IOException e) {
                LogUtils.error("Can not attachment Video in Allure report");
                e.printStackTrace();
            }
        }
    }
}
