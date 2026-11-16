package data;

/** Zentrale Testdaten – nur hier pflegen */
public final class TestData {

    private TestData() {
        // nur Konstanten, keine Objekte
    }

    // Benutzer (Passwort für alle gleich)
    public static final String PASSWORD        = "secret_sauce";
    public static final String STANDARD_USER   = "standard_user";
    public static final String LOCKED_OUT_USER = "locked_out_user";

    // Produkte
    public static final String BACKPACK      = "Sauce Labs Backpack";
    public static final String BIKE_LIGHT    = "Sauce Labs Bike Light";
    public static final String ONESIE        = "Sauce Labs Onesie";
    public static final String FLEECE_JACKET = "Sauce Labs Fleece Jacket";
    public static final String RED_TSHIRT    = "Test.allTheThings() T-Shirt (Red)";

    // Kunden
    public static final Customer MAX = new Customer("Max", "Mustermann", "86150");
}