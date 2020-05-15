package web_driver;

import org.openqa.selenium.WebDriver;
import settings.Config;

public class Driver {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getWebDriver(Config config) {
        if (webDriver.get() == null)
            webDriver.set(DriverManager.getDriver(config));
        return webDriver.get();
    }
}