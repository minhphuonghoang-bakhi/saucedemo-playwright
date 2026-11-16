package test.afterrefactor;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static data.TestData.*;

class CartTest extends BaseTest {

    private static final String BACKPACK = "Sauce Labs Backpack";
    private static final String BIKE_LIGHT = "Sauce Labs Bike Light";

    private InventoryPage inventory;

    @BeforeEach
    void loginAsStandardUser() {
        inventory = new LoginPage(page).loginAs(STANDARD_USER, PASSWORD); // Products page
    }

    @Test
    @DisplayName("TC-W01: Produkt hinzufügen")
    void addOneProduct() {
        // Act
        inventory.addToCart(BACKPACK);
        CartPage cart = inventory.openCart();
        // Assert: Badge = "1", Button heißt jetzt "Remove"
        assertThat(inventory.cartBadge()).hasText("1");
        assertThat(inventory.product(BACKPACK).getByRole(
                com.microsoft.playwright.options.AriaRole.BUTTON,
                new com.microsoft.playwright.Locator.GetByRoleOptions().setName("Remove"))).isVisible();
    }

    @Test
    @DisplayName("TC-W02: Zwei Produkte im Warenkorb")
    void addTwoProducts() {
        // Act
        inventory.addToCart(BACKPACK);
        inventory.addToCart(BIKE_LIGHT);
        CartPage cart = inventory.openCart();

        // Assert
        assertThat(cart.cartBadge()).hasText("2");
        assertThat(cart.itemNames()).hasText(new String[]{BACKPACK, BIKE_LIGHT});
    }

    // AAA
    @Test
    @DisplayName("TC-W03: Produkt entfernen")
    void removeProduct() {
        // Arrange: Backpack hinzufügen
        inventory.addToCart(BACKPACK);
        CartPage cart = inventory.openCart();
        // Act: "Remove" klicken
        cart.removeFromCart(BACKPACK);
        // Assert: Badge ist verschwunden
        assertThat(cart.cartBadge()).isHidden();
    }
}