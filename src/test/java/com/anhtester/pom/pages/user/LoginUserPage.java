package com.anhtester.pom.pages.user;

import com.anhtester.keywords.WebKeyword;
import com.anhtester.managers.PageManager;
import com.anhtester.pom.pages.CommonPage;
import com.anhtester.pom.pages.admin.LoginPage;
import org.testng.Assert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginUserPage extends CommonPage {
    private String URL_USER = "https://cms.anhtester.com/users/login";
    private String headerLoginPageUser = "//div[@class='card']//h1";
    private String buttonClosePopup = "//i[@class='la la-close fs-20']";
    private String inputEmail = "//input[@id='email']";
    private String inputPassword = "//input[@id='password']";
    private String buttonLogin = "//button[normalize-space()='Login']";
    private String headerDashboardPageUser = "//h1[normalize-space()='Dashboard']";
    private String alertMessage = "//div[@role='alert']";

    public LoginUserPage loginUserCMS(String email, String password) {
        WebKeyword.navigate(URL_USER);
        PageManager.getPage().waitForLoadState();
        WebKeyword.click(buttonClosePopup);
        assertThat(PageManager.getPage().locator(headerLoginPageUser)).hasText("Login to your account.");
        WebKeyword.fill(inputEmail, email);
        WebKeyword.fill(inputPassword, password);
        WebKeyword.click(buttonLogin);
        PageManager.getPage().waitForLoadState();
        return this;
    }

    public LoginUserPage verifyLoginSuccess(){
        PageManager.getPage().waitForLoadState();
        Assert.assertTrue(PageManager.getPage().isVisible(headerDashboardPageUser), "FAIL. The button Clear Cache not visible.");
        return this;
    }

    public LoginUserPage verifyLoginFail(String message){
        PageManager.getPage().waitForLoadState();
        Assert.assertEquals(WebKeyword.textContent(alertMessage), message, "FAIL. The error message not match.");
        Assert.assertFalse(PageManager.getPage().isVisible(headerDashboardPageUser), "FAIL. The button Clear Cache visible.");
        return this;
    }
}
