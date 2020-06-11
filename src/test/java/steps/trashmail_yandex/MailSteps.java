package steps.trashmail_yandex;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import web_driver.Driver;
import web_pages.trash_mail.TrashMailPage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MailSteps {
    public static boolean firstTime = true;
    static final String TRASHMAIL_URL = "https://trashmail.com/";
    static final String YANDEX_URL = "https://mail.yandex.ru/";
    static final String BOOKING_PATH = "src/test/resources/properties/booking.properties";
    static final String TRASHMAIL_PATH = "src/test/resources/properties/trashMail.properties";
    static final String YANDEX_PATH = "src/test/resources/properties/yandexMail.properties";
    static final String REAL_EMAIL_XPATH = "//*[@id='fe-mob-forward']";
    static final String CHECKER_XPATH = "//*[contains(text(),'address is not registered')]";
    static final String NEW_EMAIL_XPATH = "//*[@id='fe-mob-name']";
    static final String SIGN_IN_XPATH = "//*[contains(@class,'HeadBanner-Button-Enter')]";
    static final String ENTER_XPATH = "//*[contains(@class,'submit passp-form-button')]";
    static final String LOGIN_XPATH = "//*[@id='passp-field-login']";
    static final String PASSWORD_XPATH = "//*[@id='passp-field-passwd']";
    static final String SENDER_XPATH = "//*[contains(text(),'%s')]";
    static final String MAIL_ATTRIBUTE = "value";
    static final String DOMAIN = "@trashmail.com";
    private static final Logger LOGGER = LogManager.getLogger(MailSteps.class);

    public static void trashMailGetNewMail() throws InterruptedException, IOException {
        LOGGER.debug("Getting new trash mail");
        Properties prop = Driver.getProperties(TRASHMAIL_PATH);
        Driver.getWebDriver().get(TRASHMAIL_URL);
        if (firstTime)
            Driver.findElementSendKeys(REAL_EMAIL_XPATH, prop.getProperty("EMAIL"));
        getNewMail();
        TrashMailPage trashMailPage = new TrashMailPage(Driver.getWebDriver());
        trashMailPage.generateMail();
        TimeUnit.SECONDS.sleep(2);
        if (Driver.getWebDriver().findElements(By.xpath(CHECKER_XPATH)).size() > 0) {
            LOGGER.debug("Account not registered. Creating new trashmail.com account");
            firstTime = false;
            trashMailPage.trashmailRegistration();
            trashMailGetNewMail();
        }
        TimeUnit.SECONDS.sleep(3);
    }

    private static void getNewMail() throws IOException {
        String newMail = Driver.findElementGetAttribute(NEW_EMAIL_XPATH, MAIL_ATTRIBUTE);
        newMail = newMail.concat(DOMAIN);
        putEmailInProperties(newMail, BOOKING_PATH);
    }

    public static void confirmLinkOnYandexMail(String sender) throws InterruptedException, IOException {
        LOGGER.debug("Going to yandex.ru");
        Driver.openUrl(YANDEX_URL);
        Properties prop = Driver.getProperties(YANDEX_PATH);
        TimeUnit.SECONDS.sleep(2);
        Driver.findElementClick(SIGN_IN_XPATH);
        LOGGER.debug("Printing email");
        Driver.findElementSendKeys(LOGIN_XPATH, prop.getProperty("EMAIL"));
        Driver.findElementClick(ENTER_XPATH);
        TimeUnit.SECONDS.sleep(2);
        LOGGER.debug("Printing password");
        Driver.findElementSendKeys(PASSWORD_XPATH, prop.getProperty("PASSWORD"));
        Driver.findElementClick(ENTER_XPATH);
        TimeUnit.SECONDS.sleep(5);
        LOGGER.debug("Finding email from " + sender);
        Driver.findElementClick(String.format(SENDER_XPATH, sender));
        TimeUnit.SECONDS.sleep(2);
    }

    public static void putEmailInProperties(String newMail, String propertiesPath) throws IOException {
        LOGGER.debug("Put new trash email in properties");
        Properties prop = Driver.getProperties(propertiesPath);
        OutputStream out = new FileOutputStream(propertiesPath);
        prop.put("NEW_MAIL", newMail);
        prop.store(out, null);
    }
}