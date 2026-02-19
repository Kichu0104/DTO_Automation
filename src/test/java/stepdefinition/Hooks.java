package stepdefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.After;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

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
        
        // Archive reports after all scenarios complete
        archiveExecutionReports();
    }

    /**
     * Archives the cucumber reports to a timestamped backup folder
     * This ensures each execution's reports are preserved and not overwritten
     */
    private static void archiveExecutionReports() {
        try {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            Path reportSourceDir = Paths.get("target/cucumber-report");
            Path reportArchiveDir = Paths.get("target/cucumber-report-archive");
            Path timestampedDir = reportArchiveDir.resolve(timestamp);

            // Create archive directory if it doesn't exist
            if (!Files.exists(reportArchiveDir)) {
                Files.createDirectories(reportArchiveDir);
            }

            // Create timestamped subdirectory
            Files.createDirectories(timestampedDir);

            // Copy all report files to timestamped directory
            if (Files.exists(reportSourceDir)) {
                try (Stream<Path> paths = Files.list(reportSourceDir)) {
                    paths.filter(Files::isRegularFile)
                            .forEach(source -> {
                                try {
                                    Path destination = timestampedDir.resolve(source.getFileName());
                                    Files.copy(source, destination, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                                } catch (IOException e) {
                                    System.err.println("Failed to archive report file: " + source.getFileName());
                                    e.printStackTrace();
                                }
                            });
                }
                System.out.println("========================================");
                System.out.println("✅ Reports archived to: " + timestampedDir);
                System.out.println("========================================");
            }
        } catch (IOException e) {
            System.err.println("Error archiving execution reports:");
            e.printStackTrace();
        }
    }
}