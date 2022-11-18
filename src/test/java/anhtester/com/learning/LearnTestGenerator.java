package anhtester.com.learning;

import anhtester.com.keyword.ActionKeyword;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LearnTestGenerator {
    public static void main(String[] args) {

        //Open record locator
        //mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="codegen anhtester.com"

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        ActionKeyword.maximizeBrowserOnWindow();
        page.navigate("https://anhtester.com");
        System.out.println(page.title());
        System.out.println(page.url());

        page.locator("#bg-header >> text=Website Testing").click();
        assertThat(page).hasURL("https://anhtester.com/courses/website-testing");

        page.locator("div[role=\"tabpanel\"] >> text=Selenium WebDriver with Java Basic to Advanced").click();
        assertThat(page).hasURL("https://anhtester.com/course/selenium-webdriver-with-java-basic-to-advanced-c4.html");

        browser.close();
        playwright.close();
    }
}
