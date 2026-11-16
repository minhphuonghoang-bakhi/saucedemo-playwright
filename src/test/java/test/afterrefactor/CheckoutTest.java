package test.afterrefactor;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static data.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckoutTest extends BaseTest {

    private InventoryPage inventory;

    @BeforeEach
    void loginAsStandardUser() {
        inventory = new LoginPage(page).loginAs(STANDARD_USER, PASSWORD);
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

    @ParameterizedTest(name = "{0}")
    @CsvFileSource(resources = "/testdata/checkout-validation.csv", numLinesToSkip = 1)
    void missingCustomerDataShowsError(String testCase, String firstName, String lastName,
                                       String postalCode, String expectedError) {
        // Arrange
        inventory.addToCart(BACKPACK);
        CheckoutInfoPage info = inventory.openCart().checkout();

        // Act
        info.fillAndContinue(firstName, lastName, postalCode);

        // Assert
        assertThat(info.errorMessage()).hasText(expectedError);
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
                .continueWith(MAX);

        // Assert 1: konkrete Werte
        assertThat(overview.itemTotalLabel()).hasText("Item total: $39.98");
        assertThat(overview.taxLabel()).hasText("Tax: $3.20");
        assertThat(overview.totalLabel()).hasText("Total: $43.18");

        // Assert 2: Rechenregel
        assertEquals(overview.itemTotal() + overview.tax(), overview.total(), 0.001);
    }
}
