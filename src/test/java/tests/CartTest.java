package tests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class CartTest extends BaseTest {
    private static final String BACKPACK = "Sauce Labs Backpack";
    private static final String BIKE_LIGHT = "Sauce Labs Bike Light";

    // Vorbedingungen: Warenkorb leer - jeder Testcase hat eigene Session.

    @BeforeEach
    void loginAsStandardUser() {
        // TODO: einloggen + Guard Assertion (URL)
        page.getByTestId("username").fill("standard_user");
        page.getByTestId("password").fill("secret_sauce");
        page.getByTestId("login-button").click();
        assertThat(page).hasURL(BASE_URL + "/inventory.html");
    }

    /** Liefert die Produktkarte mit dem angegebenen Namen */
    private Locator product(String name) {
        // TODO: inventory-item filtern nach Name
        return page.getByTestId("inventory-item")
                .filter(new Locator.FilterOptions().setHasText(name));
    }

    /** Klickt bei einem Produkt auf "Add to cart" */
    private void addToCart(String name) {
        // TODO: Button "Add to cart" INNERHALB von product(name) klicken
        product(name).getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Add to cart")).click();
        // not page.getByRole(...) because this is the button of product
    }

    @Test
    @DisplayName("TC-W01: Produkt hinzufügen")
    void addOneProduct() {
        // Act
        addToCart(BACKPACK);
        page.getByTestId("shopping-cart-link").click(); // because this data-test has role = button
        // Assert: Badge = "1", Button heißt jetzt "Remove"
        assertThat(page.getByTestId("shopping-cart-badge")).hasText("1");
    }

    @Test
    @DisplayName("TC-W02: Zwei Produkte im Warenkorb")
    void addTwoProducts() {
        // Act: zwei Produkte hinzufügen, Warenkorb öffnen
        addToCart(BACKPACK);
        addToCart(BIKE_LIGHT);
        page.getByTestId("shopping-cart-link").click(); // because this data-test has role = button
        // Assert: Badge = "2", beide Namen im Warenkorb sichtbar
        assertThat(page.getByTestId("shopping-cart-badge")).hasText("2");
        assertThat(page.getByTestId("inventory-item-name")).hasText(new String[]{BACKPACK, BIKE_LIGHT});
    }

    // AAA
    @Test
    @DisplayName("TC-W03: Produkt entfernen")
    void removeProduct() {
        // Arrange: Backpack hinzufügen
        addToCart(BACKPACK);
        page.getByTestId("shopping-cart-link").click();
        // Act: "Remove" klicken
        product(BACKPACK)
                .getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Remove"))
                .click(); // find the product and click the remove inside this product
        // Assert: Badge ist verschwunden
        assertThat(page.getByTestId("shopping-cart-badge")).isHidden();
    }
}

