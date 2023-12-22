package com.anhtester.pom.testcases;

import com.anhtester.common.BaseTest;
import com.anhtester.managers.PageManager;
import com.anhtester.pom.pages.CategoryPage;
import com.anhtester.pom.pages.CommonPage;
import com.anhtester.pom.pages.LoginPage;
import org.testng.annotations.Test;

public class CategoryTest extends BaseTest {

    LoginPage loginPage;
    CommonPage commonPage;
    CategoryPage categoryPage;

    @Test
    public void testAddNewCategory(){
        loginPage = new LoginPage();
        loginPage.loginAdminCMS("admin@example.com", "123456");
        commonPage = new CommonPage(PageManager.getPage());
        categoryPage = commonPage.openCategoryPage();
        categoryPage.addNewCategory("Acer Nitro 5 Tiger");
        categoryPage.checkCategory("Acer Nitro 5 Tiger");
    }
}
