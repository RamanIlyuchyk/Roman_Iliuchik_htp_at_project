package steps.cucumber.booking.add_favorites;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import settings.Config;
import web_driver.Driver;
import web_pages.booking.MainPage;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BookingAddFavorites {
    WebElement element;
    Properties properties;
    String firstHotel;
    String secondHotel;
    int daysAmount = 5;
    int daysShift = 30;
    int adultsNeed = 2;
    int childrenNeed = 0;
    int roomsNeed = 1;
    static String BOOKING_PATH = "src/test/resources/properties/booking.properties";
    private static final Logger LOGGER = LogManager.getLogger(BookingAddFavorites.class);

    @Before
    public void precondition() throws IOException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        properties = Driver.getProperties(BOOKING_PATH);
    }

    @Given("I go to booking.com")
    public void iGoToBookingCom() {
    }

    @Then("I log in")
    public void iLogIn() throws InterruptedException {
        MainPage.bookingLogIn(properties);
        TimeUnit.SECONDS.sleep(3);
    }

    @Then("I enter data to search")
    public void iEnterDataToSearch() throws InterruptedException {
        MainPage.setCityPersonRoomDates("Madrid", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        TimeUnit.SECONDS.sleep(7);
    }

    @Then("I click heart button on the first hotel")
    public void iClickHeartButtonOnTheFirstHotel() throws InterruptedException {
        Driver.findElementClick("//*[@id='hotellist_inner']/div[1]/div[1]/div/button");
        element = Driver.findElementReturn("//*[@id='hotellist_inner']/div[1]/div[1]/div/button");
        firstHotel = element.getAttribute("data-hotel-id");
        element = Driver.findElementReturn("//*[@id='hotellist_inner']/div[1]/div[1]/div/button/*[1]");
        TimeUnit.SECONDS.sleep(3);
    }

    @Then("I check heart button color")
    public void iCheckHeartButtonColor() {
        assertEquals("rgb(204, 0, 0)", element.getCssValue("fill"));
    }

    @Then("I go to last page")
    public void iGoToLastPage() throws InterruptedException {
        Driver.findElementClick("//*[contains(@class,'bui-pagination__item')][10]");
        TimeUnit.SECONDS.sleep(6);
    }

    @Then("I click heart button on the last hotel")
    public void iClickHeartButtonOnTheLastHotel() throws InterruptedException {
        List<WebElement> list = Driver.getWebDriver().findElements(By.xpath("//*[@id='hotellist_inner']/div"));
        Driver.findElementClick(String.format("//*[@id='hotellist_inner']/div[%s]/div[1]/div/button", (list.size() - 1)));
        TimeUnit.SECONDS.sleep(5);
        element = Driver.findElementReturn(String.format("//*[@id='hotellist_inner']/div[%s]/div[1]/div/button", (list.size() - 1)));
        secondHotel = element.getAttribute("data-hotel-id");
        element = Driver.findElementReturn(String.format("//*[@id='hotellist_inner']/div[%s]/div[1]/div/button/*[1]", (list.size() - 1)));
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I go to user page")
    public void iGoToUserPage() throws InterruptedException {
        Driver.findElementClick("//*[@id='profile-menu-trigger--content']");
        Driver.findElementClick("//*[contains(@class,'mydashboard')]");
        TimeUnit.SECONDS.sleep(3);
    }

    @Then("I check hotels id")
    public void iCheckHotelsId() throws InterruptedException {
        Driver.findElementClick("//*[contains(@class,'list_item_desc')]");
        TimeUnit.SECONDS.sleep(5);
        element = Driver.findElementReturn("//*[contains(@data-index,'0')]/div");
        assertEquals(firstHotel, element.getAttribute("data-id"));
        element = Driver.findElementReturn("//*[contains(@data-index,'1')]/div");
        assertEquals(secondHotel, element.getAttribute("data-id"));
    }

    @After
    public void postcondition() {
        LOGGER.info("Finish test");
        Driver.destroy();
    }
}