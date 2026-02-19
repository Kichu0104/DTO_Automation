package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Test Runner for Business Capability automation tests
 * Executes Cucumber tests with specified configurations
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/feature/BusinessCapability.feature",
        glue = "stepdefinition",
        tags = "@businessCapability",
        plugin = {
                "pretty",
                "html:target/cucumber-report/cucumber-report.html",
                "json:target/cucumber-report/cucumber-report.json",
                "junit:target/cucumber-report/cucumber-report.xml"
        },
        monochrome = true,
        dryRun = false,
        publish = true
)
public class BusinessCapabilityExecution {
    /**
     * This test runner class serves as the entry point for executing
     * Cucumber scenarios defined in BusinessCapability.feature file.
     * 
     * Configuration Details:
     * - Features: Reads feature files from src/test/resources/feature/ directory
     * - Glue: Connects step definitions from stepdefinition package
     * - Tags: Executes scenarios tagged with @businessCapability
     * - Plugins: Generates HTML, JSON, and JUnit reports
     * - Publish: Publishes results to Cucumber Reports
     */
}
