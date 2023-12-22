package com.anhtester.helpers;

import java.io.File;

public class SystemHelper {
    public static String getCurrentDir() {
        String current = System.getProperty("user.dir") + File.separator;
        return current;
    }
}
