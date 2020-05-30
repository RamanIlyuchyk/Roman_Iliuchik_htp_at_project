package runners.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"tests.booking.tripOslo"},
        features = {"src/test/resources/features/bookingOslo.feature"
        },
        snippets = SnippetType.CAMELCASE
)
public class BookingOsloRunner {
}