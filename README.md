# Swag Labs – UI-Testautomatisierung mit Playwright (Java)

Automatisierte UI-Tests für den Demo-Onlineshop [saucedemo.com](https://www.saucedemo.com),
umgesetzt mit **Playwright for Java** und **JUnit 5**.

Ich habe das Projekt aufgebaut, um professionelle Testautomatisierung zu üben:
vom Testkonzept über einen Testfallkatalog bis zu wartbaren, automatisierten Tests in einer CI-Pipeline.

## Tech-Stack

| | |
|---|---|
| Sprache | Java 17+ |
| Testautomatisierung | Playwright for Java |
| Testframework | JUnit 5 |
| Build | Maven |
| CI | GitHub Actions *(geplant)* |

## Voraussetzungen

- JDK 17 oder neuer
- Maven 3.9+ (oder das in IntelliJ integrierte Maven)
- Internetzugang (saucedemo.com)

Die Browser lädt Playwright beim ersten Testlauf automatisch herunter.

## Tests ausführen

```bash
# Alle Tests (Browser sichtbar)
mvn test

# Alle Tests ohne sichtbaren Browser (wie in CI)
mvn test -Dheadless=true

# Nur eine Testklasse
mvn test -Dtest=SmokeTest
```

In IntelliJ: grünen ▶-Button neben einer Testklasse oder Testmethode klicken.

## Projektstruktur

```
src/test/
├── java/
│   ├── base/        BaseTest – Browser-Setup, frischer Context pro Test
│   ├── data/        TestData, Customer – zentrale Testdaten
│   ├── pages/       Page Objects (Login, Inventory, Cart, Checkout …)
│   └── tests/       Testklassen je Modul
└── resources/
    └── testdata/    CSV-Dateien für datengetriebene Tests
```

## Dokumentation

- [Testkonzept](docs/testkonzept.md)
- [Testfallkatalog](docs/testfaelle.md)

## Status

| Modul | Testfälle | Automatisiert |
|---|---|---|
| Smoke | 1 | 0 |
| Login | 5 | 0 |
| Produkte | 3 | 0 |
| Warenkorb | 3 | 0 |
| Checkout | 3 | 0 |
| Logout | 1 | 0 |
