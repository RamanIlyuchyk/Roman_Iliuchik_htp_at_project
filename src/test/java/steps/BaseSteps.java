package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import settings.DriverSettings;
import settings.ScreenMode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class BaseSteps {
    static WebElement element;

    public static Properties getProperties(String path) throws IOException {
        Properties properties = new Properties();
        InputStream input = new FileInputStream(path);
        properties.load(input);
        return properties;
    }

    public static void followTheLinkSetWindowMode(WebDriver driver, String url, ScreenMode screenMode) {
        DriverSettings.setScreenMode(screenMode, driver);
        driver.get(url);
    }

    public static void findElementSendKeys(WebDriver driver, String xPath, String keys) {
        element = driver.findElement(By.xpath(xPath));
        element.sendKeys(keys);
    }

    public static void findElementClick(WebDriver driver, String xPath) {
        element = driver.findElement(By.xpath(xPath));
        element.click();
    }

    public static WebElement findElementClickReturn(WebDriver driver, String xPath) {
        element = driver.findElement(By.xpath(xPath));
        element.click();
        return element;
    }

    public static String findElementGetAttribute(WebDriver driver, String xPath, String attribute) {
        return driver.findElement(By.xpath(xPath)).getAttribute(attribute);
    }

    public static void findElementClickRepeat(WebDriver driver, String xPath, int startAmount, int finishAmount) {
        element = driver.findElement(By.xpath(xPath));
        for (int i = 0; i < (finishAmount - startAmount); i++)
            element.click();
    }

    public static String findElementGetText(WebDriver driver, String xPath) {
        return driver.findElement(By.xpath(xPath)).getText();
    }

    public static WebElement findElementByCssSelector(WebDriver driver, String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector));
    }

    public static String findElementClickGetText(WebDriver driver, String xPath) {
        element = driver.findElement(By.xpath(xPath));
        element.click();
        return element.getText();
    }

    public static void findElementCheckAttribute(WebDriver driver, String xPath, String attribute, String expected) {
        String getAttribute = driver.findElement(By.xpath(xPath)).getAttribute(attribute);
        assertEquals("Something wrong", expected, getAttribute);
    }

    public static void destroy(WebDriver driver) {
        driver.close();
        driver.quit();
    }
}