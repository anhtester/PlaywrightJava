package com.anhtester.constants;

import com.anhtester.helpers.PropertiesHelper;

public class AppConfig {
    public static boolean HEADLESS = Boolean.parseBoolean(PropertiesHelper.getValue("HEADLESS"));
    public static String URL = PropertiesHelper.getValue("URL");
    public static String BROWSER = PropertiesHelper.getValue("BROWSER");
    public static int VIEWPORT_WIDTH = Integer.parseInt(PropertiesHelper.getValue("VIEWPORT_WIDTH"));
    public static int VIEWPORT_HEIGHT = Integer.parseInt(PropertiesHelper.getValue("VIEWPORT_HEIGHT"));
    public static boolean VIDEO_RECORD = Boolean.parseBoolean(PropertiesHelper.getValue("VIDEO_RECORD"));
    public static boolean SCREENSHOT_FAIL = Boolean.parseBoolean(PropertiesHelper.getValue("SCREENSHOT_FAIL"));
    public static boolean SCREENSHOT_PASS = Boolean.parseBoolean(PropertiesHelper.getValue("SCREENSHOT_PASS"));
    public static int TIMEOUT_STEP = Integer.parseInt(PropertiesHelper.getValue("TIMEOUT_STEP"));
    public static boolean TRACE_VIEWER = Boolean.parseBoolean(PropertiesHelper.getValue("TRACE_VIEWER"));

}
