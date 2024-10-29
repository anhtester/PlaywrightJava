package com.anhtester.pom.pages.admin;

import com.anhtester.keywords.WebKeyword;
import com.anhtester.managers.PageManager;
import com.anhtester.pom.pages.BasePage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CategoryPage extends BasePage {

    private String buttonAddNewCategory = "//span[normalize-space()='Add New category']";
    private String inputCategoryName = "//input[@id='name']";
    private String dropdownParentCategory = "//label[normalize-space()='Parent Category']/following-sibling::div//button";
    private String inputParentCategory = "//div[@class='dropdown-menu show']//input[@aria-label='Search']";
    private String inputOrderNUmber = "//input[@id='order_level']";
    private String inputMetaTitle = "//input[@placeholder='Meta Title']";
    private String inputMetaDescription = "//textarea[@name='meta_description']";
    private String buttonSave = "//button[normalize-space()='Save']";
    private String inputSearchCategory = "//input[@id='search']";
    private String itemCategoryNameFirst = "//tbody/tr[1]/td[2]";

    public CategoryPage addNewCategory(String categoryName) {
        PageManager.getPage().waitForLoadState();
        PageManager.getPage().click(buttonAddNewCategory);
        PageManager.getPage().fill(inputCategoryName, categoryName);
        PageManager.getPage().click(dropdownParentCategory);
        WebKeyword.sleep(1);
        PageManager.getPage().fill(inputParentCategory, "Laptop");
        WebKeyword.sleep(1);
        PageManager.getPage().press(inputParentCategory, "Enter");
        PageManager.getPage().fill(inputOrderNUmber, "1");
        PageManager.getPage().fill(inputMetaTitle, categoryName);
        PageManager.getPage().fill(inputMetaDescription, categoryName);
        PageManager.getPage().click(buttonSave);
        return this;
    }

    public void checkCategory(String categoryName){
        PageManager.getPage().waitForLoadState();
        PageManager.getPage().fill(inputSearchCategory, categoryName);
        PageManager.getPage().press(inputSearchCategory, "Enter");
        WebKeyword.sleep(1);
        assertThat(PageManager.getPage().locator(itemCategoryNameFirst)).isVisible();
        assertThat(PageManager.getPage().locator(itemCategoryNameFirst)).hasText(categoryName);
    }
}
