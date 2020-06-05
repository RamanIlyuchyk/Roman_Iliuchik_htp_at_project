package web_driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import settings.Config;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    public static WebDriver getDriver(Config config) throws MalformedURLException {
        switch (config) {
            case CHROME:
                return getChromeDriver();
            case FF:
                return new FirefoxDriver();
            case REMOTE:
                return getRemoteDriver();
            default:
                throw null;
        }
    }

    private static WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        //options.addArguments("--disable-gpu");
        //options.addArguments("--window-size=1920,1200");
        return new ChromeDriver(//options
        );
    }

    private static WebDriver getRemoteDriver() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
    }
}