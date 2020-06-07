package tests.mobile.tut_by;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class TutBy {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "My");
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("browserName", "chrome");
        WebDriver driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        driver.get("https:/tut.by");
        Thread.sleep(5000);
        driver.quit();
    }
}