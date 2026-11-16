package test.afterrefactor;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import pages.LoginPage;
import static data.TestData.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class ProductTest extends BaseTest {
    private InventoryPage inventory;

    @BeforeEach
    void loginAsStandardUser() {
        inventory = new LoginPage(page).loginAs(STANDARD_USER, PASSWORD);
    }

    @Test
    @DisplayName("TC-P01: Es werden 6 Produkte angezeigt")
    void showsSixProducts() {
        assertThat(inventory.items()).hasCount(6);
    }

    @Test
    @DisplayName("TC-P02: Sortierung „Price (low to high)“")
    void sortByPriceLowToHigh() {
        // TODO 1: Sortierung wählen
        inventory.sortBy("lohi");
        // TODO 2: Erstes Produkt prüfen
        assertThat(inventory.itemNames().first()).hasText(ONESIE);
        assertThat(inventory.itemPrices().first()).hasText("$7.99");

        // TODO 3: Letztes Produkt prüfen
        assertThat(inventory.itemNames().last()).hasText(FLEECE_JACKET);
        assertThat(inventory.itemPrices().last()).hasText("$49.99");
    }

    @Test
    @DisplayName("TC-P03: Sortierung „Name (Z to A)“")
    void sortByNameZToA() {
        // TODO
        inventory.sortBy("za");

        assertThat(inventory.itemNames().first()).hasText(RED_TSHIRT);

        assertThat(inventory.itemNames().last()).hasText(BACKPACK);
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

