package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class InventoryPage extends BasePage {

    public InventoryPage(Page page) {
        super(page);
    }

    public Locator title()      {
        /* TODO: data-test="title" */
        return page.getByTestId("title");
    }
    public Locator items()      {
        /* TODO: alle Produktkarten */
        return page.getByTestId("inventory-item");
    }
    public Locator itemNames()  {
        return items().getByTestId("inventory-item-name");
    }
    public Locator itemPrices() {
        return items().getByTestId("inventory-item-price");
    }

    /** Liefert die Produktkarte mit dem angegebenen Namen */
    public Locator product(String name) {
        return page.getByTestId("inventory-item")
                .filter(new Locator.FilterOptions().setHasText(name));
    }

    public void addToCart(String name) {
        product(name).getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Add to cart")).click();
    }

    public void removeFromCart(String name) {
        product(name)
                .getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Remove"))
                .click();
    }

    /** @param option value der Sortierung, z. B. "lohi" */
    public void sortBy(String option) {
        page.getByTestId("product-sort-container").selectOption(option);
    }
}