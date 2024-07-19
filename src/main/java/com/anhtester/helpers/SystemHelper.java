package com.anhtester.helpers;

import java.io.File;

public class SystemHelper {
    public static String getCurrentDir() {
        String current = System.getProperty("user.dir") + File.separator;
        return current;
    }

    public static String getOperatingSystem() {
        String os = System.getProperty("os.name");
        // System.out.println("OS name: " + os);
        return os;
    }
}
