package com.anhtester.pom.pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.anhtester.keywords.WebKeyword;
import com.anhtester.managers.PageManager;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.Assert;

public class LoginPage {

    public String URL_ADMIN = "https://cms.anhtester.com/login";
    public String URL_USER = "https://cms.anhtester.com/users/login";

    private String headerLoginPageAdmin = "//div[@class='card-body']//h1";
    private String headerLoginPageUser = "//div[@class='card']//h1";

    private String inputEmail = "//input[@id='email']";
    private String inputPassword = "//input[@id='password']";
    private String buttonLogin = "//button[normalize-space()='Login']";
    private String buttonCopy = "//button[normalize-space()='Copy']";

    private String buttonX = "//i[@class='la la-close fs-20']";
    private String buttonDefaultOnAdminPage = "//span[.='Clear Cache']";
    private String headerDashboardPageUser = "//h1[normalize-space()='Dashboard']";

    public void loginAdminCMS(String email, String password){
        WebKeyword.navigate(URL_ADMIN);
        PageManager.getPage().waitForLoadState();
        Assert.assertEquals(WebKeyword.textContent(headerLoginPageAdmin), "Welcome to Active eCommerce CMS");
        assertThat(PageManager.getPage().locator(headerLoginPageAdmin)).hasText("Welcome to Active eCommerce CMS");
        WebKeyword.fill(inputEmail, email);
        WebKeyword.fill(inputPassword, password);
        WebKeyword.click(buttonLogin);
        PageManager.getPage().waitForLoadState();
        assertThat(PageManager.getPage().locator(buttonDefaultOnAdminPage)).isVisible();
    }

    public void loginUserCMS(String email, String password){
        WebKeyword.navigate(URL_USER);
        PageManager.getPage().waitForLoadState();
        WebKeyword.click(buttonX);
        assertThat(PageManager.getPage().locator(headerLoginPageUser)).hasText("Login to your account.");
        WebKeyword.fill(inputEmail, email);
        WebKeyword.fill(inputPassword, password);
        WebKeyword.click(buttonLogin);
        PageManager.getPage().waitForLoadState();
        assertThat(PageManager.getPage().locator(headerDashboardPageUser)).isVisible();
    }

}
