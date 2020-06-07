package tests.mobile.android_clock;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.temporal.ChronoUnit.HOURS;

public class AndroidClock {
    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Emulator");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.google.android.deskclock");
        capabilities.setCapability("appActivity", "com.android.deskclock.DeskClock");
        AndroidDriver<MobileElement> driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Cities']")).click();
        Thread.sleep(1000);
        MobileElement search = driver.findElementById("search_src_text");
        search.sendKeys("Rome");
        driver.findElement(By.id("city_name")).click();
        Thread.sleep(1000);
        List<MobileElement> time = driver.findElementsById("digital_clock");
        System.out.println(time.get(0).getText());
        System.out.println(time.get(1).getText());
        LocalTime time1 = LocalTime.parse(time.get(0).getText(), DateTimeFormatter.ofPattern("h:mm a"));
        LocalTime time2 = LocalTime.parse(time.get(1).getText(), DateTimeFormatter.ofPattern("h:mm a"));
        System.out.println("HOURS.between: " + HOURS.between(time2, time1));
    }
}