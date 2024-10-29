package com.anhtester.helpers;

import java.io.File;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

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

    public static String getDateTimeNow() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        return dateFormat.format(new Date());
    }

    public static String getDateTimeNowAndMakeSlug() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        return makeSlug(dateFormat.format(new Date()));
    }

    public static String makeSlug(String input) {
        final Pattern NONLATIN = Pattern.compile("[^\\w-]");
        final Pattern WHITESPACE = Pattern.compile("[\\s]");

        if (input == null)
            throw new IllegalArgumentException();

        String noWhiteSpace = WHITESPACE.matcher(input).replaceAll("_");
        String normalized = Normalizer.normalize(noWhiteSpace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    /**
     * Create folder empty
     *
     * @param path is the path to create the folder
     */
    public static void createFolder(String path) {
        // File is a class inside java.io package
        File file = new File(path);

        String result = null;

        int lengthSum = path.length();
        int lengthSub = path.substring(0, path.lastIndexOf('/')).length();

        result = path.substring(lengthSub, lengthSum);

        if (!file.exists()) {
            file.mkdir();  // mkdir is used to create folder
            System.out.println("Folder " + file.getName() + " created: " + path);
        } else {
            System.out.println("Folder already created");
        }
    }

    /**
     * @param str        is value as type is String to be split based on condition
     * @param valueSplit the character to split the string into an array of values
     * @return array of string values after splitting
     */
    public static ArrayList<String> splitString(String str, String valueSplit) {
        ArrayList<String> arrayListString = new ArrayList<>();
        for (String s : str.split(valueSplit, 0)) {
            arrayListString.add(s);
        }
        return arrayListString;
    }
}
