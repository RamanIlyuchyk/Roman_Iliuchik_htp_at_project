package tests.webService;

import application_items.Search;
import com.google.gson.Gson;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import steps.BaseSteps;
import steps.UsersApiSteps;
import steps.webService.GetDataSteps;
import utilities.RequiredValues;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class WebServiceTest {
    static GetDataSteps getDataSteps;
    static Gson gson;
    static Properties paths;
    static Search search;
    static RequiredValues condition;
    static RequiredValues result;
    static String WEB_SERVICE_CONDITIONS = "src/test/resources/webPaths.properties";
    private static final Logger LOGGER = LogManager.getLogger(UsersApiSteps.class);

    @Before
    public void before() throws IOException {
        LOGGER.info("Start test");
        gson = new Gson();
        getDataSteps = new GetDataSteps();
        paths = BaseSteps.getProperties(WEB_SERVICE_CONDITIONS);
    }

    @org.junit.Before
    public void beforeJunit() throws IOException {
        LOGGER.info("Start test");
        gson = new Gson();
        getDataSteps = new GetDataSteps();
        paths = BaseSteps.getProperties(WEB_SERVICE_CONDITIONS);
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

    @Test
    public void allUsersTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchData(gson, 0, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson, search);
        RequiredValues condition = getDataSteps.getTestCondition(gson, paths, "ALL_USERS");
        assertEquals(result.hashCode(), condition.hashCode());
    }

    @Test
    public void partialShortTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchData(gson, 1, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson, search);
        RequiredValues condition = getDataSteps.getTestCondition(gson, paths, "PARTIAL_SHORT");
        assertEquals(result.hashCode(), condition.hashCode());
    }

    @Test
    public void fullShortTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchData(gson, 2, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson, search);
        RequiredValues condition = getDataSteps.getTestCondition(gson, paths, "FULL_SHORT");
        assertEquals(result.hashCode(), condition.hashCode());
    }

    @Test
    public void partialLongTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchData(gson, 3, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson, search);
        RequiredValues condition = getDataSteps.getTestCondition(gson, paths, "PARTIAL_LONG");
        assertEquals(result.hashCode(), condition.hashCode());
    }

    @Test
    public void fullLongTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchData(gson, 4, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson, search);
        RequiredValues condition = getDataSteps.getTestCondition(gson, paths, "FULL_LONG");
        assertEquals(result.hashCode(), condition.hashCode());
    }

    @After
    public void after() {
        LOGGER.info("Finish test");
    }

    @org.junit.After
    public void afterJunit() {
        LOGGER.info("Finish test");
    }
}