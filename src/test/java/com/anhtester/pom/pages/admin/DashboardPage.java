package com.anhtester.pom.pages.admin;

import com.anhtester.keywords.WebKeyword;
import com.anhtester.pom.pages.BasePage;

public class DashboardPage extends BasePage {

    private String totalCustomerLabel = "//div[normalize-space()='Total Customer']/following-sibling::div";
    private String totalOrderLabel = "//div[normalize-space()='Total Order']/following-sibling::div";
    private String totalProductCategoryLabel = "//div[normalize-space()='Total Product category']/following-sibling::div";
    private String totalProductBrandLabel = "//div[normalize-space()='Total Product brand']/following-sibling::div";
    private String buttonClearCacheOnAdminPage = "//span[.='Clear Cache']";

    public void verifyTotalCustomer(String number) {
        WebKeyword.getSoftAssert().assertEquals(
                WebKeyword.textContent(totalCustomerLabel),
                number,
                "The total Customer not match."
        );
    }

    public void verifyTotalOrder(String number) {
        WebKeyword.getSoftAssert().assertEquals(
                WebKeyword.textContent(totalOrderLabel),
                number,
                "The total Order not match."
        );
    }

    public void verifyTotalProductCategory(String number) {
        WebKeyword.getSoftAssert().assertEquals(
                WebKeyword.textContent(totalProductCategoryLabel),
                number,
                "The total Product Category not match."
        );
    }

    public void verifyTotalProductBrand(String number) {
        WebKeyword.getSoftAssert().assertEquals(
                WebKeyword.textContent(totalProductBrandLabel),
                number,
                "The total Product Brand not match."
        );
    }
}
