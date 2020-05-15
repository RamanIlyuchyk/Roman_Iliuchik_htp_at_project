package steps;

import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MailSteps {
    protected static String YANDEX_PATH = "src/test/resources/yandexMail.properties";
    static String YANDEX_URL = "https://mail.yandex.ru/";

    public static void confirmLinkOnYandexMail(WebDriver driver, String sender) throws InterruptedException, IOException {
        driver.get(YANDEX_URL);
        Properties prop = BaseSteps.getProperties(YANDEX_PATH);
        TimeUnit.SECONDS.sleep(2);
        BaseSteps.findElementClick(driver, "//*[contains(@class,'HeadBanner-Button-Enter')]");
        BaseSteps.findElementSendKeys(driver, "//*[@id='passp-field-login']", prop.getProperty("EMAIL"));
        BaseSteps.findElementClick(driver, "//*[contains(@class,'submit passp-form-button')]");
        TimeUnit.SECONDS.sleep(2);
        BaseSteps.findElementSendKeys(driver, "//*[@id='passp-field-passwd']", prop.getProperty("PASSWORD"));
        BaseSteps.findElementClick(driver, "//*[contains(@class,'submit passp-form-button')]");
        TimeUnit.SECONDS.sleep(5);
        BaseSteps.findElementClick(driver, String.format("//*[contains(text(),'%s')]", sender));
        TimeUnit.SECONDS.sleep(5);
    }

    public static void putEmailInProperty(String propertyPath, String newMail) throws IOException {
        Properties prop = BaseSteps.getProperties(propertyPath);
        OutputStream out = new FileOutputStream(propertyPath);
        prop.put("NEW_MAIL", newMail);
        prop.store(out, null);
    }
}