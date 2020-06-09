package steps.cucumber.booking.trip_oslo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import settings.Config;
import settings.ScreenMode;
import web_driver.Driver;
import web_pages.booking.MainPage;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BookingOslo {
    int daysAmount = 1;
    int daysShift = 1;
    int adultsNeed = 2;
    int roomsNeed = 1;
    int childrenNeed = 1;
    WebElement element;
    private static final Logger LOGGER = LogManager.getLogger(BookingOslo.class);

    @Given("I go to booking.com")
    public void iGoToBookingCom() throws MalformedURLException {
        LOGGER.info("Start test");
        Driver.initDriver(Config.CHROME);
        Driver.followTheLinkSetWindowMode("https://www.booking.com/", ScreenMode.MAXIMIZE);
    }

    @Then("I enter data to search")
    public void iEnterDataToSearch() throws InterruptedException {
        MainPage.setCityPersonRoomDates("Oslo", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        TimeUnit.SECONDS.sleep(4);
    }

    @Then("I find hotels with 3 and 4 stars")
    public void iFindHotelsWithStars() throws InterruptedException {
        Driver.findElementClick("//*[@data-id='class-3']");
        Driver.findElementClick("//*[@data-id='class-4']");
        TimeUnit.SECONDS.sleep(4);
    }

    @Then("I find hotel №{int} in list")
    public void iFindHotelInList(Integer int1) throws InterruptedException {
        element = Driver.findElementReturn("//*[@id='hotellist_inner']/div[11]");
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I'm changing background and text color")
    public void iMChangingBackgroundAndTextColor() throws InterruptedException {
        Actions actions = new Actions(Driver.getWebDriver());
        element = MainPage.executorSetBackgroundTitleColor(element, Driver.getWebDriver(), actions);
    }

    @Then("I check that the text color is red")
    public void iCheckThatTheTextColorIsRed() {
        String textColor = element.getAttribute("style");
        if (textColor.equals("color: red;"))
            System.out.println("Red is Red");
        assertEquals("color: red;", textColor);
        LOGGER.info("Finish test");
        Driver.destroy();
    }
}