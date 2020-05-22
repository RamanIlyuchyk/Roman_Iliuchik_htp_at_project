package steps.webService;

import application_items.Search;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GetDataSteps {
    private static final String JSON = "src/test/resources/testData/search.json";

    public static Search getSearchData(Gson gson, int condition) throws FileNotFoundException {
        Search[] searchData = gson.fromJson(new JsonReader(new FileReader(JSON)), Search[].class);
        return searchData[condition];
    }

    public static List<String> getAllUsernames(String response) {
        Pattern pattern = Pattern.compile("\"username\": \"[A-z]+\"");
        Matcher matcher = pattern.matcher(response);
        List<String> userNames = new ArrayList<>();
        while (matcher.find())
            userNames.add(matcher.group());
        userNames = userNames.stream().map(s -> s.replaceAll("\"username\": ", "")).map(s -> s.replaceAll("\"", "")).collect(Collectors.toList());
        return userNames;
    }

    public static boolean fullNameChecker(List<String> userNames, String check) {
        Pattern pattern = Pattern.compile(String.format("^%s$", check));
        for (String name : userNames) {
            if (!pattern.matcher(name).matches())
                return false;
        }
        return true;
    }

    public static boolean partialNameChecker(List<String> userNames, String check) {
        Pattern pattern = Pattern.compile(String.format(".*%s.*", check));
        for (String name : userNames) {
            if (!pattern.matcher(name).matches())
                return false;
        }
        return true;
    }
}