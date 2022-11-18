package anhtester.com.pom.testcases;

import anhtester.com.common.BaseTest;
import anhtester.com.manager.PageManager;
import anhtester.com.pom.pages.CategoryPage;
import anhtester.com.pom.pages.CommonPage;
import anhtester.com.pom.pages.LoginPage;
import org.testng.annotations.Test;

public class CategoryTest extends BaseTest {

    LoginPage loginPage;
    CommonPage commonPage;
    CategoryPage categoryPage;

    @Test
    public void testAddNewCategory(){
        loginPage = new LoginPage(PageManager.getPage());
        loginPage.loginAdminCMS("admin@example.com", "123456");
        commonPage = new CommonPage(PageManager.getPage());
        categoryPage = commonPage.openCategoryPage();
        categoryPage.addNewCategory("Acer Nitro 5 Tiger");
        categoryPage.checkCategory("Acer Nitro 5 Tiger");
    }
}
