package runners.fake;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"steps.fake"},
        features = {"src/test/resources/features/fake/FakeTwo.feature"}
)
public class FakeRunner2 {
}