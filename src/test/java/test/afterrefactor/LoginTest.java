package test.afterrefactor;

import base.BaseTest;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import pages.InventoryPage;
import pages.LoginPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static data.TestData.*;

class LoginTest extends BaseTest {

    private LoginPage loginPage; // null

    // call @BeforeEach of BaseTest: page is now not null

    // without this, loginPage is always null
    @BeforeEach
    void openLoginPage() {
        loginPage = new LoginPage(page); // pass the "page" to loginPage
    }

    @Test
    @DisplayName("TC-L01: Login mit gültigen Daten") // login erfolgreich -> andere Seite öffnet
    void standardUserCanLogin() {
        InventoryPage inventory = loginPage.loginAs(STANDARD_USER, PASSWORD);
        // TODO 1: Prüfe, dass der Seitentitel-Bereich "Products" anzeigt
        assertThat(inventory.title()).hasText("Products");
    }

    @Test
    @DisplayName("TC-L02: Gesperrter Benutzer erhält Fehlermeldung")
    void lockedOutUserSeesError() {
        // TODO: Login + Fehlermeldung prüfen + prüfen, dass der Login-Button noch sichtbar ist
        loginPage.login(LOCKED_OUT_USER, PASSWORD);

        assertThat(loginPage.errorMessage()).hasText("Epic sadface: Sorry, this user has been locked out.");
        assertThat(loginPage.loginButton()).isVisible();
    }

    @Test
    @DisplayName("TC-L03: Falsches Passwort erhält Fehlermeldung")
    void wrongPasswordSeesError() {
        // TODO
        loginPage.login(STANDARD_USER, "falsch");

        assertThat(loginPage.errorMessage())
                .hasText("Epic sadface: Username and password do not match any user in this service");
    }

    @ParameterizedTest(name = "{0}")
    @CsvFileSource(resources = "/testdata/login-validation.csv", numLinesToSkip = 1)
    void missingFieldsShowError(String testCase, String username, String password, String expectedError) {
        loginPage.login(username, password);

        assertThat(loginPage.errorMessage()).hasText(expectedError);
    }

    @Test
    @DisplayName("TC-L05: Zugriff auf Produktseite ohne Login wird blockiert")
    void inventoryWithoutLoginIsBlocked() {
        page.navigate(BASE_URL + "/inventory.html");

        assertThat(loginPage.errorMessage())
                .hasText("Epic sadface: You can only access '/inventory.html' when you are logged in.");
    }
}
