package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Test Runner for Value Stream automation tests
 * Executes Cucumber tests with specified configurations
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/feature/ValueStream.feature", glue = "stepdefinition", tags = "@ValueStream", plugin = {
                "pretty",
                "html:target/cucumber-report/cucumber-report.html",
                "json:target/cucumber-report/cucumber-report.json",
                "junit:target/cucumber-report/cucumber-report.xml"
}, monochrome = true, dryRun = false, publish = true)
public class ValueStreamExecution {
}
