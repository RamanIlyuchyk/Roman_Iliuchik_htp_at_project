package runners.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"tests.booking.tripMoscow"},
        features = {"src/test/resources/features/bookingMoscow.feature"
        },
        snippets = SnippetType.CAMELCASE
)
public class BookingMoscowRunner {
}