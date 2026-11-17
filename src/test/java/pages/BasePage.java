package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public abstract class BasePage {
    public static final String BASE_URL = "https://www.saucedemo.com";

    protected final Page page;

    protected BasePage(Page page) {
        this.page = page;
    }

    public Locator cartBadge() {
        return page.getByTestId("shopping-cart-badge");
    }

    public CartPage openCart() {
        page.getByTestId("shopping-cart-link").click();
        return new CartPage(page);
    }
    /** Menü öffnen und ausloggen – liefert die Login-Seite */
    public LoginPage logout() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Open Menu")).click();
        page.getByTestId("logout-sidebar-link").click();
        return new LoginPage(page);
    }
}