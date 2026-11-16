package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage extends BasePage {

    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator errorMessage;

    public LoginPage(Page page) {
        super(page);
        usernameInput = page.getByTestId("username");
        passwordInput = page.getByTestId("password");
        loginButton   = page.getByTestId("login-button");
        errorMessage  = page.getByTestId("error");
    }

    /** Formular ausfüllen und absenden – für Negativtests (bleibt auf der Login-Seite) */
    public void login(String username, String password) {
        usernameInput.fill(username);
        passwordInput.fill(password);
        loginButton.click();
    }

    /** Erfolgreicher Login – prüft die Vorbedingung und liefert die Produktseite */
    public InventoryPage loginAs(String username, String password) {
        login(username, password);
        assertThat(page).hasURL(BASE_URL + "/inventory.html");   // Guard Assertion
        return new InventoryPage(page);
    }

    public Locator errorMessage() { return errorMessage; }
    public Locator loginButton()  { return loginButton; }
}