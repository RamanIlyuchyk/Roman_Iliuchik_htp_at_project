package steps.cucumber.booking.trip_moscow;

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

public class BookingMoscow {
    BookingPage bookingPage;
    int daysAmount = 5;
    int daysShift = 10;
    int adultsNeed = 2;
    int childrenNeed = 0;
    int roomsNeed = 1;
    private static final Logger LOGGER = LogManager.getLogger(BookingMoscow.class);

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
        bookingPage.setDataForSearch("Moscow", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
    }

    @Then("I perform actions with data")
    public void iPerformActionsWithData() throws InterruptedException {
        bookingPage.actionsForMoscow();
    }

    @And("I choose hotels from min budget")
    public void iChooseHotelsFromMinBudget() throws InterruptedException {
        bookingPage.sortForMoscow();
    }

    @And("I compare price of hotel and price in filters")
    public void iComparePriceOfHotelAndPriceInFilters() throws InterruptedException {
        bookingPage.assertForMoscow(daysAmount);
        LOGGER.info("Finish test");
        Driver.destroy();
    }
}