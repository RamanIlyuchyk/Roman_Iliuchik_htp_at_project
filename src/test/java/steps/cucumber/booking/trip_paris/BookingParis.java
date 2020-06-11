package steps.cucumber.booking.trip_paris;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import settings.Config;
import settings.ScreenMode;
import web_driver.Driver;
import web_pages.booking.BookingPage;

import java.net.MalformedURLException;

public class BookingParis {
    BookingPage bookingPage;
    int daysAmount = 7;
    int daysShift = 3;
    int adultsNeed = 4;
    int childrenNeed = 0;
    int roomsNeed = 2;
    private static final Logger LOGGER = LogManager.getLogger(BookingParis.class);

    @Given("I go to booking.com")
    public void iGoToBookingCom() throws MalformedURLException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        Driver.clearCookies();
        bookingPage = new BookingPage(Driver.getWebDriver());
        Driver.followTheLinkSetWindowMode("https://www.booking.com/", ScreenMode.MAXIMIZE);
    }

    @Then("I set data for search")
    public void iSetDataForSearch() throws InterruptedException {
        bookingPage.setDataForSearch("Paris", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
    }

    @And("I sort hotels by ascending with max budget")
    public void iSortHotelsByAscendingWithMaxBudget() throws InterruptedException {
        bookingPage.sortForParis();
    }

    @And("I compare price of hotel and price in filters")
    public void iComparePriceOfHotelAndPriceInFilters() {
        bookingPage.assertForParis(daysAmount);
        LOGGER.info("Finish test");
        Driver.destroy();
    }
}