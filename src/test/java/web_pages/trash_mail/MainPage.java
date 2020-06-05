package web_pages.trash_mail;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import steps.trashmail_yandex.MailSteps;
import web_driver.Driver;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MainPage {
    static String TRASHMAIL_PATH = "src/test/resources/properties/trashMail.properties";
    private static final Logger LOGGER = LogManager.getLogger(MainPage.class);

    public static void generateMail() {
        LOGGER.debug("Creating trash email on 1 day");
        Driver.findElementClick("//*[@id='fe-mob-fwd-nb']");
        Driver.findElementClick("//*[@id='fe-mob-fwd-nb']/option[contains(text(),'1')]");
        Driver.findElementClick("//*[@id='fe-mob-life-span']");
        Driver.findElementClick("//*[@id='fe-mob-life-span']/option[contains(text(),'1 day')]");
        Driver.findElementClick("//*[@id='fe-mob-submit']");
    }

    public static void trashmailRegistration() throws InterruptedException, IOException {
        LOGGER.debug("Registration on trashmail.com");
        Properties prop = Driver.getProperties(TRASHMAIL_PATH);
        Driver.findElementClick("//*[contains(@href,'mob-register')]");
        TimeUnit.SECONDS.sleep(1);
        Driver.findElementSendKeys("//*[@id='tab-mob-register']/form/div[1]/input", prop.getProperty("LOGIN"));
        LOGGER.debug("Printing email");
        Driver.findElementSendKeys("//*[@id='tab-mob-register']/form/div[2]/input", prop.getProperty("PASSWORD"));
        Driver.findElementSendKeys("//*[@id='tab-mob-register']/form/div[3]/input", prop.getProperty("PASSWORD"));
        LOGGER.debug("Printing password twice");
        Driver.findElementClick("//*[@id='tab-mob-register']/form/div[6]/button");
        TimeUnit.SECONDS.sleep(7);
        MailSteps.confirmLinkOnYandexMail("TrashMail");
        Driver.findElementClick("//*[contains(@href,'trashmail')]");
        TimeUnit.SECONDS.sleep(7);
    }
}