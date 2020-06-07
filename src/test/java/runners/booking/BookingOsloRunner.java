package runners.booking;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"steps.cucumber.booking.trip_oslo"},
        features = {"src/test/resources/features/booking/BookingOslo.feature"
        }
)
public class BookingOsloRunner {
}