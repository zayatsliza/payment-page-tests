package rest_api;

import java.net.http.HttpClient;

public class HttpClientFactory {

    static HttpClient client;
    private HttpClientFactory() {

    }

    public static HttpClient create() {
        if (client == null) {
            client = HttpClient.newHttpClient();
        }
        return client;
    }


}
