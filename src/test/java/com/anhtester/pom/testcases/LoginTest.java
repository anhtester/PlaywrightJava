package com.anhtester.pom.testcases;

import com.anhtester.common.BaseTest;
import com.anhtester.managers.PageManager;
import com.anhtester.pom.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @Test
    public void loginTestAdminCMS(){
        loginPage = new LoginPage();
        loginPage.loginAdminCMS("admin@example.com", "123456");
    }

    @Test
    public void loginTestUserCMS(){
        loginPage = new LoginPage();
        loginPage.loginUserCMS("customer123@example.com", "123456");
    }
}
