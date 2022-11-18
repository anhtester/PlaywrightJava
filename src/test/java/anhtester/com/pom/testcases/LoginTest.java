package anhtester.com.pom.testcases;

import anhtester.com.common.BaseTest;
import anhtester.com.manager.PageManager;
import anhtester.com.pom.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @Test
    public void loginTestAdminCMS(){
        loginPage = new LoginPage(PageManager.getPage());
        loginPage.loginAdminCMS("admin@example.com", "123456");
    }

    @Test
    public void loginTestUserCMS(){
        loginPage = new LoginPage(PageManager.getPage());
        loginPage.loginUserCMS("customer@example.com", "123456");
    }
}
