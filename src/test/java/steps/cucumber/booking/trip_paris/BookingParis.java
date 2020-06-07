package steps.cucumber.booking.trip_paris;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import settings.Config;
import settings.ScreenMode;
import web_driver.Driver;
import web_pages.booking.MainPage;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class BookingParis {
    int daysAmount = 7;
    int daysShift = 3;
    int adultsNeed = 4;
    int childrenNeed = 0;
    int roomsNeed = 2;
    String maxPrice;
    int firstOneDayPrice;
    private static final Logger LOGGER = LogManager.getLogger(BookingParis.class);

    @Given("I go to booking.com")
    public void iGoToBookingCom() throws MalformedURLException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        Driver.followTheLinkSetWindowMode("https://www.booking.com/", ScreenMode.MAXIMIZE);
    }

    @Then("I enter data to search")
    public void iEnterDataToSearch() throws InterruptedException {
        MainPage.setCityPersonRoomDates("Paris", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        TimeUnit.SECONDS.sleep(4);
    }

    @Then("I filter hotels at the maximum price")
    public void iFilterHotelsAtTheMaximumPrice() throws InterruptedException {
        Driver.findElementClick("//*[contains(@class,'sort_price')]/a");
        Driver.findElementClick("//*[@id='filter_price']//a[5]");
        TimeUnit.SECONDS.sleep(2);
    }

    @And("I'm looking hotel with minimum price")
    public void iMLookingHotelWithMinimumPrice() {
        maxPrice = Driver.findElementGetText("//*[@id='filter_price']//a[5]").replaceAll("\\D+", "");
        String firstPrice = Driver.findElementGetText("//*[contains(@class,'bui-price-display')]/div[2]/div").replaceAll("\\D+", "");
        firstOneDayPrice = Integer.parseInt(firstPrice) / daysAmount;
    }

    @And("I compare hotel's price and price in filters")
    public void iCompareHotelSPriceAndPriceInFilters() {
        System.out.println("Price: " + maxPrice + "+; Min one Night Price: " + firstOneDayPrice);
        assertTrue(firstOneDayPrice >= Integer.parseInt(maxPrice));
        LOGGER.info("Finish test");
        Driver.destroy();
    }
}