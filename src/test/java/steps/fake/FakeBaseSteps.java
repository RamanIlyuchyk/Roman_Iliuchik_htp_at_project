package steps.fake;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import settings.TempConfig;
import web_driver.TempDriver;

import java.net.MalformedURLException;

public class FakeBaseSteps {
    private static final Logger LOGGER = LogManager.getLogger(FakeBaseSteps.class);

    @Before
    public void beforeTest() throws MalformedURLException {
        LOGGER.info("Initializing WebDriver..");
        TempDriver.initDriver(TempConfig.CHROME);
    }

    @After
    public void afterTest() {
        LOGGER.info("Killing WebDriver..");
        TempDriver.destroy();
    }
}