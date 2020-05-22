package tests.webService;

import application_items.Search;
import com.google.gson.Gson;
import org.junit.Test;
import steps.webService.GetDataSteps;
import steps.webService.HttpRequestSteps;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WebServiceTest {
    Gson gson = new Gson();

    @Test
    public void fullShortTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchData(gson, 0);
        String response = HttpRequestSteps.getHttpResponse(gson, search);
        List<String> list = GetDataSteps.getAllUsernames(response);
        list.forEach(System.out::println);
        assertTrue(GetDataSteps.fullNameChecker(list, "a"));
    }

    @Test
    public void fullLongTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchData(gson, 1);
        String response = HttpRequestSteps.getHttpResponse(gson, search);
        List<String> list = GetDataSteps.getAllUsernames(response);
        list.forEach(System.out::println);
        assertTrue(GetDataSteps.fullNameChecker(list, "berta"));
    }

    @Test
    public void partialShortTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchData(gson, 2);
        String response = HttpRequestSteps.getHttpResponse(gson, search);
        List<String> list = GetDataSteps.getAllUsernames(response);
        list.forEach(System.out::println);
        assertTrue(GetDataSteps.partialNameChecker(list, "a"));
    }

    @Test
    public void partialLongTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchData(gson, 3);
        String response = HttpRequestSteps.getHttpResponse(gson, search);
        List<String> list = GetDataSteps.getAllUsernames(response);
        list.forEach(System.out::println);
        assertTrue(GetDataSteps.partialNameChecker(list, "hn"));
    }

    @Test
    public void allUsersTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchData(gson, 4);
        String response = HttpRequestSteps.getHttpResponse(gson, search);
        List<String> list = GetDataSteps.getAllUsernames(response);
        list.forEach(System.out::println);
        assertEquals(6, list.size());
    }
}