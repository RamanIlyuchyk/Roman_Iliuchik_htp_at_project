package web_pages.booking;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import web_driver.Driver;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MainPageWithFindBy {
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

    protected Actions action;

    public MainPageWithFindBy(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.action = new Actions(driver);
    }

    public static void setCityPersonRoomDates(String city, int daysAmount, int daysShift, int adultsNeed, int childrenNeed, int roomsNeed) {
        cityTextBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), city);
        dataBox.click();
        Driver.findElementClick(String.format("//*[contains(@data-date,'%s')]", setDays(daysShift)));
        Driver.findElementClick(String.format("//*[contains(@data-date,'%s')]", setDays(daysAmount + daysShift)));
        personsRoomsBox.click();
        int adultAmount = Integer.parseInt(adultInput.getAttribute("value"));
        Driver.findElementClickRepeat("//*[contains(@aria-describedby,'adult')][contains(@class,'add')]", adultAmount, adultsNeed);
        int roomAmount = Integer.parseInt(roomsInput.getAttribute("value"));
        Driver.findElementClickRepeat("//*[contains(@aria-describedby,'no_rooms_desc')][contains(@class,'add')]", roomAmount, roomsNeed);
        int childAmount = Integer.parseInt(childInput.getAttribute("value"));
        Driver.findElementClickRepeat("//*[contains(@aria-describedby,'group_children_desc')][contains(@class,'add')]", childAmount, childrenNeed);
        submitSearch.click();
    }

    public static void bookingLogIn(WebDriver driver, Properties properties) throws InterruptedException {
        driver.get("https://www.booking.com/");
        signIn.click();
        TimeUnit.SECONDS.sleep(3);
        login.sendKeys(properties.getProperty("NEW_MAIL"));
        submit.click();
        TimeUnit.SECONDS.sleep(1);
        password.sendKeys(properties.getProperty("PASSWORD"));
        submit.click();
    }

    public static void bookingRegistration(Properties properties, String BOOKING_PATH) throws InterruptedException, IOException {
        properties = Driver.getProperties(BOOKING_PATH);
        createAccount.click();
        TimeUnit.SECONDS.sleep(1);
        enterLogin.sendKeys(properties.getProperty("NEW_MAIL"));
        start.click();
        TimeUnit.SECONDS.sleep(1);
        password.sendKeys(properties.getProperty("PASSWORD"));
        confirmPassword.sendKeys(properties.getProperty("PASSWORD"));
        submitSearch.click();
    }

    public static String setDays(int daysAmount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, daysAmount);
        Date newDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(newDate);
    }
}