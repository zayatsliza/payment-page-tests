package http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class CreatePaymentPage {

    private String upstreamURL;
    private String publicKey;
    private String secretKey;

    private String signature;

    private static final String ENDPOINT = "/api/v1/init";

    public CreatePaymentPage(String url, String pbKey, String sbKey) {
        this.upstreamURL = url;
        this.publicKey = pbKey;
        this.secretKey = sbKey;
    }

    public String createPaymentPage(PaymentPageBuilder paymentPageBuilder) {
        HttpClient client = HttpClientFactory.create();

        JsonObject requestBodyObject = getJsonBody(paymentPageBuilder);
        String requestBody = new GsonBuilder().setPrettyPrinting().create().toJson(requestBodyObject);

        signature = SignatureGenerator.generateSignature(publicKey, requestBody, secretKey);
        HttpRequest request = HttpRequest.newBuilder(URI.create(upstreamURL + ENDPOINT))
                .header("merchant", publicKey).header("signature", signature)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> response;
        String paymentPageUrl = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            System.out.printf("Finished with status code: %s. And response body: %s", response.statusCode(), body);
            // possible to process common returned status codes with additional logging
            paymentPageUrl = JsonParser.parseString(body).getAsJsonObject().get("url").getAsString();
        }
        catch (IOException | InterruptedException e) {
            System.out.println("Post request is failed. See exception message below.");
            System.out.println(e.getMessage());
            // optional insert request retry
        }

        return paymentPageUrl;
    }

    private JsonObject getJsonBody(PaymentPageBuilder paymentPageBuilder) {
        Map<String, Object> orderAttributes = new LinkedHashMap<>();
        Map<String, Object> pageCustomizationAttributes = new LinkedHashMap<>();

        orderAttributes.put("order_id", paymentPageBuilder.orderId);
        orderAttributes.put("amount", paymentPageBuilder.amount);
        orderAttributes.put("currency", paymentPageBuilder.currency);
        orderAttributes.put("order_description", paymentPageBuilder.orderDescription);
        orderAttributes.put("order_items", paymentPageBuilder.orderItems);
        orderAttributes.put("order_date", paymentPageBuilder.orderDate);
        orderAttributes.put("order_number", paymentPageBuilder.orderNumber);
        orderAttributes.put("type", paymentPageBuilder.type);
        orderAttributes.put("settle_interval", paymentPageBuilder.settleInterval);
        orderAttributes.put("retry_attempt", paymentPageBuilder.retryAttempt);
        orderAttributes.put("force3ds", paymentPageBuilder.force3ds);
        orderAttributes.put("google_pay_allowed_auth_methods", paymentPageBuilder.googlePayAllowedAuthMethods);
        orderAttributes.put("customer_date_of_birth", paymentPageBuilder.customerDateOfBirth);
        orderAttributes.put("customer_email", paymentPageBuilder.customerEmail);
        orderAttributes.put("customer_first_name", paymentPageBuilder.customerFirstName);
        orderAttributes.put("customer_last_name", paymentPageBuilder.customerLastName);
        orderAttributes.put("customer_phone", paymentPageBuilder.customerPhone);
        orderAttributes.put("traffic_source", paymentPageBuilder.trafficSource);
        orderAttributes.put("transaction_source", paymentPageBuilder.transactionSource);
        orderAttributes.put("purchase_country", paymentPageBuilder.purchaseCountry);
        orderAttributes.put("geo_country", paymentPageBuilder.geoCountry);
        orderAttributes.put("geo_city", paymentPageBuilder.geoCity);
        orderAttributes.put("language", paymentPageBuilder.language);
        orderAttributes.put("website", paymentPageBuilder.website);
        orderAttributes.put("order_metadata", paymentPageBuilder.orderMetadata);
        orderAttributes.put("success_url", paymentPageBuilder.successUrl);
        orderAttributes.put("fail_url", paymentPageBuilder.failUrl);

        pageCustomizationAttributes.put("public_name", paymentPageBuilder.publicName);
        pageCustomizationAttributes.put("order_title", paymentPageBuilder.orderTitle);
        pageCustomizationAttributes.put("order_description", paymentPageBuilder.orderDescription);
        pageCustomizationAttributes.put("payment_methods", paymentPageBuilder.paymentsMethods);
        pageCustomizationAttributes.put("button_font_color", paymentPageBuilder.buttonFontColor);
        pageCustomizationAttributes.put("button_color", paymentPageBuilder.buttonColor);
        pageCustomizationAttributes.put("font_name", paymentPageBuilder.fontName);
        pageCustomizationAttributes.put("is_cardholder_visible", paymentPageBuilder.isCardholderVisible);
        pageCustomizationAttributes.put("terms_url", paymentPageBuilder.termsUrl);
        pageCustomizationAttributes.put("back_url", paymentPageBuilder.backUrl);

        JsonObject orderJson = new Gson().toJsonTree(orderAttributes).getAsJsonObject();
        JsonObject pageCustomJson = new Gson().toJsonTree(pageCustomizationAttributes).getAsJsonObject();
        JsonObject commonJson = new JsonObject();
        commonJson.add("order", orderJson);
        commonJson.add("page_customization", pageCustomJson);
        return commonJson;
    }

}
