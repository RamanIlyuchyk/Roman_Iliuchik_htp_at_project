package steps.cucumber.booking.trip_moscow;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import settings.Config;
import settings.ScreenMode;
import web_driver.Driver;
import web_pages.booking.MainPage;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class BookingMoscow {
    int daysAmount = 5;
    int daysShift = 10;
    int adultsNeed = 2;
    int childrenNeed = 0;
    int roomsNeed = 1;
    int firstOneDayPrice;
    WebElement element;
    String maxPrice;
    private static final Logger LOGGER = LogManager.getLogger(BookingMoscow.class);

    @Given("I go to booking.com")
    public void iGoToBookingCom() throws MalformedURLException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        Driver.followTheLinkSetWindowMode("https://www.booking.com/", ScreenMode.MAXIMIZE);
    }

    @Then("I enter data to search")
    public void iEnterDataToSearch() throws InterruptedException {
        MainPage.setCityPersonRoomDates("Moscow", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        TimeUnit.SECONDS.sleep(3);
    }

    @Then("I enter adults and rooms amount by actions")
    public void iEnterAdultsAndRoomsAmountByActions() throws InterruptedException {
        Actions actions = new Actions(Driver.getWebDriver());
        element = Driver.findElementReturn("//*[@id='group_adults']");
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).click().perform();
        element = Driver.findElementReturn("//*[@id='no_rooms']");
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).click().perform();
        actions.moveToElement(Driver.findElementReturn("//*[@data-sb-id='main'][contains(@type,'submit')]")).click().perform();
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I filter hotels at the minimum price")
    public void iFilterHotelsAtTheMinimumPrice() throws InterruptedException {
        Driver.findElementClick("//*[contains(@class,'sort_price')]/a");
        element = Driver.findElementClickReturn("//*[@id='filter_price']//a[1]");
        maxPrice = element.getText();
        maxPrice = maxPrice.substring(maxPrice.indexOf("-")).replaceAll("\\D+", "");
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I'm looking hotel with minimum price")
    public void iMLookingHotelWithMinimumPrice() {
        String firstPrice = Driver.findElementGetText("//*[contains(@class,'bui-price-display')]/div[2]/div");
        firstPrice = firstPrice.replaceAll("\\D+", "");
        firstOneDayPrice = Integer.parseInt(firstPrice) / (daysAmount);
    }

    @Then("I compare hotel's price and price in filters")
    public void iCompareHotelSPriceAndPriceInFilters() {
        System.out.println("Price: up to " + maxPrice + "; Min one Night Price: " + firstOneDayPrice);
        assertTrue(firstOneDayPrice <= Integer.parseInt(maxPrice));
        LOGGER.info("Finish test");
        Driver.destroy();
    }
}