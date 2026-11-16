# Testfallkatalog – Swag Labs

Testbenutzer und Testdaten: siehe [Testkonzept, Abschnitt 6](testkonzept.md#6-testdaten).
Passwort, falls nicht anders angegeben: `secret_sauce`.

**Status:** ⬜ offen · ✅ automatisiert · ❌ Abweichung gefunden

## Übersicht

| ID | Modul | Titel | Prio | Status |
|---|---|---|---|---|
| TC-S01 | Smoke | Startseite lädt | Hoch | ⬜ |
| TC-L01 | Login | Login mit gültigen Daten | Hoch | ⬜ |
| TC-L02 | Login | Gesperrter Benutzer erhält Fehlermeldung | Hoch | ⬜ |
| TC-L03 | Login | Falsches Passwort erhält Fehlermeldung | Hoch | ⬜ |
| TC-L04 | Login | Leere Felder erhalten Fehlermeldung | Mittel | ⬜ |
| TC-L05 | Login | Zugriff auf Produktseite ohne Login wird blockiert | Mittel | ⬜ |
| TC-P01 | Produkte | Es werden 6 Produkte angezeigt | Mittel | ⬜ |
| TC-P02 | Produkte | Sortierung „Price (low to high)" | Mittel | ⬜ |
| TC-P03 | Produkte | Sortierung „Name (Z to A)" | Niedrig | ⬜ |
| TC-W01 | Warenkorb | Produkt hinzufügen | Hoch | ⬜ |
| TC-W02 | Warenkorb | Zwei Produkte im Warenkorb | Hoch | ⬜ |
| TC-W03 | Warenkorb | Produkt entfernen | Mittel | ⬜ |
| TC-C01 | Checkout | Kompletter Kaufprozess (E2E) | Hoch | ⬜ |
| TC-C02a | Checkout | Vorname fehlt → „Error: First Name is required" | Mittel | ✅ |
| TC-C02b | Checkout | Nachname fehlt → „Error: Last Name is required" | Mittel | ✅ |
| TC-C02c | Checkout | PLZ fehlt → „Error: Postal Code is required" | Mittel | ✅ |
| TC-C02d | Checkout | Alle Felder leer → „Error: First Name is required" | Niedrig | ✅ || TC-C03 | Checkout | Preisberechnung | Mittel | ⬜ |
| TC-O01 | Logout | Logout über das Menü | Mittel | ⬜ |

---

## Smoke

### TC-S01 – Startseite lädt
| | |
|---|---|
| **Prio** | Hoch |
| **Vorbedingung** | – |
| **Schritte** | 1. https://www.saucedemo.com öffnen |
| **Erwartet** | Seitentitel ist „Swag Labs“. Die Felder „Username“ und „Password“ sowie der Button „Login“ werden angezeigt. |

---

## Login

### TC-L01 – Login mit gültigen Daten
| | |
|---|---|
| **Prio** | Hoch |
| **Vorbedingung** | Login-Seite ist geöffnet |
| **Schritte** | 1. Username `standard_user` eingeben<br>2. Password `secret_sauce` eingeben<br>3. „Login“ klicken |
| **Erwartet** | URL endet auf `/inventory.html`, Überschrift „Products“ wird angezeigt |

### TC-L02 – Gesperrter Benutzer erhält Fehlermeldung
| | |
|---|---|
| **Prio** | Hoch |
| **Vorbedingung** | Login-Seite ist geöffnet |
| **Schritte** | 1. Username `locked_out_user` eingeben<br>2. Password `secret_sauce` eingeben<br>3. „Login“ klicken |
| **Erwartet** | Fehlermeldung „Epic sadface: Sorry, this user has been locked out.“; Benutzer bleibt auf der Login-Seite |

### TC-L03 – Falsches Passwort erhält Fehlermeldung
| | |
|---|---|
| **Prio** | Hoch |
| **Vorbedingung** | Login-Seite ist geöffnet |
| **Schritte** | 1. Username `standard_user` eingeben<br>2. Password `falsch` eingeben<br>3. „Login“ klicken |
| **Erwartet** | Fehlermeldung „Epic sadface: Username and password do not match any user in this service“ |

### TC-L04 – Leere Felder erhalten Fehlermeldung
| | |
|---|---|
| **Prio** | Mittel |
| **Vorbedingung** | Login-Seite ist geöffnet |
| **Schritte** | 1. Beide Felder leer lassen<br>2. „Login“ klicken |
| **Erwartet** | Fehlermeldung „Epic sadface: Username is required“ |

### TC-L05 – Zugriff auf Produktseite ohne Login wird blockiert
| | |
|---|---|
| **Prio** | Mittel |
| **Vorbedingung** | Nicht eingeloggt |
| **Schritte** | 1. https://www.saucedemo.com/inventory.html direkt aufrufen |
| **Erwartet** | Login-Seite mit Fehlermeldung „Epic sadface: You can only access '/inventory.html' when you are logged in.“ |

---

## Produkte

### TC-P01 – Es werden 6 Produkte angezeigt
| | |
|---|---|
| **Prio** | Mittel |
| **Vorbedingung** | Eingeloggt als `standard_user` |
| **Schritte** | 1. Produktseite betrachten |
| **Erwartet** | Genau 6 Produkte werden angezeigt |

### TC-P02 – Sortierung „Price (low to high)“
| | |
|---|---|
| **Prio** | Mittel |
| **Vorbedingung** | Eingeloggt als `standard_user` |
| **Schritte** | 1. Sortierung „Price (low to high)“ wählen |
| **Erwartet** | Erstes Produkt: „Sauce Labs Onesie“ ($7.99), letztes Produkt: „Sauce Labs Fleece Jacket“ ($49.99) |

### TC-P03 – Sortierung „Name (Z to A)“
| | |
|---|---|
| **Prio** | Niedrig |
| **Vorbedingung** | Eingeloggt als `standard_user` |
| **Schritte** | 1. Sortierung „Name (Z to A)“ wählen |
| **Erwartet** | Erstes Produkt: „Test.allTheThings() T-Shirt (Red)“, letztes Produkt: „Sauce Labs Backpack“ |

---

## Warenkorb

### TC-W01 – Produkt hinzufügen
| | |
|---|---|
| **Prio** | Hoch |
| **Vorbedingung** | Eingeloggt als `standard_user`, Warenkorb leer |
| **Schritte** | 1. Bei „Sauce Labs Backpack“ auf „Add to cart“ klicken |
| **Erwartet** | Warenkorb-Symbol zeigt „1“, Button wechselt zu „Remove“ |

### TC-W02 – Zwei Produkte im Warenkorb
| | |
|---|---|
| **Prio** | Hoch |
| **Vorbedingung** | Eingeloggt als `standard_user`, Warenkorb leer |
| **Schritte** | 1. „Sauce Labs Backpack“ hinzufügen<br>2. „Sauce Labs Bike Light“ hinzufügen<br>3. Warenkorb öffnen |
| **Erwartet** | Warenkorb-Symbol zeigt „2“; beide Produkte stehen im Warenkorb |

### TC-W03 – Produkt entfernen
| | |
|---|---|
| **Prio** | Mittel |
| **Vorbedingung** | Eingeloggt, „Sauce Labs Backpack“ im Warenkorb |
| **Schritte** | 1. Bei „Sauce Labs Backpack“ auf „Remove“ klicken |
| **Erwartet** | Warenkorb-Symbol zeigt keine Zahl mehr |

---

## Checkout

### TC-C01 – Kompletter Kaufprozess (E2E)
| | |
|---|---|
| **Prio** | Hoch |
| **Vorbedingung** | Eingeloggt als `standard_user` |
| **Schritte** | 1. „Sauce Labs Backpack“ hinzufügen<br>2. Warenkorb öffnen, „Checkout“ klicken<br>3. `Max` / `Mustermann` / `86150` eingeben, „Continue“<br>4. „Finish“ klicken |
| **Erwartet** | Meldung „Thank you for your order!“ wird angezeigt |

### TC-C02 – Fehlender Vorname
| | |
|---|---|
| **Prio** | Mittel |
| **Vorbedingung** | Eingeloggt, ein Produkt im Warenkorb, Seite „Checkout: Your Information“ |
| **Schritte** | 1. Vorname leer lassen, `Mustermann` / `86150` eingeben<br>2. „Continue“ klicken |
| **Erwartet** | Fehlermeldung „Error: First Name is required“ |

### TC-C03 – Preisberechnung
| | |
|---|---|
| **Prio** | Mittel |
| **Vorbedingung** | Eingeloggt, „Sauce Labs Backpack“ ($29.99) und „Sauce Labs Bike Light“ ($9.99) im Warenkorb |
| **Schritte** | 1. Checkout bis zur Seite „Checkout: Overview“ durchführen |
| **Erwartet** | Item total: $39.98 · Tax: $3.20 · Total: $43.18 (Item total + Tax = Total) |

---

## Logout

### TC-O01 – Logout über das Menü
| | |
|---|---|
| **Prio** | Mittel |
| **Vorbedingung** | Eingeloggt als `standard_user` |
| **Schritte** | 1. Menü öffnen („Open Menu“)<br>2. „Logout“ klicken |
| **Erwartet** | Login-Seite wird angezeigt, der „Login“-Button ist sichtbar |
