package com.anhtester.pom.testcases.admin;

import com.anhtester.common.BaseTest;
import com.anhtester.managers.PageManager;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {

    @Test
    public void testTotalSummaryInDashboard() {
        getLoginPage().loginAdminCMS("admin@example.com", "123456");
        PageManager.getPage().waitForLoadState();
        getDashboardPage().verifyTotalCustomer("596");
        getDashboardPage().verifyTotalOrder("106");
        getDashboardPage().verifyTotalProductCategory("289");
        getDashboardPage().verifyTotalProductBrand("243");
    }

}
