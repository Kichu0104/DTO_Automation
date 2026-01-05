package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/feature/BusinessCapability.feature",
        glue = "stepdefinition",
        tags = "@businessCapability",
        plugin = {
                "pretty",
                "html:target/cucumber-report.html"
        },
        monochrome = true
)
public class BusinessCapabilityExecution {    
}
