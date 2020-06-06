package runners.silver_screen;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"tests.cucumber.silver_screen.login"},
        features = {"src/test/resources/features/silver_screen/SilverScreenLogin.feature"
        }
)
public class SilverScreenLoginRunner {
}