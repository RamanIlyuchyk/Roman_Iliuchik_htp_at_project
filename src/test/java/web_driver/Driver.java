package web_driver;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import settings.Config;
import settings.DriverSettings;
import settings.ScreenMode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Properties;

public class Driver {
    private static WebElement element;
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private static final Logger LOGGER = LogManager.getLogger(Driver.class);

    public static WebDriver getWebDriver() {
        return webDriver.get();
    }

    public static void initDriver(Config config) throws MalformedURLException {
        if (webDriver.get() == null) {
            //try {
            webDriver.set(DriverManager.getDriver(config));
        } //catch (MalformedURLException e) {
        //e.printStackTrace();
        //}
        //}
    }

    public static WebDriver getFakeDriver() throws MalformedURLException {
        if (webDriver.get() == null) {
            //try {
            webDriver.set(DriverManager.getDriver(Config.CHROME));
        } //catch (MalformedURLException e) {
        //e.printStackTrace();
        //}
        return webDriver.get();
    }

    public static void openUrl(String url) {
        webDriver.get().get(url);
    }

    public static Properties getProperties(String path) throws IOException {
        LOGGER.debug("Getting data from the file");
        Properties properties = new Properties();
        InputStream input = new FileInputStream(path);
        properties.load(input);
        return properties;
    }

    public static void followTheLinkSetWindowMode(String url, ScreenMode screenMode) {
        DriverSettings.setScreenMode(screenMode, webDriver.get());
        webDriver.get().get(url);
    }

    public static void findElementClick(String xPath) {
        LOGGER.debug("Click on element");
        element = webDriver.get().findElement(By.xpath(xPath));
        element.click();
    }

    public static void findElementClickRepeat(String xPath, int startAmount, int finishAmount) {
        LOGGER.debug("Click on element " + (finishAmount - startAmount) + " times");
        element = webDriver.get().findElement(By.xpath(xPath));
        for (int i = 0; i < (finishAmount - startAmount); i++)
            element.click();
    }

    public static String findElementGetAttribute(String xPath, String attribute) {
        return webDriver.get().findElement(By.xpath(xPath)).getAttribute(attribute);
    }

    public static WebElement findElementClickReturn(String xPath) {
        element = webDriver.get().findElement(By.xpath(xPath));
        element.click();
        return element;
    }

    public static WebElement findElementReturn(String xPath) {
        element = webDriver.get().findElement(By.xpath(xPath));
        return element;
    }

    public static void findElementSendKeys(String xPath, String keys) {
        element = webDriver.get().findElement(By.xpath(xPath));
        element.sendKeys(keys);
    }

    public static String findElementGetText(String xPath) {
        return webDriver.get().findElement(By.xpath(xPath)).getText();
    }

    public static boolean isDisplayed(String xPath) {
        element = webDriver.get().findElement(By.xpath(xPath));
        return element.isDisplayed();
    }

    public static void destroy() {
        webDriver.get().close();
        webDriver.get().quit();
    }

    public static void clearCookies() {
        webDriver.get().get("chrome://settings/clearBrowserData");
        webDriver.get().findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
    }
}