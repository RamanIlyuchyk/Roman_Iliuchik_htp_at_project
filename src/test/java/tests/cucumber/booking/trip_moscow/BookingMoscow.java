package tests.cucumber.booking.trip_moscow;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import settings.Config;
import settings.ScreenMode;
import steps.BaseSteps;
import steps.UsersApiSteps;
import web_driver.Driver;
import web_pages.booking.MainPage;

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
    static WebDriver driver;
    String maxPrice;
    private static final Logger LOGGER = LogManager.getLogger(UsersApiSteps.class);

    @Given("I go to booking.com")
    public void iGoToBookingCom() {
        LOGGER.info("Start test");
        driver = Driver.getWebDriver(Config.CHROME);
        BaseSteps.followTheLinkSetWindowMode(driver, "https://www.booking.com/", ScreenMode.MAXIMIZE);
    }

    @Then("I enter data to search")
    public void iEnterDataToSearch() throws InterruptedException {
        MainPage.setCityPersonRoomDates(driver, "Moscow", daysAmount, daysShift, adultsNeed, childrenNeed, roomsNeed);
        TimeUnit.SECONDS.sleep(3);
    }

    @Then("I enter alders amount by actions")
    public void iEnterAldersAmountByActions() throws InterruptedException {
        Actions actions = new Actions(driver);
        element = driver.findElement(By.xpath("//*[@id='group_adults']"));
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).click().perform();
        element = driver.findElement(By.xpath("//*[@id='no_rooms']"));
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).click().perform();
        actions.moveToElement(driver.findElement(By.xpath("//*[@data-sb-id='main'][contains(@type,'submit')]"))).click().perform();
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I filter hotels at the minimum price")
    public void iFilterHotelsAtTheMinimumPrice() throws InterruptedException {
        BaseSteps.findElementClick(driver, "//*[contains(@class,'sort_price')]/a");
        element = BaseSteps.findElementClickReturn(driver, "//*[@id='filter_price']//a[1]");
        maxPrice = element.getText();
        maxPrice = maxPrice.substring(maxPrice.indexOf("-")).replaceAll("\\D+", "");
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I'm looking hotel with minimum price")
    public void iMLookingHotelWithMinimumPrice() {
        String firstPrice = BaseSteps.findElementGetText(driver, "//*[contains(@class,'bui-price-display')]/div[2]/div");
        firstPrice = firstPrice.replaceAll("\\D+", "");
        firstOneDayPrice = Integer.parseInt(firstPrice) / (daysAmount);
    }

    @Then("I compare hotel's price and price in filters")
    public void iCompareHotelSPriceAndPriceInFilters() {
        System.out.println("Price: up to " + maxPrice + "; Min one Night Price: " + firstOneDayPrice);
        assertTrue(firstOneDayPrice <= Integer.parseInt(maxPrice));
        LOGGER.info("Finish test");
        BaseSteps.destroy(driver);
    }
}