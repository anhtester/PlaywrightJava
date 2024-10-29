package com.anhtester.pom.testcases.user;

import com.anhtester.common.BaseTest;
import com.anhtester.pom.pages.admin.LoginPage;
import com.anhtester.pom.pages.user.LoginUserPage;
import org.testng.annotations.Test;

public class LoginUserTest extends BaseTest {
    LoginUserPage loginUserPage;

    @Test
    public void testLoginSuccess() {
        loginUserPage = new LoginUserPage();
        loginUserPage.loginUserCMS("customer@example.com", "123456")
                .verifyLoginSuccess();
    }

    @Test
    public void testLoginFailWithEmailInvalid() {
        loginUserPage = new LoginUserPage();
        loginUserPage.loginUserCMS("customer123@example.com", "123456")
                .verifyLoginFail("Invalid login credentials 123");
    }
}
