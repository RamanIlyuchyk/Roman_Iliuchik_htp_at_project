package web_pages.silver_screen;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import web_driver.Driver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SilverScreenPage {
    @FindBy(xpath = "(//*[name()='svg' and @id='svg-icon-search'])[1]")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id='root']//descendant::input[@placeholder='Поиск']")
    private WebElement searchField;

    @FindBy(xpath = "//*[contains(text(),'привилегии')]")
    private WebElement logIn;

    @FindBy(xpath = "//*[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//*[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[contains(text(),'Войти')]")
    private WebElement enterButton;

    @FindBy(xpath = "(//*[@id='root']//descendant::span[contains(.,'уровень: ')])[1]")
    private WebElement level;

    @FindBy(xpath = "//*[@id='root']//descendant::span[contains(.,'Выйти')]")
    private WebElement logOut;

    @FindBy(xpath = "//*[@id='root']/div[2]/div/div[1]/div[1]/div[3]/span")
    private WebElement description;

    @FindBy(xpath = "//span[contains(text(),'Пользователь не найден')]")
    private WebElement bannerForUnregistered;

    protected Actions action;
    private static final String TITLES = "//*[@poster]/../div/a/span";
    private static final String TITLE_NAME = "(//*[@poster]/../div/a/span)[%s]";
    private static final String BANNER_FOR_BLANK_FIELD = "//div[contains(text(),'%s')]";

    public SilverScreenPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.action = new Actions(driver);
    }

    public void signIn(String email, String password) throws InterruptedException {
        action.moveToElement(logIn).perform();
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        enterButton.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void signOut() {
        logOut.click();
    }

    public void blankEmail(String password) throws InterruptedException {
        action.moveToElement(logIn).perform();
        passwordField.sendKeys(password);
        enterButton.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public void blankPassword(String email) throws InterruptedException {
        action.moveToElement(logIn).perform();
        emailField.sendKeys(email);
        enterButton.click();
        TimeUnit.SECONDS.sleep(2);
    }

    public boolean isLevelDisplayed() {
        return level.isDisplayed();
    }

    public boolean isBannerForUnregisteredDisplayed() {
        return bannerForUnregistered.isDisplayed();
    }

    public boolean isBannerAboutMistakeDisplayed(String field) {
        return Driver.getWebDriver().findElement(By.xpath(String.format(BANNER_FOR_BLANK_FIELD, field))).isDisplayed();
    }

    public void findMovie(String searchWord) {
        action.moveToElement(searchButton).perform();
        action.click(searchField).sendKeys(searchWord).build().perform();
    }

    public void seeListOfMovies() {
        searchField.sendKeys(Keys.ENTER);
    }

    public boolean checkSearchWord(String searchWord) {
        Matcher matcher;
        Pattern pattern = Pattern.compile(searchWord.toLowerCase());
        List<WebElement> titles = Driver.getWebDriver().findElements(By.xpath(TITLES));
        for (int i = 0; i < titles.size(); i++) {
            matcher = pattern.matcher(Driver.findElementGetText(String.format(TITLE_NAME, (i + 1))).toLowerCase());
            if (!matcher.find()) {
                Driver.findElementClick(String.format(TITLE_NAME, i + 1));
                matcher = pattern.matcher(description.getText().toLowerCase());
                if (!matcher.find()) {
                    return false;
                }
                Driver.getWebDriver().navigate().back();
            }
        }
        return true;
    }
}