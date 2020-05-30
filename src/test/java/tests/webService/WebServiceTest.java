package tests.webService;

import application_items.Search;
import com.google.gson.Gson;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Test;
import steps.BaseSteps;
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

    @Before
    public void before() throws IOException {
        gson = new Gson();
        getDataSteps = new GetDataSteps();
        paths = BaseSteps.getProperties(WEB_SERVICE_CONDITIONS);
    }

    @org.junit.Before
    public void beforeJunit() throws IOException {
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
        assertEquals(condition.code, result.code);
    }

    @Test
    public void allUsersTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchData(gson, 0, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson, search);
        RequiredValues condition = getDataSteps.getTestCondition(gson, paths, "ALL_USERS");
        assertEquals(condition.code, result.code);
    }

    @Test
    public void partialShortTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchData(gson, 1, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson, search);
        RequiredValues condition = getDataSteps.getTestCondition(gson, paths, "PARTIAL_SHORT");
        assertEquals(condition.code, result.code);
    }

    @Test
    public void fullShortTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchData(gson, 2, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson, search);
        RequiredValues condition = getDataSteps.getTestCondition(gson, paths, "FULL_SHORT");
        assertEquals(condition.code, result.code);
    }

    @Test
    public void partialLongTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchData(gson, 3, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson, search);
        RequiredValues condition = getDataSteps.getTestCondition(gson, paths, "PARTIAL_LONG");
        assertEquals(condition.code, result.code);
    }

    @Test
    public void fullLongTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchData(gson, 4, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson, search);
        RequiredValues condition = getDataSteps.getTestCondition(gson, paths, "FULL_LONG");
        assertEquals(condition.code, result.code);
    }
}