package tests.booking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import properties.Path;
import settings.Config;
import steps.BaseSteps;
import steps.MailSteps;
import steps.trashMail.TrashMailNewUser;
import web_driver.Driver;
import web_pages.booking.MainPage;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BookingNewUserTest {
    WebDriver driver;

    @Before
    public void preCondition() throws IOException, InterruptedException {
        driver = Driver.getWebDriver(Config.CHROME);
        TrashMailNewUser.trashMailGetTempMail(driver);
    }

    @Test
    public void createNewUserTest() throws InterruptedException, IOException {
        MainPage.bookingRegistration(driver, Path.BOOKING_PATH);
        String currentHandle = driver.getWindowHandle();
        if (!TrashMailNewUser.firstTime) {
            BaseSteps.findElementClick(driver, String.format("//*[contains(text(),'%s')]", "booking.com"));
        } else {
            MailSteps.confirmLinkOnYandexMail(driver, "booking.com");
        }
        BaseSteps.findElementClick(driver, "//*[contains(text(),'Подтверждаю')]");
        TimeUnit.SECONDS.sleep(8);
        Set<String> handles = driver.getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                driver.switchTo().window(currentHandle);
            }
        }
        MainPage.bookingLogIn(driver,BaseSteps.getProperties(Path.BOOKING_PATH));
        BaseSteps.findElementClick(driver, "//*[@id='profile-menu-trigger--content']");
        TimeUnit.SECONDS.sleep(3);
        BaseSteps.findElementClick(driver, "//*[contains(@class,'mydashboard')]");
        TimeUnit.SECONDS.sleep(3);
        assertEquals(0, driver.findElements(By.xpath("//*[@class='email-confirm-banner']")).size());
    }

    @After
    public void postCondition() {
        BaseSteps.destroy(driver);
    }
}