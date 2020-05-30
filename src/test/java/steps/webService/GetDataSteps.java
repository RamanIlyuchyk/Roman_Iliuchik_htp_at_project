package steps.webService;

import application_items.Search;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import steps.UsersApiSteps;
import utilities.RequiredValues;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class GetDataSteps {
    private static final Logger LOGGER = LogManager.getLogger(UsersApiSteps.class);

    public static Search getSearchData(Gson gson, int condition, Properties paths) throws FileNotFoundException {
        LOGGER.debug("Parsing predicate to .json");
        Search[] searchData = gson.fromJson(new JsonReader(new FileReader(paths.getProperty("JSON"))), Search[].class);
        return searchData[condition];
    }

    public RequiredValues parseResponseToClass(Gson gson, Search search) throws IOException, URISyntaxException {
        LOGGER.debug("Parsing response to class object");
        return gson.fromJson(HttpRequestSteps.getHttpResponse(gson, search), RequiredValues.class);
    }

    public RequiredValues getTestCondition(Gson gson, Properties paths, String condition) throws FileNotFoundException {
        LOGGER.debug("Parsing .json with validation data to class");
        return gson.fromJson(new JsonReader(new FileReader(paths.getProperty(condition))), RequiredValues.class);
    }
}