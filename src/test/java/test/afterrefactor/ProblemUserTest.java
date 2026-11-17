package test.afterrefactor;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CheckoutOverviewPage;
import pages.InventoryPage;
import pages.LoginPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static data.TestData.*;

/**
 * Tests mit problem_user. Sie beschreiben das ERWARTETE Verhalten
 * und schlagen fehl, solange die bekannten Fehler bestehen.
 * Black-Box-Test
 */
class ProblemUserTest extends BaseTest {

    private InventoryPage inventory;

    @BeforeEach
    void loginAsProblemUser() {
        inventory = new LoginPage(page).loginAs(PROBLEM_USER, PASSWORD);
    }

    @Test
    @Disabled("Bekannter Fehler BUG-001 – wieder aktivieren, wenn behoben")
    @DisplayName("BUG-001: Checkout-Formular übernimmt den Nachnamen nicht")
    void checkoutWithProblemUser() {
        // Arrange
        inventory.addToCart(BACKPACK);

        // Act
        CheckoutOverviewPage overview = inventory.openCart()
                .checkout()
                .continueWith(MAX);

        // Assert: Übersichtsseite wird erreicht
        assertThat(page).hasURL(BASE_URL + "/checkout-step-two.html");
        assertThat(overview.itemTotalLabel()).isVisible();
    }
}