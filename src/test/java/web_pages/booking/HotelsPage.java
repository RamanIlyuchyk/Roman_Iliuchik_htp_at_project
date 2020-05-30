package web_pages.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import steps.UsersApiSteps;

import java.util.concurrent.TimeUnit;

public class HotelsPage {
    private static final Logger LOGGER = LogManager.getLogger(UsersApiSteps.class);

    public static WebElement executorSetBackgroundTitleColor(WebElement element, WebDriver driver, Actions actions) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
        TimeUnit.SECONDS.sleep(3);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id='hotellist_inner']/div[11]/div[2]/div/div/div[2]/a"))).build().perform();
        LOGGER.debug("Finding 10th hotel on page");
        TimeUnit.SECONDS.sleep(3);
        element = driver.findElement(By.xpath("//*[@id='hotellist_inner']/div[11]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor='green'", element);
        LOGGER.debug("Changing background color to green");
        TimeUnit.SECONDS.sleep(3);
        element = driver.findElement(By.xpath("//*[@id='hotellist_inner']/div[11]/div[2]/div/div/div/h3/a"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.color='red'", element);
        LOGGER.debug("Changing text color to red");
        TimeUnit.SECONDS.sleep(3);
        return element;
    }
}