package com.anhtester.pom.pages.admin;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.anhtester.keywords.WebKeyword;
import com.anhtester.managers.PageManager;
import com.anhtester.pom.pages.BasePage;
import org.testng.Assert;

public class LoginPage extends BasePage {

    private String URL_ADMIN = "https://cms.anhtester.com/login";
    private String headerLoginPageAdmin = "//div[@class='card-body']//h1";
    private String inputEmail = "//input[@id='email']";
    private String inputPassword = "//input[@id='password']";
    private String buttonLogin = "//button[normalize-space()='Login']";
    private String buttonCopy = "//button[normalize-space()='Copy']";

    private String alertMessage = "//div[@role='alert']";


    public LoginPage loginAdminCMS(String email, String password){
        WebKeyword.navigate(URL_ADMIN);
        PageManager.getPage().waitForLoadState();
        Assert.assertEquals(WebKeyword.textContent(headerLoginPageAdmin), "Welcome to Active eCommerce CMS");
        assertThat(PageManager.getPage().locator(headerLoginPageAdmin)).hasText("Welcome to Active eCommerce CMS");
        WebKeyword.fill(inputEmail, email);
        WebKeyword.fill(inputPassword, password);
        WebKeyword.click(buttonLogin);
        PageManager.getPage().waitForLoadState();
        return this;
    }

    public LoginPage verifyLoginSuccess(){
        PageManager.getPage().waitForLoadState();
        Assert.assertTrue(PageManager.getPage().isVisible(buttonClearCacheOnAdminPage), "FAIL. The button Clear Cache not visible.");
        return this;
    }

    public LoginPage verifyLoginFail(String message){
        PageManager.getPage().waitForLoadState();
        Assert.assertEquals(WebKeyword.textContent(alertMessage), message, "FAIL. The error message not match.");
        Assert.assertFalse(PageManager.getPage().isVisible(buttonClearCacheOnAdminPage), "FAIL. The button Clear Cache visible.");
        return this;
    }

}
