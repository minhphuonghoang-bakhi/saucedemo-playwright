package tests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class SmokeTest extends BaseTest {

    @Test
    @DisplayName("TC-S01: Startseite lädt")
    void startPageLoads() {
        // TODO 1: Prüfe, dass der Seitentitel "Swag Labs" ist
        assertThat(page).hasTitle("Swag Labs");
        // TODO 2: Prüfe, dass das Feld "Username" sichtbar ist (Username is placeholder)
        Locator username = page.getByTestId("username");  // access data-test = "username"
        assertThat(username).isVisible();
        // TODO 3: Prüfe, dass das Feld "Password" sichtbar ist
        assertThat(page.getByPlaceholder("Password")).isVisible();
        // TODO 4: Prüfe, dass der Button "Login" sichtbar ist
        // Locator login = page.getByTestId("login-button");
        Locator login = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
        assertThat(login).isVisible();
    }


}