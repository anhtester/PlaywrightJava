package com.anhtester.pom.pages;

import com.anhtester.keywords.WebKeyword;
import com.anhtester.managers.PageManager;
import com.anhtester.pom.pages.admin.CategoryPage;
import com.anhtester.pom.pages.admin.DashboardPage;
import com.anhtester.pom.pages.admin.LoginPage;
import com.anhtester.pom.pages.user.LoginUserPage;

public class BasePage {

    public String menuProducts = "//span[normalize-space()='Products']";
    public String menuCategory = "//span[normalize-space()='Category']";
    public String buttonClearCacheOnAdminPage = "//span[.='Clear Cache']";
    public String buttonAcceptCookie = "//button[contains(@class, 'aiz-cookie-accept')]";

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private CategoryPage categoryPage;
    private LoginUserPage loginUserPage;

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public DashboardPage getDashboardPage() {
        if (dashboardPage == null) {
            dashboardPage = new DashboardPage();
        }
        return dashboardPage;
    }

    public CategoryPage getCategoryPage() {
        if (categoryPage == null) {
            categoryPage = new CategoryPage();
        }
        return categoryPage;
    }

    public LoginUserPage getLoginUserPage() {
        if (loginUserPage == null) {
            loginUserPage = new LoginUserPage();
        }
        return loginUserPage;
    }

    public CategoryPage openCategoryPage() {
        PageManager.getPage().waitForLoadState();
        WebKeyword.click(menuProducts);
        WebKeyword.click(menuCategory);
        return new CategoryPage();
    }
}
