package runners.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"steps"},
        features = {
                "src/test/resources/features/UsersApiTest.feature",
        },
        //tags = {"@qa or @prod"},
        monochrome = false,
        snippets = SnippetType.CAMELCASE,
        strict = false)
public class RunTests {
}