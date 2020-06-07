package runners.booking;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"steps.cucumber.booking.create_new_user"},
        features = {"src/test/resources/features/booking/BookingRegistration.feature"
        }
)
public class BookingRegistrationRunner {
}