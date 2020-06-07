package runners.booking;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"steps.cucumber.booking.add_favorites"},
        features = {"src/test/resources/features/booking/BookingAddFavorites.feature"
        }
)
public class BookingAddFavoritesRunner {
}