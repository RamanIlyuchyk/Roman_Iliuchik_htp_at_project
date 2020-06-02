package runners.booking;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"tests.cucumber.booking.check_header"},
        features = {"src/test/resources/features/BookingCheckHeader.feature"
        }
)
public class BookingCheckHeaderRunner {
}