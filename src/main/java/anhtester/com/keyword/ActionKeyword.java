package anhtester.com.keyword;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ActionKeyword {
    public static void maximizeBrowserOnWindow() {
        Robot rb = null;
        try {
            rb = new Robot();
            rb.keyPress(KeyEvent.VK_WINDOWS);
            rb.keyPress(KeyEvent.VK_UP);
            rb.keyRelease(KeyEvent.VK_WINDOWS);
            rb.keyRelease(KeyEvent.VK_UP);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
