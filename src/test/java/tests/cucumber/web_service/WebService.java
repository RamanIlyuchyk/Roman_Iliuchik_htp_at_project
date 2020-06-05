package tests.cucumber.web_service;

import com.google.gson.Gson;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import steps.web_service.GetDataSteps;
import test_objects.RequiredValues;
import test_objects.Search;
import web_driver.Driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class WebService {
    static GetDataSteps getDataSteps;
    static Gson gson;
    static Properties paths;
    static Search search;
    static RequiredValues condition;
    static RequiredValues result;
    static String WEB_SERVICE_CONDITIONS = "src/test/resources/properties/webPaths.properties";
    private static final Logger LOGGER = LogManager.getLogger(WebService.class);

    @Before
    public void before() throws IOException {
        LOGGER.info("Start test");
        gson = new Gson();
        getDataSteps = new GetDataSteps();
        paths = Driver.getProperties(WEB_SERVICE_CONDITIONS);
    }

    @Given("I start finding by {int} predicate")
    public void iStartFindingByPredicate(Integer int1) throws IOException {
        search = GetDataSteps.getSearchData(gson, int1, paths);
    }

    @When("I get a response from a web service")
    public void iGetAResponseFromAWebService() throws IOException, URISyntaxException {
        result = getDataSteps.parseResponseToClass(gson, search);
    }

    @And("I form a known {string} result")
    public void iFormAKnownResult(String string) throws FileNotFoundException {
        condition = getDataSteps.getTestCondition(gson, paths, string);
    }

    @Then("I validate the web service response")
    public void iValidateTheWebServiceResponse() {
        assertEquals(result.hashCode(), condition.hashCode());
    }

    @After
    public void after() {
        LOGGER.info("Finish test");
    }
}