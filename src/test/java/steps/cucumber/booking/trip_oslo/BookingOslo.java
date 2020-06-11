package steps.cucumber.booking.trip_oslo;

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

public class BookingOslo {
    BookingPage bookingPage;
    int daysAmount = 1;
    int daysShift = 1;
    int adultsNeed = 2;
    int roomsNeed = 1;
    int childrenNeed = 1;
    private static final Logger LOGGER = LogManager.getLogger(BookingOslo.class);

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
        bookingPage.setDataForSearch("Oslo", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
    }

    @And("I choose three star and four star hotels")
    public void iChooseThreeStarAndFourStarHotels() throws InterruptedException {
        bookingPage.sortForOslo();
    }

    @And("I set and check colors")
    public void iSetAndCheckColors() throws InterruptedException {
        bookingPage.actionsForOslo();
        Driver.destroy();
    }
}