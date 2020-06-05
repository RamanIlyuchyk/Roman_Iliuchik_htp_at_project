package steps.trashmail_yandex;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import web_driver.Driver;
import web_pages.trash_mail.MainPage;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TrashMailNewUser {
    private static boolean firstTime = true;
    static String BOOKING_PATH = "src/test/resources/properties/booking.properties";
    static String TRASHMAIL_PATH = "src/test/resources/properties/trashMail.properties";
    private static final Logger LOGGER = LogManager.getLogger(TrashMailNewUser.class);

    public static void trashMailGetNewMail() throws InterruptedException, IOException {
        LOGGER.debug("Getting new trash mail");
        Properties prop = Driver.getProperties(TRASHMAIL_PATH);
        Driver.getWebDriver().get("https://trashmail.com/");
        if (firstTime)
            Driver.findElementSendKeys("//*[@id='fe-mob-forward']", prop.getProperty("EMAIL"));
        getNewMail();
        MainPage.generateMail();
        TimeUnit.SECONDS.sleep(2);
        if (Driver.getWebDriver().findElements(By.xpath("//*[contains(text(),'address is not registered')]")).size() > 0) {
            LOGGER.debug("Account not registered. Creating new trashmail.com account");
            firstTime = false;
            MainPage.trashmailRegistration();
            trashMailGetNewMail();
        }
        TimeUnit.SECONDS.sleep(3);
    }

    private static void getNewMail() throws IOException {
        String newMail = Driver.findElementGetAttribute("//*[@id='fe-mob-name']", "value");
        newMail = newMail.concat("@trashmail.com");
        MailSteps.putEmailInProperty(newMail, BOOKING_PATH);
    }
}