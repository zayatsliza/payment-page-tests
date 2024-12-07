package http;

import java.net.http.HttpClient;

public class HttpClientFactory {

    static HttpClient client;
    private HttpClientFactory() {

    }

    static HttpClient create() {
        if (client == null) {
            client = HttpClient.newHttpClient();
        }
        return client;
    }


}
