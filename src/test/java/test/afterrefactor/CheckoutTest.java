package test.afterrefactor;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckoutTest extends BaseTest {

    private static final String BACKPACK = "Sauce Labs Backpack";
    private static final String BIKE_LIGHT = "Sauce Labs Bike Light";

    private InventoryPage inventory;

    @BeforeEach
    void loginAsStandardUser() {
        inventory = new LoginPage(page).loginAs("standard_user", "secret_sauce");
    }

    @Test
    @DisplayName("TC-C01: Kompletter Kaufprozess (E2E)")
    void completePurchase() {
        // Arrange
        // already login before each test
        inventory.addToCart(BACKPACK);

        // Act
        CheckoutCompletePage complete = inventory.openCart()
                .checkout()
                .continueWith("Max", "Mustermann", "86150")
                .finish();

        // Assert
        assertThat(complete.completeHeader()).hasText("Thank you for your order!");
    }

    @Test
    @DisplayName("TC-C02: Fehlender Vorname")
    void missingFirstNameShowsError() {
        inventory.addToCart(BACKPACK);

        // dont use continueWith (return CheckoutCompletePage means successfully going to this page)
        CheckoutInfoPage info = inventory.openCart().checkout();
        info.fillAndContinue("","Mustermann","86150");

        assertThat(info.errorMessage()).hasText("Error: First Name is required");
    }

    @Test
    @DisplayName("TC-C03: Preisberechnung")
    void priceCalculation() {
        // Arrange
        inventory.addToCart(BACKPACK);
        inventory.addToCart(BIKE_LIGHT);

        // Act
        CheckoutOverviewPage overview = inventory.openCart()
                .checkout()
                .continueWith("Max", "Mustermann", "86150");

        // Assert 1: konkrete Werte
        assertThat(overview.itemTotalLabel()).hasText("Item total: $39.98");
        assertThat(overview.taxLabel()).hasText("Tax: $3.20");
        assertThat(overview.totalLabel()).hasText("Total: $43.18");

        // Assert 2: Rechenregel
        assertEquals(overview.itemTotal() + overview.tax(), overview.total(), 0.001);
    }
}
