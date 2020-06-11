package tests.junit.web_service;

import org.junit.After;
import org.junit.Before;
import test_objects.Search;
import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import steps.web_service.GetDataSteps;
import test_objects.RequiredValues;
import web_driver.Driver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class WebServiceTest {
    static GetDataSteps getDataSteps;
    static Gson gson;
    static Properties paths;
    static String WEB_SERVICE_CONDITIONS = "src/test/resources/properties/webPaths.properties";
    private static final Logger LOGGER = LogManager.getLogger(WebServiceTest.class);

    @Before
    public void before() throws IOException {
        LOGGER.info("Start test");
        gson = new Gson();
        getDataSteps = new GetDataSteps();
        paths = Driver.getProperties(WEB_SERVICE_CONDITIONS);
    }

    @Test
    public void allUsersTest() throws IOException, URISyntaxException {
        LOGGER.debug("I check ALL_USERS names");
        Search search = GetDataSteps.getDataForRequest(gson, 0, paths);
        RequiredValues response = getDataSteps.getResponse(gson, search);
        RequiredValues preliminary = getDataSteps.getDataForComparisonWithResponse(gson, paths, "ALL_USERS");
        LOGGER.debug("I compare response and preliminary data");
        assertEquals(response.hashCode(), preliminary.hashCode());
    }

    @Test
    public void partialShortTest() throws IOException, URISyntaxException {
        LOGGER.debug("I check PARTIAL_SHORT names");
        Search search = GetDataSteps.getDataForRequest(gson, 1, paths);
        RequiredValues response = getDataSteps.getResponse(gson, search);
        RequiredValues preliminary = getDataSteps.getDataForComparisonWithResponse(gson, paths, "PARTIAL_SHORT");
        LOGGER.debug("I compare response and preliminary data");
        assertEquals(response.hashCode(), preliminary.hashCode());
    }

    @Test
    public void fullShortTest() throws IOException, URISyntaxException {
        LOGGER.debug("I check FULL_SHORT names");
        Search search = GetDataSteps.getDataForRequest(gson, 2, paths);
        RequiredValues response = getDataSteps.getResponse(gson, search);
        RequiredValues preliminary = getDataSteps.getDataForComparisonWithResponse(gson, paths, "FULL_SHORT");
        LOGGER.debug("I compare response and preliminary data");
        assertEquals(response.hashCode(), preliminary.hashCode());
    }

    @Test
    public void partialLongTest() throws IOException, URISyntaxException {
        LOGGER.debug("I check PARTIAL_LONG names");
        Search search = GetDataSteps.getDataForRequest(gson, 3, paths);
        RequiredValues response = getDataSteps.getResponse(gson, search);
        RequiredValues preliminary = getDataSteps.getDataForComparisonWithResponse(gson, paths, "PARTIAL_LONG");
        LOGGER.debug("I compare response and preliminary data");
        assertEquals(response.hashCode(), preliminary.hashCode());
    }

    @Test
    public void fullLongTest() throws IOException, URISyntaxException {
        LOGGER.debug("I check FULL_LONG names");
        Search search = GetDataSteps.getDataForRequest(gson, 4, paths);
        RequiredValues response = getDataSteps.getResponse(gson, search);
        RequiredValues preliminary = getDataSteps.getDataForComparisonWithResponse(gson, paths, "FULL_LONG");
        LOGGER.debug("I compare response and preliminary data");
        assertEquals(response.hashCode(), preliminary.hashCode());
    }

    @After
    public void after() {
        LOGGER.info("Finish test");
    }
}