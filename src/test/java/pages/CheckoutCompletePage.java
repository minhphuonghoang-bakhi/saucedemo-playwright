package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutCompletePage extends BasePage {

    public CheckoutCompletePage(Page page) {
        super(page);
    }

    public Locator completeHeader() {
        return page.getByTestId("complete-header");
    }
}
