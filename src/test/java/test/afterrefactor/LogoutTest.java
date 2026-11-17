package test.afterrefactor;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import pages.LoginPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static data.TestData.*;

class LogoutTest extends BaseTest {

    private InventoryPage inventory;

    @BeforeEach
    void loginAsStandardUser() {
        inventory = new LoginPage(page).loginAs(STANDARD_USER, PASSWORD);
    }

    @Test
    @DisplayName("TC-O01: Logout über das Menü")
    void logoutReturnsToLoginPage() {
        // Act
        LoginPage loginPage = inventory.logout();

        // Assert
        assertThat(loginPage.loginButton()).isVisible();
        assertThat(inventory.items()).hasCount(0);
    }
}