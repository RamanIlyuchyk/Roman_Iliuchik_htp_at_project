package runners;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.booking.BookingParisTest;
import tests.booking.BookingMoscowTest;
import tests.booking.BookingOsloTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({BookingParisTest.class, BookingMoscowTest.class, BookingOsloTest.class})
public class BookingRunner {
}