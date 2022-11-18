package anhtester.com.pom.pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.Assert;

public class LoginPage {

    public String URL_ADMIN = "https://demo.activeitzone.com/ecommerce/login";
    public String URL_USER = "https://demo.activeitzone.com/ecommerce/users/login";

    private String headerLoginPageAdmin = "//div[@class='card-body']//h1";
    private String headerLoginPageUser = "//div[@class='card']//h1";

    private String inputEmail = "//input[@id='email']";
    private String inputPassword = "//input[@id='password']";
    private String buttonLogin = "//button[normalize-space()='Login']";
    private String buttonCopy = "//button[normalize-space()='Copy']";

    private String buttonX = "//i[@class='la la-close fs-20']";
    private String headerDashboardPageAdmin = "//span[normalize-space()='Dashboard']";
    private String headerDashboardPageUser = "//h1[normalize-space()='Dashboard']";

    Page page;
    public LoginPage(Page _page){
        page = _page;
    }

    public void loginAdminCMS(String email, String password){
        page.navigate(URL_ADMIN, new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
        Assert.assertEquals(page.locator(headerLoginPageAdmin).textContent(), "Welcome to Active eCommerce CMS");
        assertThat(page.locator(headerLoginPageAdmin)).hasText("Welcome to Active eCommerce CMS");
        page.fill(inputEmail, email);
        page.fill(inputPassword, password);
        page.click(buttonLogin);
        page.waitForLoadState();
        assertThat(page.locator(headerDashboardPageAdmin)).isVisible();
    }

    public void loginUserCMS(String email, String password){
        page.navigate(URL_USER, new Page.NavigateOptions().setWaitUntil(WaitUntilState.NETWORKIDLE));
        page.click(buttonX);
        assertThat(page.locator(headerLoginPageUser)).hasText("Login to your account.");
        page.fill(inputEmail, email);
        page.fill(inputPassword, password);
        page.click(buttonLogin);
        page.waitForLoadState();
        assertThat(page.locator(headerDashboardPageUser)).isVisible();
    }

}
