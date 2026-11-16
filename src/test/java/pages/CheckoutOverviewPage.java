package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutOverviewPage extends BasePage {

    public CheckoutOverviewPage(Page page) {
        super(page);
    }

    public Locator itemTotalLabel() {
        return page.getByTestId("subtotal-label");
    }
    public Locator taxLabel()       {
        return page.getByTestId("tax-label");
    }
    public Locator totalLabel()     {
        return page.getByTestId("total-label");
    }

    /** "Item total: $39.98" → 39.98 */
    public double itemTotal() { return amountOf(itemTotalLabel()); }
    public double tax()       { return amountOf(taxLabel()); }
    public double total()     { return amountOf(totalLabel()); }

    /** Liest den Betrag hinter dem "$" aus einem Label */
    private double amountOf(Locator label) {
        // TODO: textContent() lesen, Teil nach "$" nehmen, in double umwandeln
        String text = label.textContent(); // "Item total: $45.98"
        String number = text.substring(text.indexOf("$") + 1);  // "45.98"
        return Double.parseDouble(number);  // 45.98
    }

    public CheckoutCompletePage finish() {
        // TODO: "Finish" klicken (data-test="finish")
        page.getByTestId("finish").click();
        return new CheckoutCompletePage(page);
    }
}
