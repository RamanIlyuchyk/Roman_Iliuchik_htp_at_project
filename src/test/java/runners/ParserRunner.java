package runners;

import application_items.Search;
import utils.HttpClient;
import utils.Parser;

import java.io.IOException;
import java.net.URISyntaxException;

public class ParserRunner {
    static Parser parser = new Parser();

    public static void main(String[] args)
            throws IOException, URISyntaxException {
        parser.parseJSON();
        parser.parseGSON();
        parser.parseJackson();
        parser.fromGSON();
        HttpClient httpClient = new HttpClient();
        Search search = new Search("berta", true);
        httpClient.search(search);
    }
}