package runners;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.booking.tripParis.BookingParisTest;
import tests.booking.tripMoscow.BookingMoscowTest;
import tests.booking.tripOslo.BookingOsloTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({BookingParisTest.class, BookingMoscowTest.class, BookingOsloTest.class})
public class BookingRunner {
}