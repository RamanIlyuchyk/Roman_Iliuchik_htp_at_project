package runners.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"tests.booking.checkHeader"},
        features = {"src/test/resources/features/bookingCheckHeader.feature"
        },
        snippets = SnippetType.CAMELCASE
)
public class BookingCheckHeaderRunner {
}