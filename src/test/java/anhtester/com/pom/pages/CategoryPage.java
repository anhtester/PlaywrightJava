package anhtester.com.pom.pages;

import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CategoryPage extends CommonPage {

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

    Page page;

    public CategoryPage(Page _page) {
        super(_page);
        page = _page;
    }

    public void addNewCategory(String categoryName) {
        page.click(buttonAddNewCategory);
        page.fill(inputCategoryName, categoryName);
        page.click(dropdownParentCategory);
        page.waitForTimeout(1000);
        page.fill(inputParentCategory, "Gaming pc");
        page.waitForTimeout(1000);
        page.press(inputParentCategory, "Enter");
        page.fill(inputOrderNUmber, "1");
        page.fill(inputMetaTitle, categoryName);
        page.fill(inputMetaDescription, categoryName);
        page.click(buttonSave);
    }

    public void checkCategory(String categoryName){
        page.waitForLoadState();
        page.fill(inputSearchCategory, categoryName);
        page.press(inputSearchCategory, "Enter");
        page.waitForTimeout(1000);
        assertThat(page.locator(itemCategoryNameFirst)).isVisible();
        assertThat(page.locator(itemCategoryNameFirst)).hasText(categoryName);
    }
}
