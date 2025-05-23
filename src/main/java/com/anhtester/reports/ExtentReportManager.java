package com.anhtester.reports;

import com.anhtester.constants.AppConfig;
import com.anhtester.helpers.PropertiesHelper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(AppConfig.EXTENT_REPORT_PATH);
        reporter.config().setReportName("Extent Report | Anh Tester");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Framework Name", "Playwright Java | Anh Tester");
        extentReports.setSystemInfo("Author", "Anh Tester");

        return extentReports;
    }

}
