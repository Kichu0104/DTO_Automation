# Copilot / AI Agent Instructions (short)

Purpose: Help an AI coding agent become productive quickly in this repo (Maven Java + Selenium + Cucumber).

1) Big picture
- Java 17 Maven project. Minimal app entry at [src/main/java/com/insight/Main.java](src/main/java/com/insight/Main.java#L1).
- End-to-end UI automation lives in `src/test`: Cucumber feature files in [src/test/resources/feature](src/test/resources/feature/BusinessCapability.feature#L1), step definitions in [src/test/java/stepdefinition](src/test/java/stepdefinition/BusinessCapability.java#L1) and test runner(s) in [src/test/java/runner](src/test/java/runner/BusinessCapabilityExecution.java#L1).
- Tests use Selenium WebDriver + Cucumber (JUnit). `Hooks` class creates a static `driver` shared across steps: see [src/test/java/stepdefinition/Hooks.java](src/test/java/stepdefinition/Hooks.java#L1).

2) How tests are executed (developer workflows)
- Build system: Maven with Java 17. Use `mvn test` to run the Cucumber tests.
- Surefire is configured to include test classes matching `**/*Execution.java`. To run the runner directly use:

```bash
mvn -Dtest=runner.BusinessCapabilityExecution test
```

- CI considerations: `Hooks` already applies headless and CI-friendly Chrome flags (`--headless=new`, `--no-sandbox`, `--disable-dev-shm-usage`). Preserve these when editing test start-up logic.

3) Key project conventions an agent must follow
- Runner naming: test runners end with `Execution.java` and live in `runner` package.
- Feature location: `src/test/resources/feature/*.feature`. Glue code uses package `stepdefinition`.
- Shared WebDriver: `Hooks.driver` is static and referenced directly by step defs (e.g., `BusinessCapability` uses `Hooks.driver`). Do not replace with instance fields unless updating all steps consistently.
- Waits: code mixes implicit waits and explicit `WebDriverWait`. Prefer explicit waits for element visibility before interacting.

4) Integration points & sensitive values
- Tests interact with an external app at `http://3.131.133.70:3001/login` and contain hardcoded credentials in `BusinessCapability.java`. Treat these as secrets — do not commit new credentials; prefer environment variables or test fixtures when refactoring.
- The project includes `io.github.bonigarcia:webdrivermanager` in `pom.xml` but `Hooks` currently instantiates `new ChromeDriver(options)` directly. If adding driver management, update `Hooks` to call `WebDriverManager.chromedriver().setup()` first.

5) Useful file references (examples to inspect or modify)
- Runner: [src/test/java/runner/BusinessCapabilityExecution.java](src/test/java/runner/BusinessCapabilityExecution.java#L1)
- Step defs: [src/test/java/stepdefinition/BusinessCapability.java](src/test/java/stepdefinition/BusinessCapability.java#L1)
- Hooks: [src/test/java/stepdefinition/Hooks.java](src/test/java/stepdefinition/Hooks.java#L1)
- Feature: [src/test/resources/feature/BusinessCapability.feature](src/test/resources/feature/BusinessCapability.feature#L1)
- Build config: [pom.xml](pom.xml#L1)

6) Quick change patterns (examples)
- Add a new scenario: add a `.feature` under `src/test/resources/feature/` and a corresponding `@When/@Then` method in `stepdefinition` package; reuse `Hooks.driver`.
- Make `Hooks` use WebDriverManager (recommended snippet):

```java
// inside launchBrowser()
WebDriverManager.chromedriver().setup();
driver = new ChromeDriver(options);
```

- To run a single scenario locally from IDE, run the runner class `runner.BusinessCapabilityExecution` as a JUnit test.

7) What not to change without explicit confirmation
- Don't change CI/Headless flags in `Hooks` unless tests are validated locally and in CI.
- Don't replace hardcoded credentials in tests without providing an environment-variable alternative and updating README/CI secrets.

8) Typical fixes an agent will be asked to do (how to approach)
- Flaky locators: prefer replacing absolute XPaths with stable CSS selectors or shorter relative XPaths; add `WebDriverWait` around elements. Show before/after examples in the PR.
- Add WebDriverManager setup to `Hooks` rather than committing a local chromedriver path.
- Extract test data (URLs, credentials) to a properties file or environment variables and update step defs to read from those sources.

If anything above is unclear or you want more details (e.g., locally reproducible CI steps, secrets handling, or preferred PR format), tell me which area to expand.
