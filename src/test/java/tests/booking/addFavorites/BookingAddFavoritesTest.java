package tests.booking.addFavorites;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import settings.Config;
import steps.BaseSteps;
import steps.UsersApiSteps;
import web_driver.Driver;
import web_pages.booking.MainPage;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BookingAddFavoritesTest {
    WebElement element;
    WebDriver driver;
    Properties properties;
    String firstHotel;
    String secondHotel;
    int daysAmount = 5;
    int daysShift = 25;
    int adultsNeed = 2;
    int childrenNeed = 0;
    int roomsNeed = 1;
    static String BOOKING_PATH = "src/test/resources/booking.properties";
    private static final Logger LOGGER = LogManager.getLogger(UsersApiSteps.class);

    @org.junit.Before
    public void preCondition() throws IOException {
        LOGGER.info("Start test");
        driver = Driver.getWebDriver(Config.CHROME);
        properties = BaseSteps.getProperties(BOOKING_PATH);
    }

    @Before
    public void precondition() throws IOException {
        LOGGER.info("Start test");
        driver = Driver.getWebDriver(Config.CHROME);
        properties = BaseSteps.getProperties(BOOKING_PATH);
    }

    @Given("I go to booking.com")
    public void iGoToBookingCom() {
    }

    @Then("I log in")
    public void iLogIn() throws InterruptedException {
        MainPage.bookingLogIn(driver, properties);
        TimeUnit.SECONDS.sleep(3);
    }

    @Then("I enter data to search")
    public void iEnterDataToSearch() throws InterruptedException {
        MainPage.setCityPersonRoomDates(driver, "Madrid", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        TimeUnit.SECONDS.sleep(7);
    }

    @Then("I click heart button on the first hotel")
    public void iClickHeartButtonOnTheFirstHotel() throws InterruptedException {
        BaseSteps.findElementClick(driver, "//*[@id='hotellist_inner']/div[1]/div[1]/div/button");
        element = BaseSteps.findElementReturn(driver, "//*[@id='hotellist_inner']/div[1]/div[1]/div/button");
        firstHotel = element.getAttribute("data-hotel-id");
        element = driver.findElement(By.xpath("//*[@id='hotellist_inner']/div[1]/div[1]/div/button/*[1]"));
        TimeUnit.SECONDS.sleep(3);
    }

    @Then("I check heart button color")
    public void iCheckHeartButtonColor() {
        assertEquals("rgb(204, 0, 0)", element.getCssValue("fill"));
    }

    @Then("I go to last page")
    public void iGoToLastPage() throws InterruptedException {
        BaseSteps.findElementClick(driver, "//*[contains(@class,'bui-pagination__item')][10]");
        TimeUnit.SECONDS.sleep(6);
    }

    @Then("I click heart button on the last hotel")
    public void iClickHeartButtonOnTheLastHotel() throws InterruptedException {
        List<WebElement> list = driver.findElements(By.xpath("//*[@id='hotellist_inner']/div"));
        BaseSteps.findElementClick(driver, String.format("//*[@id='hotellist_inner']/div[%s]/div[1]/div/button", (list.size() - 1)));
        TimeUnit.SECONDS.sleep(5);
        element = BaseSteps.findElementReturn(driver, String.format("//*[@id='hotellist_inner']/div[%s]/div[1]/div/button", (list.size() - 1)));
        secondHotel = element.getAttribute("data-hotel-id");
        element = driver.findElement(By.xpath(String.format("//*[@id='hotellist_inner']/div[%s]/div[1]/div/button/*[1]", (list.size() - 1))));
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I go to user page")
    public void iGoToUserPage() throws InterruptedException {
        BaseSteps.findElementClick(driver, "//*[@id='profile-menu-trigger--content']");
        BaseSteps.findElementClick(driver, "//*[contains(@class,'mydashboard')]");
        TimeUnit.SECONDS.sleep(3);
    }

    @Then("I check hotels id")
    public void iCheckHotelsId() throws InterruptedException {
        BaseSteps.findElementClick(driver, "//*[contains(@class,'list_item_desc')]");
        TimeUnit.SECONDS.sleep(5);
        element = driver.findElement(By.xpath("//*[contains(@data-index,'0')]/div"));
        assertEquals(firstHotel, element.getAttribute("data-id"));
        element = driver.findElement(By.xpath("//*[contains(@data-index,'1')]/div"));
        assertEquals(secondHotel, element.getAttribute("data-id"));
    }

    @Test
    public void addToFavoritesTest() throws InterruptedException {
        MainPage.bookingLogIn(driver, properties);
        TimeUnit.SECONDS.sleep(3);
        MainPage.setCityPersonRoomDates(driver, "Madrid", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        TimeUnit.SECONDS.sleep(7);
        setFavoritesCheckColor();
        compareHotelIndex(firstHotel, secondHotel);
    }

    public void setFavoritesCheckColor() throws InterruptedException {
        BaseSteps.findElementClick(driver, "//*[@id='hotellist_inner']/div[1]/div[1]/div/button");
        element = BaseSteps.findElementReturn(driver, "//*[@id='hotellist_inner']/div[1]/div[1]/div/button");
        firstHotel = element.getAttribute("data-hotel-id");
        element = driver.findElement(By.xpath("//*[@id='hotellist_inner']/div[1]/div[1]/div/button/*[1]"));
        TimeUnit.SECONDS.sleep(3);
        assertEquals("rgb(204, 0, 0)", element.getCssValue("fill"));
        BaseSteps.findElementClick(driver, "//*[contains(@class,'bui-pagination__item')][10]");
        TimeUnit.SECONDS.sleep(6);
        List<WebElement> list = driver.findElements(By.xpath("//*[@id='hotellist_inner']/div"));
        BaseSteps.findElementClick(driver, String.format("//*[@id='hotellist_inner']/div[%s]/div[1]/div/button", (list.size() - 1)));
        TimeUnit.SECONDS.sleep(5);
        element = BaseSteps.findElementReturn(driver, String.format("//*[@id='hotellist_inner']/div[%s]/div[1]/div/button", (list.size() - 1)));
        secondHotel = element.getAttribute("data-hotel-id");
        element = driver.findElement(By.xpath(String.format("//*[@id='hotellist_inner']/div[%s]/div[1]/div/button/*[1]", (list.size() - 1))));
        TimeUnit.SECONDS.sleep(2);
        assertEquals("rgb(204, 0, 0)", element.getCssValue("fill"));
        System.out.println(firstHotel + " " + secondHotel);
    }

    public void compareHotelIndex(String firstHotel, String secondHotel) throws InterruptedException {
        BaseSteps.findElementClick(driver, "//*[@id='profile-menu-trigger--content']");
        BaseSteps.findElementClick(driver, "//*[contains(@class,'mydashboard')]");
        TimeUnit.SECONDS.sleep(3);
        BaseSteps.findElementClick(driver, "//*[contains(@class,'list_item_desc')]");
        TimeUnit.SECONDS.sleep(5);
        element = driver.findElement(By.xpath("//*[contains(@data-index,'0')]/div"));
        assertEquals(firstHotel, element.getAttribute("data-id"));
        element = driver.findElement(By.xpath("//*[contains(@data-index,'1')]/div"));
        assertEquals(secondHotel, element.getAttribute("data-id"));
    }

    @After
    public void postcondition() {
        LOGGER.info("Finish test");
        BaseSteps.destroy(driver);
    }

    @org.junit.After
    public void postCondition() {
        LOGGER.info("Finish test");
        BaseSteps.destroy(driver);
    }
}