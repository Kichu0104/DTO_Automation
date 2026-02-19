package stepdefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.After;

public class Hooks {

    public static WebDriver driver;

    @BeforeAll
    public static void launchBrowserOnce() {
        if (driver == null) {
            System.out.println("========================================");
            System.out.println("Launching browser ONCE for all scenarios");
            System.out.println("========================================");

            ChromeOptions options = new ChromeOptions();

            // options.addArguments("--headless=new"); // enable in CI
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--start-maximized");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

            System.out.println("Browser launched successfully");
        }
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("➡️ Starting Scenario: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("⬅️ Scenario Status: " + scenario.getStatus());
    }

    @AfterAll
    public static void closeBrowserOnce() {
        if (driver != null) {
            System.out.println("========================================");
            System.out.println("Closing browser AFTER all scenarios");
            System.out.println("========================================");

            driver.quit();
            driver = null;

            System.out.println("Browser closed successfully");
        }
    }
}