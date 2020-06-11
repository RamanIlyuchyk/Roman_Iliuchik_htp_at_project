package steps.cucumber.booking.add_favorites;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import settings.Config;
import web_driver.Driver;
import web_pages.booking.BookingPage;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BookingAddFavorites {
    Properties properties;
    int daysAmount = 5;
    int daysShift = 30;
    int adultsNeed = 2;
    int childrenNeed = 0;
    int roomsNeed = 1;
    static BookingPage bookingPage;
    static String BOOKING_PATH = "src/test/resources/properties/booking.properties";
    private static final Logger LOGGER = LogManager.getLogger(BookingAddFavorites.class);

    @Before
    public void preCondition() throws IOException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        properties = Driver.getProperties(BOOKING_PATH);
        bookingPage = new BookingPage(Driver.getWebDriver());
    }

    @Given("I go to booking.com")
    public void iGoToBookingCom() {
    }

    @Then("I sing in")
    public void iSignIn() throws InterruptedException {
        bookingPage.signIn(properties);
        TimeUnit.SECONDS.sleep(3);
    }

    @And("I set properties for search")
    public void iSetPropertiesForSearch() throws InterruptedException {
        bookingPage.setDataForSearch("Madrid", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        TimeUnit.SECONDS.sleep(5);
    }

    @And("I add two hotels to wish list and check color")
    public void iAddTwoHotelsToWishListAndCheckColor() throws InterruptedException {
        bookingPage.setFavoritesAndCheckColor();
    }

    @And("I check wish list")
    public void iCheckWishList() throws InterruptedException {
        bookingPage.compareHotelID(bookingPage.getFirstFavoriteHotel(), bookingPage.getLastFavoriteHotel());
    }

    @After
    public void postCondition() {
        LOGGER.info("Finish test");
        Driver.destroy();
    }
}