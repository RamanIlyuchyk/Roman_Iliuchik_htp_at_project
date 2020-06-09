package web_pages.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import web_driver.Driver;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MainPage {
    private static final Logger LOGGER = LogManager.getLogger(MainPage.class);

    public static void setCityPersonRoomDates(String city, int daysAmount, int daysShift, int adultsNeed, int childrenNeed, int roomsNeed) {
        LOGGER.debug("Adding search parameters: " + city + ", " + "on " + daysAmount + " days after " + daysShift
                + " days for " + adultsNeed + " adults, " + childrenNeed + " children in " + roomsNeed + " rooms");
        WebElement element = Driver.getWebDriver().findElement(By.xpath("//*[@id='ss']"));
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), city);
        Driver.findElementClick("//*[contains(@class,'xp__input-group xp__date-time')]");
        Driver.findElementClick(String.format("//*[contains(@data-date,'%s')]", setDays(daysShift)));
        Driver.findElementClick(String.format("//*[contains(@data-date,'%s')]", setDays(daysAmount + daysShift)));
        Driver.findElementClick("//*[@id='xp__guests__toggle']");
        int adultAmount = Integer.parseInt(Driver.findElementGetAttribute("//*[contains(@class,'field-adult')]//input", "value"));
        Driver.findElementClickRepeat("//*[contains(@aria-describedby,'adult')][contains(@class,'add')]", adultAmount, adultsNeed);
        int roomAmount = Integer.parseInt(Driver.findElementGetAttribute("//*[contains(@class,'field-rooms')]//input", "value"));
        Driver.findElementClickRepeat("//*[contains(@aria-describedby,'no_rooms_desc')][contains(@class,'add')]", roomAmount, roomsNeed);
        int childAmount = Integer.parseInt(Driver.findElementGetAttribute("//*[@id='group_children']", "value"));
        Driver.findElementClickRepeat("//*[contains(@aria-describedby,'group_children_desc')][contains(@class,'add')]", childAmount, childrenNeed);
        Driver.findElementClick("//*[contains(@type,'submit')]");
    }

    public static WebElement executorSetBackgroundTitleColor(WebElement element, WebDriver driver, Actions actions) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
        TimeUnit.SECONDS.sleep(3);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id='hotellist_inner']/div[11]/div[2]/div/div/div[2]/a"))).build().perform();
        LOGGER.debug("Finding 10th hotel on page");
        TimeUnit.SECONDS.sleep(3);
        element = driver.findElement(By.xpath("//*[@id='hotellist_inner']/div[11]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor='green'", element);
        LOGGER.debug("Changing background color to green");
        TimeUnit.SECONDS.sleep(3);
        element = driver.findElement(By.xpath("//*[@id='hotellist_inner']/div[11]/div[2]/div/div/div/h3/a"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.color='red'", element);
        LOGGER.debug("Changing text color to red");
        TimeUnit.SECONDS.sleep(3);
        return element;
    }

    public static void bookingLogIn(Properties properties) throws InterruptedException {
        LOGGER.debug("Log in on booking.com");
        Driver.getWebDriver().get("https://www.booking.com/");
        Driver.findElementClick("//*[@id='current_account']");
        TimeUnit.SECONDS.sleep(3);
        Driver.findElementSendKeys("//*[@id='username']", properties.getProperty("NEW_MAIL"));
        LOGGER.debug("Printing email");
        Driver.findElementClick("//*[@type='submit']");
        TimeUnit.SECONDS.sleep(1);
        Driver.findElementSendKeys("//*[@id='password']", properties.getProperty("PASSWORD"));
        LOGGER.debug("Printing password");
        Driver.findElementClick("//*[@type='submit']");
    }

    public static void bookingRegistration(Properties properties, String BOOKING_PATH) throws InterruptedException, IOException {
        LOGGER.debug("Booking.com registration");
        properties = Driver.getProperties(BOOKING_PATH);
        Driver.findElementClick("//*[@id='current_account_create']");
        TimeUnit.SECONDS.sleep(1);
        Driver.findElementSendKeys("//*[@id='login_name_register']", properties.getProperty("NEW_MAIL"));
        LOGGER.debug("Printing email");
        Driver.findElementClick("//*[contains(@class,'nw-register')]/button");
        TimeUnit.SECONDS.sleep(1);
        Driver.findElementSendKeys("//*[@id='password']", properties.getProperty("PASSWORD"));
        Driver.findElementSendKeys("//*[@id='confirmed_password']", properties.getProperty("PASSWORD"));
        LOGGER.debug("Printing password twice");
        Driver.findElementClick("//*[contains(@type,'submit')]");
    }

    public static String setDays(int daysAmount) {
        LOGGER.debug("Calculating vocation days");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, daysAmount);
        Date newDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(newDate);
    }
}