package runners.silver_screen;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import web_driver.Driver;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"steps.cucumber.silver_screen.blank_field"},
        features = {"src/test/resources/features/silver_screen/SilverScreenBlankField.feature"
        }
)
public class SilverScreenBlankFieldRunner {
    @AfterClass
    public static void postCondition() {
        Driver.destroy();
    }
}