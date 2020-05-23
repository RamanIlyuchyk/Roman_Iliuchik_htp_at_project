package web_pages.booking;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HotelsPage {
    public static WebElement scrollIntoViewHighlightSetAttribute(WebElement element, WebDriver driver, Actions actions) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
        actions.moveToElement(driver.findElement(By.xpath("//*[@data-hotelid][10]//span[contains(@class,'sr-hotel__name')]"))).perform();
        element = driver.findElement(By.xpath("//*[@data-hotelid][10]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor = 'green'", element);
        element = driver.findElement(By.xpath("//*[@data-hotelid][10]//span[contains(@class,'sr-hotel__name')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.color = 'red'", element);
        return element;
    }
}