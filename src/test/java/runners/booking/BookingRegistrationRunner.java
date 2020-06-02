package runners.booking;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"tests.cucumber.booking.create_new_user"},
        features = {"src/test/resources/features/BookingRegistration.feature"
        }
)
public class BookingRegistrationRunner {
}