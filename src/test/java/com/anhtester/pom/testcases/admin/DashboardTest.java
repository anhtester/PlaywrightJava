package com.anhtester.pom.testcases.admin;

import com.anhtester.common.BaseTest;
import com.anhtester.managers.PageManager;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {

    @Test
    public void testTotalSummaryInDashboard() {
        getLoginPage().loginAdminCMS("admin@example.com", "123456");
        PageManager.getPage().waitForLoadState();
        getDashboardPage().verifyTotalCustomer("124");
        getDashboardPage().verifyTotalOrder("734");
        getDashboardPage().verifyTotalProductCategory("439");
        getDashboardPage().verifyTotalProductBrand("84");
    }

}
