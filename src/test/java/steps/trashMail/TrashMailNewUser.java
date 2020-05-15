package steps.trashMail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import steps.BaseSteps;
import steps.MailSteps;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TrashMailNewUser {
    public static boolean firstTime = true;
    protected static String TRASH_MAIL_PATH = "src/test/resources/trashMail.properties";
    protected static String BOOKING_PATH = "src/test/resources/booking.properties";
    static String TRASH_MAIL_URL = "https://trashmail.com/";
    static String DOMAIN = "@trashmail.com";

    public static void trashMailGetTempMail(WebDriver driver) throws InterruptedException, IOException {
        Properties prop = BaseSteps.getProperties(TRASH_MAIL_PATH);
        driver.get(TRASH_MAIL_URL);
        if (firstTime)
            BaseSteps.findElementSendKeys(driver, "//*[@id='fe-mob-forward']", prop.getProperty("EMAIL"));
        getNewMail(driver);
        BaseSteps.findElementClick(driver, "//*[@id='fe-mob-fwd-nb']");
        BaseSteps.findElementClick(driver, "//*[@id='fe-mob-fwd-nb']/option[contains(text(),'1')][1]");
        BaseSteps.findElementClick(driver, "//*[@id='fe-mob-life-span']");
        BaseSteps.findElementClick(driver, "//*[@id='fe-mob-life-span']/option[contains(text(),'1 day')]");
        BaseSteps.findElementClick(driver, "//*[@id='fe-mob-submit']");
        TimeUnit.SECONDS.sleep(3);
        if (driver.findElements(By.xpath("//*[contains(text(),'address is not registered')]")).size() > 0) {
            firstTime = false;
            trashMailRegistration(driver);
            trashMailGetTempMail(driver);
        }
        TimeUnit.SECONDS.sleep(5);
    }

    public static void getNewMail(WebDriver driver) throws IOException {
        String newMail = BaseSteps.findElementGetAttribute(driver, "//*[@id='fe-mob-name']", "value");
        newMail = newMail.concat(DOMAIN);
        MailSteps.putEmailInProperty(BOOKING_PATH, newMail);
    }

    public static void trashMailRegistration(WebDriver driver) throws InterruptedException, IOException {
        Properties prop = BaseSteps.getProperties(TRASH_MAIL_PATH);
        BaseSteps.findElementClick(driver, "//*[contains(@href,'mob-register')]");
        TimeUnit.SECONDS.sleep(1);
        BaseSteps.findElementSendKeys(driver, "//*[@id='tab-mob-register']/form/div[1]/input", prop.getProperty("LOGIN"));
        BaseSteps.findElementSendKeys(driver, "//*[@id='tab-mob-register']/form/div[2]/input", prop.getProperty("PASSWORD"));
        BaseSteps.findElementSendKeys(driver, "//*[@id='tab-mob-register']/form/div[3]/input", prop.getProperty("PASSWORD"));
        BaseSteps.findElementClick(driver, "//*[@id='tab-mob-register']/form/div[6]/button");
        TimeUnit.SECONDS.sleep(7);
        MailSteps.confirmLinkOnYandexMail(driver, "TrashMail");
        String currentHandle = driver.getWindowHandle();
        TimeUnit.SECONDS.sleep(2);
        BaseSteps.findElementClick(driver, "//*[contains(@href,'trashmail')]");
        TimeUnit.SECONDS.sleep(7);
        Set<String> handles = driver.getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                driver.switchTo().window(currentHandle);
            }
        }
    }
}