package tests.fake;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import web_driver.Driver;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class FakeGuiSteps {
    @Given(value = "I go to tut.by")
    public void checkHeaderTest() throws MalformedURLException {
        Driver.getFakeDriver().get("https://tut.by");
    }

    @When("I start waiting")
    public void checkFooterTest() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
    }

    @Then("I just passed")
    public void verify() {
        Assert.assertTrue(true);
    }
}