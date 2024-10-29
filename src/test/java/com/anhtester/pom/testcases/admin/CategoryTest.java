package com.anhtester.pom.testcases.admin;

import com.anhtester.common.BaseTest;
import org.testng.annotations.Test;

public class CategoryTest extends BaseTest {

    @Test
    public void testAddNewCategory() {
        getLoginPage().loginAdminCMS("admin@example.com", "123456");
        getCategoryPage().openCategoryPage()
                .addNewCategory("Acer Nitro 5 Tiger")
                .checkCategory("Acer Nitro 5 Tiger");
    }
}
