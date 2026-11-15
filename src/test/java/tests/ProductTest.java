package tests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class ProductTest extends BaseTest {

    // Vorbedingungen (run 1 time before all tests - each test do not need to call login function)
    @BeforeEach
    void loginAsStandardUser() {
        // TODO: als standard_user einloggen
        page.getByTestId("username").fill("standard_user");
        page.getByTestId("password").fill("secret_sauce");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
    }


    @Test
    @DisplayName("TC-P01: Es werden 6 Produkte angezeigt")
    void showsSixProducts() {
        // TODO
        assertThat(page.getByTestId("inventory-item")).hasCount(6);
        // page.getByTestId("inventory-item") - find all elements having data-test="inventory-item"
    }

    @Test
    @DisplayName("TC-P02: Sortierung „Price (low to high)“")
    void sortByPriceLowToHigh() {
        // TODO 1: Sortierung wählen
        Locator sort = page.getByTestId("product-sort-container");
        sort.selectOption("lohi");
        // TODO 2: Erstes Produkt prüfen
        Locator firstProduct = page.getByTestId("inventory-item")
                .filter(new Locator.FilterOptions().setHasText("Sauce Labs Onesie")); // find Sauce Labs Onesie
        assertThat(firstProduct.getByTestId("inventory-item-price")).hasText("$7.99"); // access the price
        // TODO 3: Letztes Produkt prüfen
        Locator lastProduct = page.getByTestId("inventory-item")
                .filter(new Locator.FilterOptions().setHasText("Sauce Labs Fleece Jacket"));
        assertThat(lastProduct.getByTestId("inventory-item-price")).hasText("$49.99"); // access the price
    }

    @Test
    @DisplayName("TC-P03: Sortierung „Name (Z to A)“")
    void sortByNameZToA() {
        // TODO
        Locator sort = page.getByTestId("product-sort-container");
        sort.selectOption("za");

        Locator first = page.getByTestId("inventory-item").first();  // access the first item of all found items
        assertThat(first.getByTestId("inventory-item-name")).hasText("Test.allTheThings() T-Shirt (Red)");
        // access the name inside this item and check Text

        Locator last = page.getByTestId("inventory-item").last();
        assertThat(last.getByTestId("inventory-item-name")).hasText("Sauce Labs Backpack");
    }
}

/**
 * inventory-item                         ← ①
 * ├── inventory_item_img
 * └── inventory-item-description
 *     ├── inventory_item_label
 *     │   └── a
 *     │       └── inventory-item-name    → "Sauce Labs Onesie"
 *     └── pricebar
 *         ├── inventory-item-price       → "$" + "7.99"   ← ②
 *         └── button "Add to cart"
*/

