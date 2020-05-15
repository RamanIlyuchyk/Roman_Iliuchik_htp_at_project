package steps.booking;

import org.openqa.selenium.*;
import steps.BaseSteps;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SpecialSteps {
    static WebElement element;

    public static String setDays(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static void findElementScrollIntoView(WebDriver driver, String xPath) {
        element = driver.findElement(By.xpath(xPath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public static void findElementSetAttribute(WebDriver driver, String xPath, String attribute, String value) {
        element = driver.findElement(By.xpath(xPath));
        ((JavascriptExecutor) driver).executeScript(String.format("arguments[0].style.%s='%s'", attribute, value), element);
    }

    public static void bookingLogIn(WebDriver driver, Properties properties) throws InterruptedException {
        driver.get("https://www.booking.com/");
        BaseSteps.findElementClick(driver, "//*[@id='current_account']");
        TimeUnit.SECONDS.sleep(3);
        BaseSteps.findElementSendKeys(driver, "//*[@id='username']", properties.getProperty("NEW_MAIL"));
        BaseSteps.findElementClick(driver, "//*[@type='submit']");
        TimeUnit.SECONDS.sleep(1);
        BaseSteps.findElementSendKeys(driver, "//*[@id='password']", properties.getProperty("PASSWORD"));
        BaseSteps.findElementClick(driver, "//*[@type='submit']");
    }

    public static void setCityPersonRoomDates(WebDriver driver, String city, int daysAmount, int daysShift, int adultsNeed, int childrenNeed, int roomNeed) {
        WebElement element = driver.findElement(By.xpath("//*[@id='ss']"));
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), city);
        BaseSteps.findElementClick(driver, "//*[@data-mode='checkin']");
        BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date,'%s')]", SpecialSteps.setDays(daysShift)));
        BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date,'%s')]", SpecialSteps.setDays(daysAmount + daysShift)));
        BaseSteps.findElementClick(driver, "//*[@id='xp__guests__toggle']");
        int adultAmount = Integer.parseInt(BaseSteps.findElementGetAttribute(driver, "//*[contains(@class,'field-adult')]//input", "value"));
        BaseSteps.findElementClickRepeat(driver, "//*[contains(@aria-describedby,'adult')][contains(@class,'add')]", adultAmount, adultsNeed);
        int roomAmount = Integer.parseInt(BaseSteps.findElementGetAttribute(driver, "//*[contains(@class,'field-rooms')]//input", "value"));
        BaseSteps.findElementClickRepeat(driver, "//*[contains(@aria-describedby,'no_rooms_desc')][contains(@class,'add')]", roomAmount, roomNeed);
        int childAmount = Integer.parseInt(BaseSteps.findElementGetAttribute(driver, "//*[@id='group_children']", "value"));
        BaseSteps.findElementClickRepeat(driver, "//*[@aria-describedby='group_children_desc'][2]", childAmount, childrenNeed);
        BaseSteps.findElementClick(driver, "(//*[contains(@type,'submit')])[1]");
    }
}