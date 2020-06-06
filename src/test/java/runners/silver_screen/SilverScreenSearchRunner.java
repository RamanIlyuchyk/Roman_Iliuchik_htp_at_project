package runners.silver_screen;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"tests.cucumber.silver_screen.search"},
        features = {"src/test/resources/features/silver_screen/SilverScreenSearch.feature"
        }
)
public class SilverScreenSearchRunner {
}