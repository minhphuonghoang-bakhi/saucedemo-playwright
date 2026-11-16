package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import data.Customer;

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

    /** Overloading */
    /** Erfolgreicher Fall – liefert die Übersichtsseite */
    public CheckoutOverviewPage continueWith(String firstName, String lastName, String postalCode) {
        fillAndContinue(firstName, lastName, postalCode);
        return new CheckoutOverviewPage(page);
    }

    /** Erfolgreicher Fall mit einem Kunden aus den Testdaten */
    public CheckoutOverviewPage continueWith(Customer customer) {
        return continueWith(customer.firstName(), customer.lastName(), customer.postalCode());
    }

    public Locator errorMessage() {
        return page.getByTestId("error");
    }
}