package steps.trashmail_yandex;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import web_driver.Driver;
import web_pages.trash_mail.MainPage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MailSteps {
    private static boolean firstTime = true;
    static String BOOKING_PATH = "src/test/resources/properties/booking.properties";
    static String TRASHMAIL_PATH = "src/test/resources/properties/trashMail.properties";
    static String YANDEX_PATH = "src/test/resources/properties/yandexMail.properties";
    private static final Logger LOGGER = LogManager.getLogger(MailSteps.class);

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
        putEmailInProperty(newMail, BOOKING_PATH);
    }

    public static void confirmLinkOnYandexMail(String sender) throws InterruptedException, IOException {
        LOGGER.debug("Going to yandex.ru");
        Driver.openUrl("https://mail.yandex.ru/");
        Properties prop = Driver.getProperties(YANDEX_PATH);
        TimeUnit.SECONDS.sleep(2);
        Driver.findElementClick("//*[contains(@class,'HeadBanner-Button-Enter')]");
        LOGGER.debug("Printing email");
        Driver.findElementSendKeys("//*[@id='passp-field-login']", prop.getProperty("EMAIL"));
        Driver.findElementClick("//*[contains(@class,'submit passp-form-button')]");
        TimeUnit.SECONDS.sleep(2);
        LOGGER.debug("Printing password");
        Driver.findElementSendKeys("//*[@id='passp-field-passwd']", prop.getProperty("PASSWORD"));
        Driver.findElementClick("//*[contains(@class,'submit passp-form-button')]");
        TimeUnit.SECONDS.sleep(5);
        LOGGER.debug("Finding email from " + sender);
        Driver.findElementClick(String.format("//*[contains(text(),'%s')]", sender));
        TimeUnit.SECONDS.sleep(2);
    }

    public static void putEmailInProperty(String newMail, String propertyPath) throws IOException {
        LOGGER.debug("Put new trash email in property");
        Properties prop = Driver.getProperties(propertyPath);
        OutputStream out = new FileOutputStream(propertyPath);
        prop.put("NEW_MAIL", newMail);
        prop.store(out, null);
    }
}