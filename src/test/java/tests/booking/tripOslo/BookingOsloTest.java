package tests.booking.tripOslo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import settings.Config;
import settings.ScreenMode;
import steps.BaseSteps;
import web_driver.Driver;
import web_pages.booking.HotelsPage;
import web_pages.booking.MainPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BookingOsloTest {
    int daysAmount = 1;
    int daysShift = 1;
    int adultsNeed = 2;
    int roomsNeed = 1;
    int childrenNeed = 1;
    WebElement element;
    static WebDriver driver;

    @BeforeClass
    public static void preCondition() {
        driver = Driver.getWebDriver(Config.CHROME);
    }

    @Given("I go to booking.com")
    public void iGoToBookingCom() {
        driver = Driver.getWebDriver(Config.CHROME);
        BaseSteps.followTheLinkSetWindowMode(driver, "https://www.booking.com/", ScreenMode.MAXIMIZE);
    }

    @Then("I enter data to search")
    public void iEnterDataToSearch() throws InterruptedException {
        MainPage.setCityPersonRoomDates(driver, "Oslo", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        TimeUnit.SECONDS.sleep(4);
    }

    @Then("I find hotels with 3 and 4 stars")
    public void iFindHotelsWithStars() throws InterruptedException {
        BaseSteps.findElementClick(driver, "//*[@data-id='class-3']");
        BaseSteps.findElementClick(driver, "//*[@data-id='class-4']");
        TimeUnit.SECONDS.sleep(4);
    }

    @Then("I find hotel №{int} in list")
    public void iFindHotelInList(Integer int1) throws InterruptedException {
        element = driver.findElement(By.xpath("//*[@id='hotellist_inner']/div[11]"));
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I'm changing background and text color")
    public void iMChangingBackgroundAndTextColor() throws InterruptedException {
        Actions actions = new Actions(driver);
        element = HotelsPage.executorSetBackgroundTitleColor(element, driver, actions);
    }

    @Then("I check that the text color is red")
    public void iCheckThatTheTextColorIsRed() {
        String textColor = element.getAttribute("style");
        if (textColor.equals("color: red;"))
            System.out.println("Red is Red");
        assertEquals("color: red;", textColor);
        BaseSteps.destroy(driver);
    }

    @Test
    public void tripOsloTest() throws InterruptedException {
        BaseSteps.followTheLinkSetWindowMode(driver, "https://www.booking.com/", ScreenMode.MAXIMIZE);
        MainPage.setCityPersonRoomDates(driver, "Oslo", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        TimeUnit.SECONDS.sleep(4);
        BaseSteps.findElementClick(driver, "//*[@data-id='class-3']");
        BaseSteps.findElementClick(driver, "//*[@data-id='class-4']");
        TimeUnit.SECONDS.sleep(4);
        element = driver.findElement(By.xpath("//*[@id='hotellist_inner']/div[11]"));
        TimeUnit.SECONDS.sleep(2);
        Actions actions = new Actions(driver);
        element = HotelsPage.executorSetBackgroundTitleColor(element, driver, actions);
        String textColor = element.getAttribute("style");
        if (textColor.equals("color: red;"))
            System.out.println("Red is Red");
        assertEquals("color: red;", textColor);
    }

    @AfterClass
    public static void postCondition() {
        BaseSteps.destroy(driver);
    }
}