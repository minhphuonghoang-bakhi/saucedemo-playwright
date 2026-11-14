package tests;

import base.BaseTest;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class LoginTest extends BaseTest {
    private static final String PASSWORD = "secret_sauce";

    /** Hilfsmethode: Login-Formular ausfüllen und absenden */
    private void login(String username, String password) {
        // TODO A: Username eintragen
        page.getByTestId("username").fill(username);
        // TODO B: Password eintragen
        page.getByTestId("password").fill(password);
        // TODO C: Login-Button klicken
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
    }

    @Test
    @DisplayName("TC-L01: Login mit gültigen Daten")
    // login erfolgreich -> andere Seite öffnet
    void standardUserCanLogin() {
        login("standard_user", PASSWORD);
        // TODO 1: Prüfe die URL (BASE_URL + "/inventory.html")
        assertThat(page).hasURL(BASE_URL + "/inventory.html");
        // TODO 2: Prüfe, dass der Seitentitel-Bereich "Products" anzeigt
        assertThat(page.getByTestId("title")).hasText("Products");
    }

    @Test
    @DisplayName("TC-L02: Gesperrter Benutzer erhält Fehlermeldung")
    void lockedOutUserSeesError() {
        // TODO: Login + Fehlermeldung prüfen + prüfen, dass der Login-Button noch sichtbar ist

    }

    @Test
    @DisplayName("TC-L03: Falsches Passwort erhält Fehlermeldung")
    void wrongPasswordSeesError() {
        // TODO
    }

    @Test
    @DisplayName("TC-L04: Leere Felder erhalten Fehlermeldung")
    void emptyFieldsSeeError() {
        // TODO (Tipp: Was übergibst du an login(), wenn die Felder leer sein sollen?)
    }

    @Test
    @DisplayName("TC-L05: Zugriff auf Produktseite ohne Login wird blockiert")
    void inventoryWithoutLoginIsBlocked() {
        // TODO: Seite direkt aufrufen + Fehlermeldung prüfen
    }

}
