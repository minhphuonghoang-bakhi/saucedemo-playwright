package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutInfoPage extends BasePage {

    public CheckoutInfoPage(Page page) {
        super(page);
    }

    /** Formular ausfüllen und "Continue" klicken – für Negativtests (bleibt auf der Seite) */
    public void fillAndContinue(String firstName, String lastName, String postalCode) {
        // TODO: firstName, lastName, postalCode ausfüllen, "continue" klicken
        page.getByTestId("firstName").fill(firstName);
        page.getByTestId("lastName").fill(lastName);
        page.getByTestId("postalCode").fill(postalCode);
        page.getByTestId("continue").click();
    }

    /** Erfolgreicher Fall – liefert die Übersichtsseite */
    public CheckoutOverviewPage continueWith(String firstName, String lastName, String postalCode) {
        fillAndContinue(firstName, lastName, postalCode);
        return new CheckoutOverviewPage(page);
    }

    public Locator errorMessage() {
        return page.getByTestId("error");
    }
}