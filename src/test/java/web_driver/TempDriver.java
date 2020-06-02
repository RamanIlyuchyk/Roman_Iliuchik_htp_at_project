package web_driver;

import org.openqa.selenium.WebDriver;
import settings.TempConfig;

import java.net.MalformedURLException;

public class TempDriver {
    private static ThreadLocal<WebDriver> tempDriver = new ThreadLocal<>();

    public static void initDriver(TempConfig tempConfig) throws MalformedURLException {
        if (null == tempDriver.get()) {
            //try {
            tempDriver.set(TempDriverManager.getDriver(tempConfig));
        } //catch (MalformedURLException e) {
        //e.printStackTrace();
        //}
        //}
    }

    public static WebDriver getDriver() throws MalformedURLException {
        if (tempDriver.get() == null) {
            //try {
            tempDriver.set(TempDriverManager.getDriver(TempConfig.CHROME));
        } //catch (MalformedURLException e) {
        //e.printStackTrace();
        //}
        return tempDriver.get();
    }

    public static void destroy() {
        tempDriver.get().close();
        tempDriver.get().quit();
    }
}