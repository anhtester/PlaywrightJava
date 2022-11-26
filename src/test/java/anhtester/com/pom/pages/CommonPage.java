package anhtester.com.pom.pages;

import com.microsoft.playwright.Page;

public class CommonPage {

    private String menuProducts = "//span[normalize-space()='Products']";
    private String menuCategory = "//span[normalize-space()='Category']";

    private Page page;
    public CommonPage(Page _page){
        page = _page;
    }

    public CategoryPage openCategoryPage(){
        page.waitForLoadState();
        page.click(menuProducts);
        page.click(menuCategory);
        return new CategoryPage(page);
    }
}
