package runners.booking;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.junit.booking.trip_paris.BookingParisTest;
import tests.junit.booking.trip_moscow.BookingMoscowTest;
import tests.junit.booking.trip_oslo.BookingOsloTest;
import web_driver.Driver;

@RunWith(Suite.class)
@Suite.SuiteClasses({BookingParisTest.class, BookingMoscowTest.class, BookingOsloTest.class})
public class BookingTripsRunner {
    @AfterClass
    public static void postCondition() {
        Driver.destroy();
    }
}