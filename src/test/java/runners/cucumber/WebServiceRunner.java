package runners.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"tests.webService"},
        features = {"src/test/resources/features/webService.feature"
        },
        snippets = SnippetType.CAMELCASE
)
public class WebServiceRunner {
}