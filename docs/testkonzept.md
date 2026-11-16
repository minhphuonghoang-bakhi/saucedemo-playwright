# Testkonzept – Swag Labs (saucedemo.com)

| |                                  |
|---|----------------------------------|
| **Projekt** | UI-Testautomatisierung Swag Labs |
| **Autor** | Minh Phuong Hoang                |
| **Version** | 1.0                              |
| **Stand** | 16.11.2026                       |

## 1. Ziel

Die wichtigsten Geschäftsprozesse des Online-Shops „Swag Labs" werden automatisiert getestet,
damit Regressionen nach Änderungen schnell erkannt werden. Die Tests sind wartbar
(Page Object Model), reproduzierbar (isolierte Testfälle) und laufen automatisch in einer CI-Pipeline.

## 2. Testobjekt

| | |
|---|---|
| **Anwendung** | Swag Labs – Demo-Onlineshop von Sauce Labs |
| **URL** | https://www.saucedemo.com |
| **Typ** | Webanwendung (Single Page Application) |

## 3. Testumfang

### 3.1 Im Scope

| Modul | Funktionen |
|---|---|
| Login | Anmeldung, Fehlermeldungen, Zugriffsschutz |
| Produkte | Produktliste, Sortierung |
| Warenkorb | Hinzufügen, Entfernen, Anzeige im Warenkorb |
| Checkout | Kundendaten, Übersicht, Preisberechnung, Bestellabschluss |
| Logout | Abmeldung über das Menü |

### 3.2 Nicht im Scope

- Performance- und Lasttests
- Visuelle Tests (Pixelvergleich)
- Barrierefreiheit (Accessibility)
- Mobile Browser
- Backend / Datenbank (kein Zugriff)

## 4. Teststrategie

| Testart | Beschreibung |
|---|---|
| **Smoke-Test** | Prüft, ob die Anwendung grundsätzlich erreichbar ist. Läuft zuerst. |
| **Funktionale Tests** | Positiv- und Negativtests je Modul |
| **End-to-End-Test** | Kompletter Kaufprozess vom Login bis zur Bestellbestätigung |
| **Regressionstests** | Alle automatisierten Tests laufen bei jedem Push (CI) |

**Testentwurfsverfahren:** Äquivalenzklassen (gültige / ungültige / leere Eingaben),
anwendungsfallbasierter Test (Kaufprozess).

**Priorisierung:** Hoch = Kernprozess (Login, Warenkorb, Kauf) · Mittel = wichtige Nebenfunktion ·
Niedrig = Komfortfunktion.

## 5. Testumgebung und Werkzeuge

| Bereich | Werkzeug |
|---|---|
| Programmiersprache | Java 17+ |
| Testautomatisierung | Playwright for Java 1.49 |
| Testframework | JUnit 5 |
| Build | Maven |
| Browser | Chromium (lokal sichtbar, in CI headless) |
| IDE | IntelliJ IDEA |
| Versionsverwaltung | Git / GitHub |
| CI | GitHub Actions |

## 6. Testdaten

Die Demo-Anwendung stellt feste Testbenutzer bereit (öffentlich auf der Login-Seite).
Passwort für alle Benutzer: `secret_sauce`.

| Benutzer | Verhalten | Verwendung |
|---|---|---|
| `standard_user` | Normaler Benutzer | Positivtests |
| `locked_out_user` | Gesperrt | Negativtest Login |
| `problem_user` | Enthält bekannte Fehler | Fehlerbericht (Aufgabe 8) |

Kundendaten im Checkout: Vorname `Max`, Nachname `Mustermann`, PLZ `86150`.

**Ablage der Testdaten**
- Feste Testdaten (Benutzer, Produkte, Kunden): `src/test/java/data/TestData.java`
- Datengetriebene Tests: CSV-Dateien unter `src/test/resources/testdata/`
  (z. B. `checkout-validation.csv`). Neue Fälle können ohne Codeänderung ergänzt werden.

## 7. Eingangs- und Endekriterien

**Eingangskriterien**
- saucedemo.com ist erreichbar
- Testfälle sind im Testfallkatalog beschrieben

**Endekriterien**
- Alle Testfälle mit Priorität „Hoch" sind automatisiert und grün
- Gefundene Abweichungen sind als Fehlerbericht dokumentiert
- Die Tests laufen erfolgreich in der CI-Pipeline

## 8. Konventionen

| Thema | Regel |
|---|---|
| Testfall-ID | `TC-<Modul><Nr>`, z. B. `TC-L01` (S = Smoke, L = Login, P = Produkte, W = Warenkorb, C = Checkout, O = Logout) |
| Testklasse | Eine Klasse pro Modul: `LoginTest`, `CartTest` … |
| Testmethode | Beschreibt das Verhalten: `lockedOutUserSeesError()` |
| `@DisplayName` | Testfall-ID + Titel: `"TC-L02: Gesperrter Benutzer erhält Fehlermeldung"` |
| Locator | Bevorzugt `getByRole`, `getByPlaceholder`, `getByTestId`; CSS nur wenn nötig |
| Warten | Kein `Thread.sleep` – Playwright wartet automatisch |
| Unabhängigkeit | Jeder Test läuft in einem eigenen BrowserContext und hängt von keinem anderen Test ab |
| Commits | `test: …` (neuer Test), `refactor: …`, `docs: …`, `ci: …` |

## 9. Risiken

| Risiko | Maßnahme |
|---|---|
| Demo-Seite ist nicht erreichbar | Smoke-Test schlägt zuerst fehl → klarer Hinweis |
| Änderungen an der Oberfläche | Stabile Locator (Rolle, data-test), Page Object Model |
| Flaky Tests durch Timing | Web-first Assertions (`assertThat`) statt einmaliger Prüfung |

## 10. Dokumente

- [Testfallkatalog](testfaelle.md)
- Fehlerberichte: `docs/fehlerberichte/` (ab Aufgabe 8)
