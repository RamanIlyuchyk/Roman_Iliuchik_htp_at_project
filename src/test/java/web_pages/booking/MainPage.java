package web_pages.booking;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import steps.BaseSteps;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MainPage {
    @FindBy(xpath = "//*[@id='ss']")
    private static WebElement cityTextBox;
    @FindBy(xpath = "//*[contains(@class,'xp__input-group xp__date-time')]")
    private static WebElement dataBox;
    @FindBy(xpath = "//*[@id='xp__guests__toggle']")
    private static WebElement personsRoomsBox;
    @FindBy(xpath = "//*[contains(@class,'field-adult')]//input")
    private static WebElement adultInput;
    @FindBy(xpath = "//*[contains(@aria-describedby,'adult')][contains(@class,'add')]")
    private static WebElement incAdult;
    @FindBy(xpath = "//*[contains(@class,'field-rooms')]//input")
    private static WebElement roomsInput;
    @FindBy(xpath = "//*[contains(@aria-describedby,'no_rooms_desc')][contains(@class,'add')]")
    private static WebElement incRoom;
    @FindBy(xpath = "//*[@id='group_children']//input")
    private static WebElement childInput;
    @FindBy(xpath = "//*[contains(@aria-describedby,'group_children_desc')][contains(@class,'add')]")
    private static WebElement incChild;
    @FindBy(xpath = "//*[contains(@type,'submit')]")
    private static WebElement submitSearch;
    @FindBy(xpath = "//*[@id='current_account']")
    private static WebElement signIn;
    @FindBy(xpath = "//*[@id='username']")
    private static WebElement login;
    @FindBy(xpath = "//*[@type='submit']")
    private static WebElement submit;
    @FindBy(xpath = "//*[@id='password']")
    private static WebElement password;
    @FindBy(xpath = "//*[@id='current_account_create']")
    private static WebElement createAccount;
    @FindBy(xpath = "//*[@id='login_name_register']")
    private static WebElement enterLogin;
    @FindBy(xpath = "//*[contains(@class,'nw-register')]/button")
    private static WebElement start;
    @FindBy(xpath = "//*[@id='password']")
    private static WebElement enterPassword;
    @FindBy(xpath = "//*[@id='confirmed_password']")
    private static WebElement confirmPassword;

    //public static void setCityPersonRoomDates(WebDriver driver, String city, int daysAmount, int daysShift, int adultsNeed, int childrenNeed, int roomsNeed) throws InterruptedException {
    //    cityTextBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), city);
    //    dataBox.click();
    //    BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date,'%s')]", setDays(daysShift)));
    //    BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date,'%s')]", setDays(daysAmount + daysShift)));
    //    personsRoomsBox.click();
    //    int adultsAmount = Integer.parseInt(adultInput.getAttribute("value"));
    //    BaseSteps.findElementClickRepeat(driver, "//*[contains(@aria-describedby,'adult')][contains(@class,'add')]", adultsAmount, adultsNeed);
    //    int roomAmount = Integer.parseInt(roomsInput.getAttribute("value"));
    //    BaseSteps.findElementClickRepeat(driver, "//*[contains(@aria-describedby,'no_rooms_desc')][contains(@class,'add')]", roomAmount, roomsNeed);
    //    int childrenAmount = Integer.parseInt(childInput.getAttribute("value"));
    //    BaseSteps.findElementClickRepeat(driver, "//*[contains(@aria-describedby,'group_children_desc')][contains(@class,'add')]", childrenAmount, childrenNeed);
    //    submitSearch.click();
    //    TimeUnit.SECONDS.sleep(3);
    //}

    public static void setCityPersonRoomDates(WebDriver driver, String city, int daysAmount, int daysShift, int adultsNeed, int childrenNeed, int roomNeed) {
        WebElement element = driver.findElement(By.xpath("//*[@id='ss']"));
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), city);
        BaseSteps.findElementClick(driver, "//*[@data-mode='checkin']");
        BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date,'%s')]", setDays(daysShift)));
        BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date,'%s')]", setDays(daysAmount + daysShift)));
        BaseSteps.findElementClick(driver, "//*[@id='xp__guests__toggle']");
        int adultAmount = Integer.parseInt(BaseSteps.findElementGetAttribute(driver, "//*[contains(@class,'field-adult')]//input", "value"));
        BaseSteps.findElementClickRepeat(driver, "//*[contains(@aria-describedby,'adult')][contains(@class,'add')]", adultAmount, adultsNeed);
        int roomAmount = Integer.parseInt(BaseSteps.findElementGetAttribute(driver, "//*[contains(@class,'field-rooms')]//input", "value"));
        BaseSteps.findElementClickRepeat(driver, "//*[contains(@aria-describedby,'no_rooms_desc')][contains(@class,'add')]", roomAmount, roomNeed);
        int childAmount = Integer.parseInt(BaseSteps.findElementGetAttribute(driver, "//*[@id='group_children']", "value"));
        BaseSteps.findElementClickRepeat(driver, "//*[@aria-describedby='group_children_desc'][2]", childAmount, childrenNeed);
        BaseSteps.findElementClick(driver, "(//*[contains(@type,'submit')])[1]");
    }

    public static void setCityDates(WebDriver driver, String city, int daysAmount, int daysShift) throws InterruptedException {
        cityTextBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), city);
        dataBox.click();
        BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date,'%s')]", setDays(daysShift)));
        BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date,'%s')]", setDays(daysAmount + daysShift)));
        submitSearch.click();
        TimeUnit.SECONDS.sleep(3);
    }

    //public static void bookingLogIn(WebDriver driver, Properties properties) throws InterruptedException {
    //    driver.get("https://www.booking.com/");
    //    signIn.click();
    //    TimeUnit.SECONDS.sleep(3);
    //    login.sendKeys(properties.getProperty("NEW_MAIL"));
    //    submit.click();
    //    TimeUnit.SECONDS.sleep(1);
    //    password.sendKeys(properties.getProperty("PASSWORD"));
    //    submit.click();
    //}

    public static void bookingLogIn(WebDriver driver, Properties properties) throws InterruptedException {
        driver.get("https://www.booking.com/");
        BaseSteps.findElementClick(driver, "//*[@id='current_account']");
        TimeUnit.SECONDS.sleep(3);
        BaseSteps.findElementSendKeys(driver, "//*[@id='username']", properties.getProperty("NEW_MAIL"));
        BaseSteps.findElementClick(driver, "//*[@type='submit']");
        TimeUnit.SECONDS.sleep(1);
        BaseSteps.findElementSendKeys(driver, "//*[@id='password']", properties.getProperty("PASSWORD"));
        BaseSteps.findElementClick(driver, "//*[@type='submit']");
        TimeUnit.SECONDS.sleep(3);
    }

    //public static void bookingRegistration(WebDriver driver, String BOOKING_PATH) throws IOException, InterruptedException {
    //    driver.get("https://www.booking.com/");
    //    Properties properties = BaseSteps.getProperties(BOOKING_PATH);
    //    createAccount.click();
    //    TimeUnit.SECONDS.sleep(1);
    //    enterLogin.sendKeys(properties.getProperty("NEW_MAIL"));
    //    start.click();
    //    TimeUnit.SECONDS.sleep(1);
    //    password.sendKeys(properties.getProperty("PASSWORD"));
    //    confirmPassword.sendKeys(properties.getProperty("PASSWORD"));
    //    submitSearch.click();
    //}

    public static void bookingRegistration(WebDriver driver, String BOOKING_PATH) throws IOException, InterruptedException {
        driver.get("https://www.booking.com/");
        Properties properties = BaseSteps.getProperties(BOOKING_PATH);
        BaseSteps.findElementClick(driver, "//*[@id='current_account_create']");
        TimeUnit.SECONDS.sleep(1);
        BaseSteps.findElementSendKeys(driver, "//*[@id='login_name_register']", properties.getProperty("NEW_MAIL"));
        BaseSteps.findElementClick(driver, "//*[contains(@class,'nw-register')]/button");
        TimeUnit.SECONDS.sleep(1);
        BaseSteps.findElementSendKeys(driver, "//*[@id='password']", properties.getProperty("PASSWORD"));
        BaseSteps.findElementSendKeys(driver, "//*[@id='confirmed_password']", properties.getProperty("PASSWORD"));
        BaseSteps.findElementClick(driver, "//*[contains(@type,'submit')]");
    }

    public static String setDays(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}