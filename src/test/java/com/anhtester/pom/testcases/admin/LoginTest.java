package com.anhtester.pom.testcases.admin;

import com.anhtester.common.BaseTest;
import com.anhtester.pom.pages.admin.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @Test
    public void testLoginSuccess() {
        loginPage = new LoginPage();
        loginPage.loginAdminCMS("admin@example.com", "123456")
                .verifyLoginSuccess();
    }

    @Test
    public void testLoginFailWithEmailInvalid() {
        loginPage = new LoginPage();
        loginPage.loginAdminCMS("admin123@example.com", "123456")
                .verifyLoginFail("Invalid login credentials 123");
    }
}
