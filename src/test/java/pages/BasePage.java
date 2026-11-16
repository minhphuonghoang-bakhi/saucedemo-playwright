package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

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
}