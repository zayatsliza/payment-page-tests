package rest_api.services.payment_status_service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import rest_api.HttpClientFactory;
import rest_api.generators.SignatureGenerator;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.Map;

public class FetchPaymentStatusReq {

    private String targetUrl;
    private String publicKey;
    private String secretKey;
    private final static String ENDPOINT = "/api/v1/status";

    public FetchPaymentStatusReq(String url, String pbKey, String sbKey) {
        this.targetUrl = url;
        this.publicKey = pbKey;
        this.secretKey = sbKey;
    }

    protected Map<String, String> fetchPaymentStatus(String orderId) {
        Map<String, String> requestAttr = new LinkedHashMap<>();
        requestAttr.put("order_id", orderId);
        String requestBody = new Gson().toJson(requestAttr);

        String signature = SignatureGenerator.generateSignature(publicKey, requestBody, secretKey);
        HttpClient client = HttpClientFactory.create();
        HttpRequest request = HttpRequest.newBuilder(URI.create(targetUrl + ENDPOINT))
                .header("merchant", publicKey).header("signature", signature)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        Map<String, String> statusResponseAttr = new LinkedHashMap<>();
        try {
            HttpResponse<String> response = null;
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            System.out.printf("Finished with status code: %s. And response body: %s", response.statusCode(), body);

            JsonObject bodyJson = JsonParser.parseString(body).getAsJsonObject().get("order").getAsJsonObject();
            statusResponseAttr.put("amount", bodyJson.get("amount").getAsString());
            statusResponseAttr.put("currency", bodyJson.get("currency").getAsString());
            statusResponseAttr.put("status", bodyJson.get("status").getAsString());
        }
        catch (IOException | InterruptedException e) {
            System.out.println("Get payment status request is failed. See exception message below.");
            System.out.println(e.getMessage());
        }

        return statusResponseAttr;
    }

}
