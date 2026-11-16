package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CartPage extends BasePage {

    public CartPage(Page page) {
        super(page);
    }

    /** Alle Zeilen im Warenkorb */
    public Locator items() {
        return page.getByTestId("inventory-item");
    }

    /** Namen aller Produkte im Warenkorb */
    public Locator itemNames() {
        return page.getByTestId("inventory-item-name");
    }

    /** Liefert die Warenkorb-Zeile mit dem angegebenen Namen */
    public Locator product(String name) {
        return items().filter(new Locator.FilterOptions().setHasText(name));
    }

    /** Entfernt ein Produkt direkt im Warenkorb */
    public void removeFromCart(String name) {
        product(name)
                .getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Remove"))
                .click();
    }
    public CheckoutInfoPage checkout() {
        // TODO: Button "Checkout" klicken (data-test="checkout")
        page.getByTestId("checkout").click();
        return new CheckoutInfoPage(page);
    }
}