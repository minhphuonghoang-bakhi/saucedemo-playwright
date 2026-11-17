package base;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.nio.file.Paths;

/**
 * Gemeinsame Basis für alle Tests.
 * Ein Browser pro Testklasse, ein frischer BrowserContext (= Inkognito-Fenster) pro Test.
 * Bei einem Fehler werden Screenshot und Trace automatisch gespeichert.
 */
public class BaseTest {
    protected static final String BASE_URL = "https://www.saucedemo.com";

    // Lokal: Browser sichtbar. In CI: mvn test -Dheadless=true
    private static final boolean HEADLESS = Boolean.getBoolean("headless");

    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeAll
    public static void launchBrowser() {
        playwright = Playwright.create();
        // saucedemo nutzt data-test="..." statt data-testid="..."
        playwright.selectors().setTestIdAttribute("data-test");
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(HEADLESS)
                        .setSlowMo(HEADLESS ? 0 : 300));
    }

    @AfterAll
    public static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    public void openPage() {
        context = browser.newContext();
        // Aufzeichnung starten: Screenshots + DOM-Snapshots jedes Schritts
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true));
        page = context.newPage();
        page.navigate(BASE_URL);
    }

    /**
     * Läuft direkt NACH dem Test und VOR @AfterEach (Context ist noch offen).
     * Bei Fehler: Screenshot und Trace speichern. Sonst: Aufzeichnung verwerfen.
     */
    @RegisterExtension
    protected AfterTestExecutionCallback saveArtifactsOnFailure = extensionContext -> {
        boolean failed = extensionContext.getExecutionException().isPresent();
        String name = fileName(extensionContext.getRequiredTestClass().getSimpleName()
                + "_" + extensionContext.getDisplayName());

        if (failed) {
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("screenshots", name + ".png"))
                    .setFullPage(true));
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("traces", name + ".zip")));
        } else {
            context.tracing().stop();
        }
    };

    @AfterEach
    public void closePage() {
        context.close();
    }

    /** "TC-C02a: Vorname fehlt" → "TC-C02a__Vorname_fehlt" (gültiger Dateiname) */
    private static String fileName(String text) {
        return text.replaceAll("[^a-zA-Z0-9-]", "_");
    }
}