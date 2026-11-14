package base;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

public class BaseTest {
    protected static final String BASE_URL = "https://www.saucedemo.com";

    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeAll
    public static void launchBrowser() {
        playwright = Playwright.create();
        playwright.selectors().setTestIdAttribute("data-test"); // getByTestId()
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(300));
    }

    @AfterAll
    public static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    public void openPage() {
        context = browser.newContext();
        page = context.newPage();
        page.navigate(BASE_URL);
    }

    @AfterEach
    public void closePage() {
        context.close();
    }
}