package web_pages.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import web_driver.Driver;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookingPage {
    protected Actions actions;
    public WebElement element;
    private String firstFavoriteHotel;
    private String lastFavoriteHotel;

    public String getFirstFavoriteHotel() {
        return firstFavoriteHotel;
    }

    public String getLastFavoriteHotel() {
        return lastFavoriteHotel;
    }

    private static final String SELECT_DATE_XPATH = "//*[contains(@data-date,'%s')]";
    private static final String ADULTS_AMOUNT_XPATH = "//*[contains(@aria-describedby,'adult')][contains(@class,'add')]";
    private static final String ROOMS_AMOUNT_XPATH = "//*[contains(@aria-describedby,'no_rooms_desc')][contains(@class,'add')]";
    private static final String CHILDREN_AMOUNT_XPATH = "//*[contains(@aria-describedby,'group_children_desc')][contains(@class,'add')]";
    private static final String ACTIONS_ADULTS_XPATH = "//*[@id='group_adults']";
    private static final String ACTIONS_ROOMS_XPATH = "//*[@id='no_rooms']";
    private static final String SUBMIT_ON_SECOND_PAGE_XPATH = "//*[@data-sb-id='main'][contains(@type,'submit')]";
    private static final String MIN_INTERVAL_OF_BUDGET_XPATH = "//*[@id='filter_price']//a[1]";
    private static final String TENTH_HOTEL_XPATH = "//*[@data-hotelid][10]";
    private static final String TENTH_HOTEL_TITLE_XPATH = "%s//span[contains(@class,'sr-hotel__name')]";
    private static final String FIRST_ID_XPATH = "//*[@id='hotellist_inner']/div[1]/div[1]/div/button";
    private static final String FIRST_FILL_XPATH = "//*[@id='hotellist_inner']/div[1]/div[1]/div/button/*[1]";
    private static final String LAST_HEART_XPATH = "//*[@id='hotellist_inner']/div[%s]/div[1]/div/button";
    private static final String LAST_FILL_XPATH = "//*[@id='hotellist_inner']/div[%s]/div[1]/div/button/*[1]";
    private static final String AMOUNT_FOR_LIST_XPATH = "//*[@id='hotellist_inner']/div";
    private static final String FIRST_FAVOURITE_ID_XPATH = "//*[contains(@data-index,'0')]/div";
    private static final String SECOND_FAVOURITE_ID_XPATH = "//*[contains(@data-index,'1')]/div";

    private static final String HOTEL_ID_ATTRIBUTE = "data-hotel-id";
    private static final String HOTEL_FILL_ATTRIBUTE = "fill";
    private static final String WISH_LIST_ID_ATTRIBUTE = "data-id";
    private static final String RED = "rgb(204, 0, 0)";

    private static final String LOGO_XPATH = "//*[@class='header-wrapper']/img";
    private static final String CURRENCY_XPATH = "//*[@data-id='currency_selector']";
    private static final String LANGUAGE_XPATH = "//*[@data-id='language_selector']";
    private static final String NOTIFICATIONS_XPATH = "//*[@data-id='notifications']";
    private static final String HELP_CENTER_XPATH = "//*[contains(@class,'uc_helpcenter')]";
    private static final String LIST_PROPERTY_XPATH = "//*[contains(@class,'uc_account logged')]";
    private static final String CURRENT_ACCOUNT_XPATH = "//*[contains(@id,'current_account')]";
    private static final String ACCOMMODATION_XPATH = "//*[contains(@data-ga-track,'accommodation')]";
    private static final String FLIGHTS_XPATH = "//*[contains(@data-ga-track,'flights')]";
    private static final String CARS_XPATH = "//*[contains(@data-ga-track,'cars')]";
    private static final String ATTRACTIONS_XPATH = "//*[contains(@data-ga-track,'attractions')]";
    private static final String AIRPORT_TAXIS_XPATH = "//*[contains(@data-ga-track,'airport_taxis')]";

    private static final Logger LOGGER = LogManager.getLogger(BookingPage.class);

    @FindBy(xpath = "//*[@id='ss']")
    private static WebElement cityField;
    @FindBy(xpath = "//*[contains(@class,'xp__input-group xp__date-time')]")
    private static WebElement dataBox;
    @FindBy(xpath = "//*[@id='xp__guests__toggle']")
    private static WebElement additionalProperties;
    @FindBy(xpath = "//*[contains(@class,'field-adult')]//input")
    private static WebElement adultsValue;
    @FindBy(xpath = "//*[contains(@class,'field-rooms')]//input")
    private static WebElement roomsValue;
    @FindBy(xpath = "//*[@id='group_children']")
    private static WebElement childrenValue;
    @FindBy(xpath = "//*[contains(@type,'submit')]")
    private static WebElement submitSearch;
    @FindBy(xpath = "//*[contains(@class,'sort_price')]/a")
    private static WebElement sortByAscending;
    @FindBy(xpath = "//*[@id='filter_price']//a[5]")
    private static WebElement maxIntervalOfBudget;
    @FindBy(xpath = "//*[contains(@class,'bui-price-display')]/div[2]/div")
    private static WebElement firstHotelInList;
    @FindBy(xpath = "//*[@id='filter_price']//a[1]")
    private static WebElement minIntervalOfBudget;
    @FindBy(xpath = "//*[@data-id='class-3']")
    private static WebElement threeStarHotels;
    @FindBy(xpath = "//*[@data-id='class-4']")
    private static WebElement fourStarHotels;
    @FindBy(xpath = "//*[@id='hotellist_inner']/div[1]/div[1]/div/button")
    private static WebElement firstHeart;
    @FindBy(xpath = "//*[contains(@id,'current_account')]")
    private static WebElement currentAccount;
    @FindBy(xpath = "//*[contains(@class,'mydashboard')]")
    private static WebElement myDashBoard;
    @FindBy(xpath = "//*[contains(@class,'list_item_desc')]")
    private static WebElement wishList;
    @FindBy(xpath = "//*[contains(@data-index,'0')]/div/a")
    private static WebElement deleteFistFavoriteHotel;
    @FindBy(xpath = "//*[contains(@data-index,'1')]/div/a")
    private static WebElement deleteSecondFavoriteHotel;
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

    public BookingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.actions = new Actions(driver);
    }

    public void setDataForSearch(String city, int daysAmount, int daysShift, int adultsNeed, int childrenNeed, int roomsNeed) throws InterruptedException {
        LOGGER.debug("Set city");
        cityField.sendKeys(Keys.chord(Keys.CONTROL, "a"), city);
        LOGGER.debug("Set period");
        dataBox.click();
        Driver.findElementClick(String.format(SELECT_DATE_XPATH, setDays(daysShift)));
        Driver.findElementClick(String.format(SELECT_DATE_XPATH, setDays(daysAmount + daysShift)));
        LOGGER.debug("Set additional properties");
        additionalProperties.click();
        LOGGER.debug("Set adults");
        int adultsAmount = Integer.parseInt(adultsValue.getAttribute("value"));
        Driver.findElementClickRepeat(ADULTS_AMOUNT_XPATH, adultsAmount, adultsNeed);
        LOGGER.debug("Set rooms");
        int roomsAmount = Integer.parseInt(roomsValue.getAttribute("value"));
        Driver.findElementClickRepeat(ROOMS_AMOUNT_XPATH, roomsAmount, roomsNeed);
        LOGGER.debug("Set children");
        int childrenAmount = Integer.parseInt(childrenValue.getAttribute("value"));
        Driver.findElementClickRepeat(CHILDREN_AMOUNT_XPATH, childrenAmount, childrenNeed);
        LOGGER.debug("Search with properties");
        submitSearch.click();
        TimeUnit.SECONDS.sleep(4);
    }

    public static String setDays(int daysAmount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, daysAmount);
        Date newDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(newDate);
    }

    public void sortForParis() throws InterruptedException {
        LOGGER.debug("Sort for Paris trip");
        sortByAscending.click();
        maxIntervalOfBudget.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void assertForParis(int daysAmount) {
        LOGGER.debug("Check hotels in Paris trip");
        String maxBudget = maxIntervalOfBudget.getText().replaceAll("\\D+", "");
        String firstPrice = firstHotelInList.getText().replaceAll("\\D+", "");
        int firstOneNightPrice = Integer.parseInt(firstPrice) / daysAmount;
        System.out.println("Budget: " + maxBudget + "+");
        System.out.println("One night price: " + firstOneNightPrice);
        assertTrue(firstOneNightPrice >= Integer.parseInt(maxBudget));
    }

    public void actionsForMoscow() throws InterruptedException {
        LOGGER.debug("Properties with actions in Moscow trip");
        element = Driver.findElementReturn(ACTIONS_ADULTS_XPATH);
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).click().perform();
        element = Driver.findElementReturn(ACTIONS_ROOMS_XPATH);
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).click().perform();
        actions.moveToElement(Driver.findElementReturn(SUBMIT_ON_SECOND_PAGE_XPATH)).click().perform();
        TimeUnit.SECONDS.sleep(2);
    }

    public void sortForMoscow() throws InterruptedException {
        LOGGER.debug("Sort for Moscow trip");
        minIntervalOfBudget.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void assertForMoscow(int daysAmount) throws InterruptedException {
        LOGGER.debug("Check hotels in Moscow trip");
        element = Driver.findElementReturn(MIN_INTERVAL_OF_BUDGET_XPATH);
        String minBudget = element.getText();
        minBudget = minBudget.substring(minBudget.indexOf("-")).replaceAll("\\D+", "");
        TimeUnit.SECONDS.sleep(2);
        String firstPrice = firstHotelInList.getText().replaceAll("\\D+", "");
        int firstOneNightPrice = Integer.parseInt(firstPrice) / (daysAmount);
        System.out.println("Budget: up to " + minBudget);
        System.out.println("One night price: " + firstOneNightPrice);
        assertTrue(firstOneNightPrice <= Integer.parseInt(minBudget));
    }

    public void sortForOslo() throws InterruptedException {
        LOGGER.debug("Sort for Oslo trip");
        threeStarHotels.click();
        fourStarHotels.click();
        TimeUnit.SECONDS.sleep(4);
    }

    public void actionsForOslo() throws InterruptedException {
        LOGGER.debug("Find hotel and check color for Oslo trip");
        element = Driver.findElementReturn(TENTH_HOTEL_XPATH);
        element = setBackgroundsAndCheckTitle(Driver.getWebDriver(), element);
        String titleColor = element.getAttribute("style");
        if (titleColor.equals("color: red;"))
            System.out.println("Red is really red");
        assertEquals("color: red;", titleColor);
    }

    public WebElement setBackgroundsAndCheckTitle(WebDriver driver, WebElement element) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
        LOGGER.debug("Finding 10th hotel on page");
        TimeUnit.SECONDS.sleep(3); //just to have time to see this action
        actions.moveToElement(driver.findElement(By.xpath(String.format(TENTH_HOTEL_TITLE_XPATH, TENTH_HOTEL_XPATH)))).perform();
        LOGGER.debug("Mouse on title of 10th hotel");
        TimeUnit.SECONDS.sleep(3); //just to have time to see this action
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor='green'", element);
        LOGGER.debug("Changing background's color to green");
        TimeUnit.SECONDS.sleep(3); //just to have time to see this action
        element = driver.findElement(By.xpath(String.format(TENTH_HOTEL_TITLE_XPATH, TENTH_HOTEL_XPATH)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.color='red'", element);
        LOGGER.debug("Changing title's color to red");
        TimeUnit.SECONDS.sleep(3); //just to have time to see this action
        return element;
    }

    public void checkHeader() {
        LOGGER.debug("Check all header elements");
        assertTrue(Driver.isDisplayed(LOGO_XPATH));
        assertTrue(Driver.isDisplayed(CURRENCY_XPATH));
        assertTrue(Driver.isDisplayed(LANGUAGE_XPATH));
        assertTrue(Driver.isDisplayed(NOTIFICATIONS_XPATH));
        assertTrue(Driver.isDisplayed(HELP_CENTER_XPATH));
        assertTrue(Driver.isDisplayed(LIST_PROPERTY_XPATH));
        assertTrue(Driver.isDisplayed(CURRENT_ACCOUNT_XPATH));
        assertTrue(Driver.isDisplayed(ACCOMMODATION_XPATH));
        assertTrue(Driver.isDisplayed(FLIGHTS_XPATH));
        assertTrue(Driver.isDisplayed(CARS_XPATH));
        assertTrue(Driver.isDisplayed(ATTRACTIONS_XPATH));
        assertTrue(Driver.isDisplayed(AIRPORT_TAXIS_XPATH));
    }

    public void signIn(Properties properties) throws InterruptedException {
        LOGGER.debug("Sign in to booking.com account");
        Driver.getWebDriver().get("https://www.booking.com/");
        signIn.click();
        TimeUnit.SECONDS.sleep(3);
        LOGGER.debug("Printing email");
        login.sendKeys(properties.getProperty("NEW_MAIL"));
        submit.click();
        TimeUnit.SECONDS.sleep(1);
        LOGGER.debug("Printing password");
        password.sendKeys(properties.getProperty("PASSWORD"));
        submit.click();
    }

    public void setFavoritesAndCheckColor() throws InterruptedException {
        LOGGER.debug("Click first heart and check color");
        firstHeart.click();
        element = Driver.findElementReturn(FIRST_ID_XPATH);
        firstFavoriteHotel = element.getAttribute(HOTEL_ID_ATTRIBUTE);
        element = Driver.findElementReturn(FIRST_FILL_XPATH);
        TimeUnit.SECONDS.sleep(3);
        assertEquals(RED, element.getCssValue(HOTEL_FILL_ATTRIBUTE));
        LOGGER.debug("Click last heart and check color");
        List<WebElement> list = Driver.getWebDriver().findElements(By.xpath(AMOUNT_FOR_LIST_XPATH));
        Driver.findElementClick(String.format(LAST_HEART_XPATH, (list.size() - 1)));
        TimeUnit.SECONDS.sleep(3);
        element = Driver.findElementReturn(String.format(LAST_HEART_XPATH, (list.size() - 1)));
        lastFavoriteHotel = element.getAttribute(HOTEL_ID_ATTRIBUTE);
        element = Driver.findElementReturn(String.format(LAST_FILL_XPATH, (list.size() - 1)));
        assertEquals(RED, element.getCssValue(HOTEL_FILL_ATTRIBUTE));
        System.out.println(firstFavoriteHotel + " " + lastFavoriteHotel);
    }

    public void compareHotelID(String firstFavoriteHotel, String lastFavoriteHotel) throws InterruptedException {
        LOGGER.debug("Check wish list");
        currentAccount.click();
        myDashBoard.click();
        TimeUnit.SECONDS.sleep(3);
        wishList.click();
        TimeUnit.SECONDS.sleep(5);
        element = Driver.findElementReturn(FIRST_FAVOURITE_ID_XPATH);
        assertEquals(firstFavoriteHotel, element.getAttribute(WISH_LIST_ID_ATTRIBUTE));
        element = Driver.findElementReturn(SECOND_FAVOURITE_ID_XPATH);
        assertEquals(lastFavoriteHotel, element.getAttribute(WISH_LIST_ID_ATTRIBUTE));
        deleteFistFavoriteHotel.click();
        deleteSecondFavoriteHotel.click();
    }

    public void registration(Properties properties, String BOOKING_PATH) throws InterruptedException, IOException {
        LOGGER.debug("Booking.com registration");
        properties = Driver.getProperties(BOOKING_PATH);
        createAccount.click();
        TimeUnit.SECONDS.sleep(1);
        LOGGER.debug("Printing email");
        enterLogin.sendKeys(properties.getProperty("NEW_MAIL"));
        start.click();
        TimeUnit.SECONDS.sleep(1);
        LOGGER.debug("Printing password twice");
        password.sendKeys(properties.getProperty("PASSWORD"));
        confirmPassword.sendKeys(properties.getProperty("PASSWORD"));
        submitSearch.click();
    }
}