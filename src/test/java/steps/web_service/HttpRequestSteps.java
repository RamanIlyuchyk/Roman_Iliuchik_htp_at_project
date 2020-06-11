package steps.web_service;

import test_objects.Search;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;

public class HttpRequestSteps {
    private static final String URL = "http://178.124.206.46:8001/app/ws/";
    private static final Logger LOGGER = LogManager.getLogger(HttpRequestSteps.class);

    public static String getHttpResponse(Gson gson, Search search) throws IOException, URISyntaxException {
        LOGGER.debug("I get response");
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder(URL);
        HttpPost request = new HttpPost(builder.build());
        request.setEntity(new StringEntity(gson.toJson(search)));
        HttpResponse response = client.execute(request);
        return EntityUtils.toString(response.getEntity());
    }
}