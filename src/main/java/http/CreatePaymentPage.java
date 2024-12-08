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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class CreatePaymentPage {

    private String upstreamURL;
    private String publicKey;
    private String secretKey;

    private String signature;
    private String orderId;
    private Integer amount;
    private String currency;
    private String orderDescription;
    private String orderItems;
    private String orderDate;
    private Integer orderNumber;
    private String type;
    private Integer settleInterval;
    private Integer retryAttempt;
    private Boolean force3ds;
    private String[] googlePayAllowedAuthMethods;
    private String customerDateOfBirth;
    private String customerEmail;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhone;
    private String trafficSource;
    private String transactionSource;
    private String purchaseCountry;
    private String geoCountry;
    private String geoCity;
    private String language;
    private String website;
    private Map<String, String> orderMetadata;
    private String successUrl;
    private String failUrl;

    private String publicName;
    private String orderTitle;
    private String[] paymentsMethods;
    private String buttonFontColor;
    private String buttonColor;
    private String fontName;
    private Boolean isCardholderVisible;
    private String termsUrl;
    private String backUrl;

   private static final String ENDPOINT = "/api/v1/init";

    public CreatePaymentPage(String url, String pbKey, String sbKey) {
        this.upstreamURL = url;
        this.publicKey = pbKey;
        this.secretKey = sbKey;
    }

    public CreatePaymentPage withOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public CreatePaymentPage withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public CreatePaymentPage withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public CreatePaymentPage withOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
        return this;
    }

    public CreatePaymentPage withOrderItems(String... orderItems) {
        this.orderItems = Arrays.stream(orderItems).collect(Collectors.joining(", "));
        return this;
    }

    public CreatePaymentPage withOrderDate(LocalDateTime orderDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateFormat = orderDate.format(formatter);
        this.orderDate = dateFormat;
        return this;
    }

    public CreatePaymentPage withOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public CreatePaymentPage withType(String type) {
        this.type = type;
        return this;
    }

    public CreatePaymentPage withSettleInterval(int settleInterval) {
        this.settleInterval = settleInterval;
        return this;
    }

    public CreatePaymentPage withRetryAttempt(int retryAttempt) {
        this.retryAttempt = retryAttempt;
        return this;
    }

    public CreatePaymentPage withForce3ds(boolean force3ds) {
        this.force3ds = force3ds;
        return this;
    }

    public CreatePaymentPage withGooglePayAllowedAuthMethods(String[] googlePayAllowedAuthMethods) {
        this.googlePayAllowedAuthMethods = googlePayAllowedAuthMethods;
        return this;
    }

    public CreatePaymentPage withCustomerDateOfBirth(LocalDate dateOfBirth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.customerDateOfBirth = dateOfBirth.format(formatter);
        return this;
    }

    public CreatePaymentPage withCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
        return this;
    }

    public CreatePaymentPage withCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
        return this;
    }
    public CreatePaymentPage withCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
        return this;
    }

    public CreatePaymentPage withCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
        return this;
    }

    public CreatePaymentPage withTrafficSource(String trafficSource) {
        this.trafficSource = trafficSource;
        return this;
    }

    public CreatePaymentPage withTransactionSource(String transactionSource) {
        this.transactionSource = transactionSource;
        return this;
    }

    public CreatePaymentPage withPurchaseCountry(String purchaseCountry) {
        this.purchaseCountry = purchaseCountry;
        return this;
    }

    public CreatePaymentPage withGeoCountry(String geoCountry) {
        this.geoCountry = geoCountry;
        return this;
    }

    public CreatePaymentPage withGeoCity(String geoCity) {
        this.geoCity = geoCity;
        return this;
    }

    public CreatePaymentPage withLanguage(String language) {
        this.language = language;
        return this;
    }

    public CreatePaymentPage withWebsite(String website) {
        this.website = website;
        return this;
    }

    public CreatePaymentPage withOrderMetadata(Map<String, String> orderMetadata) {
        this.orderMetadata = orderMetadata;
        return this;
    }

    public CreatePaymentPage withSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
        return this;
    }

    public CreatePaymentPage withFailUrl(String failUrl) {
        this.failUrl = failUrl;
        return this;
    }

    public CreatePaymentPage withPublicName(String publicName) {
        this.publicName = publicName;
        return this;
    }

    public CreatePaymentPage withOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
        return this;
    }

    public CreatePaymentPage withPaymentMethods(String[] paymentMethods) {
        this.paymentsMethods = paymentMethods;
        return this;
    }

    public CreatePaymentPage withButtonFontColor(String buttonFontColor) {
        this.buttonFontColor = buttonFontColor;
        return this;
    }

    public CreatePaymentPage withButtonColor(String buttonColor) {
        this.buttonColor = buttonColor;
        return this;
    }

    public CreatePaymentPage withFontName(String fontName) {
        this.fontName = fontName;
        return this;
    }

    public CreatePaymentPage withCardholderVisible(boolean isCardholderVisible) {
        this.isCardholderVisible = isCardholderVisible;
        return this;
    }

    public CreatePaymentPage withTermsUrl(String termsUrl) {
        this.termsUrl = termsUrl;
        return this;
    }

    public CreatePaymentPage withBackUrl(String backUrl) {
        this.backUrl = backUrl;
        return this;
    }

    public String build() {
        return createPaymentPage(
                orderId != null ? orderId : String.valueOf(UUID.randomUUID()),
                amount != null ? amount : 1,
                currency != null ? currency : "USD",
                orderDescription != null ? orderDescription : "Simple order description as required field",
                orderItems != null ? orderItems: null,
                orderDate != null ? orderDate: null,
                orderNumber != null ? orderNumber : null,
                type != null ? type : null,
                settleInterval != null ? settleInterval : null,
                retryAttempt != null ? retryAttempt : null,
                force3ds != null ? force3ds : false,
                googlePayAllowedAuthMethods != null ? googlePayAllowedAuthMethods : null,
                customerDateOfBirth != null ? customerDateOfBirth : null,
                customerEmail != null ? customerEmail : null,
                customerFirstName != null ? customerFirstName : null,
                customerLastName != null ? customerLastName : null,
                customerPhone != null ? customerPhone : null,
                trafficSource != null ? trafficSource : null,
                transactionSource != null? transactionSource : null,
                purchaseCountry != null ? purchaseCountry : null,
                geoCountry != null ? geoCountry : null,
                geoCity != null ? geoCity : null,
                language != null ? language : null,
                website != null ? website : null,
                orderMetadata != null ? orderMetadata : null,
                successUrl != null ? successUrl : null,
                failUrl != null ? failUrl : null,
                publicName != null ? publicName : "Required public name",
                orderTitle != null ? orderTitle : null,
                paymentsMethods != null ? paymentsMethods : null,
                buttonFontColor != null ? buttonFontColor : null,
                buttonColor != null ? buttonColor: null,
                fontName != null ? fontName : null,
                isCardholderVisible != null ? isCardholderVisible : false,
                termsUrl != null ? termsUrl : null,
                backUrl != null ? backUrl : null
        );
    }

    public String createPaymentPage(String orderId, int amount, String currency, String orderDescription, String orderItems, String orderDate, Integer orderNumber, String type, Integer settleInterval, Integer retryAttempt, Boolean force3ds, String[] googlePayAllowedAuthMethods, String customerDateOfBirth, String customerEmail, String customerFirstName, String customerLastName, String customerPhone, String trafficSource, String transactionSource, String purchaseCountry, String geoCountry, String geoCity, String language, String website, Map<String, String> orderMetadata, String successUrl, String failUrl, String publicName, String orderTitle, String[] paymentsMethods, String buttonFontColor, String buttonColor, String fontName, Boolean isCardholderVisible, String termsUrl, String backUrl) {
        HttpClient client = HttpClientFactory.create();

        JsonObject requestBodyObject = getJsonBody(
                orderId,
                amount,
                currency,
                orderDescription,
                orderItems,
                orderDate,
                orderNumber,
                type,
                settleInterval,
                retryAttempt,
                force3ds,
                googlePayAllowedAuthMethods,
                customerDateOfBirth,
                customerEmail,
                customerFirstName,
                customerLastName,
                customerPhone,
                trafficSource,
                transactionSource,
                purchaseCountry,
                geoCountry,
                geoCity,
                language,
                website,
                orderMetadata,
                successUrl,
                failUrl,
                publicName,
                orderTitle,
                paymentsMethods,
                buttonFontColor,
                buttonColor,
                fontName,
                isCardholderVisible,
                termsUrl,
                backUrl
        );
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
            paymentPageUrl = JsonParser.parseString(body).getAsJsonObject().get("url").getAsString();
        }
        catch (IOException | InterruptedException e) {
            System.out.println("Post request is failed. See exception message below.");
            System.out.println(e.getMessage());
        }

        return paymentPageUrl;
    }

    private JsonObject getJsonBody(String orderId, int amount, String currency, String orderDescription, String orderItems, String orderDate, Integer orderNumber, String type, Integer settleInterval, Integer retryAttempt, Boolean force3ds, String[] googlePayAllowedAuthMethods, String customerDateOfBirth, String customerEmail, String customerFirstName, String customerLastName, String customerPhone, String trafficSource, String transactionSource, String purchaseCountry, String geoCountry, String geoCity, String language, String website, Map<String, String> orderMetadata, String successUrl, String failUrl, String publicName, String orderTitle, String[] paymentsMethods, String buttonFontColor, String buttonColor, String fontName, Boolean isCardholderVisible, String termsUrl, String backUrl) {
        Map<String, Object> orderAttributes = new LinkedHashMap<>();
        Map<String, Object> pageCustomizationAttributes = new LinkedHashMap<>();

        orderAttributes.put("order_id", orderId);
        orderAttributes.put("amount", amount);
        orderAttributes.put("currency", currency);
        orderAttributes.put("order_description", orderDescription);
        orderAttributes.put("order_items", orderItems);
        orderAttributes.put("order_date", orderDate);
        orderAttributes.put("order_number", orderNumber);
        orderAttributes.put("type", type);
        orderAttributes.put("settle_interval", settleInterval);
        orderAttributes.put("retry_attempt", retryAttempt);
        orderAttributes.put("force3ds", force3ds);
        orderAttributes.put("google_pay_allowed_auth_methods", googlePayAllowedAuthMethods);
        orderAttributes.put("customer_date_of_birth", customerDateOfBirth);
        orderAttributes.put("customer_email", customerEmail);
        orderAttributes.put("customer_first_name", customerFirstName);
        orderAttributes.put("customer_last_name", customerLastName);
        orderAttributes.put("customer_phone", customerPhone);
        orderAttributes.put("traffic_source", trafficSource);
        orderAttributes.put("transaction_source", transactionSource);
        orderAttributes.put("purchase_country", purchaseCountry);
        orderAttributes.put("geo_country", geoCountry);
        orderAttributes.put("geo_city", geoCity);
        orderAttributes.put("language", language);
        orderAttributes.put("website", website);
        orderAttributes.put("order_metadata", orderMetadata);
        orderAttributes.put("success_url", successUrl);
        orderAttributes.put("fail_url", failUrl);

        pageCustomizationAttributes.put("public_name", publicName);
        pageCustomizationAttributes.put("order_title", orderTitle);
        pageCustomizationAttributes.put("order_description", orderDescription);
        pageCustomizationAttributes.put("payment_methods", paymentsMethods);
        pageCustomizationAttributes.put("button_font_color", buttonFontColor);
        pageCustomizationAttributes.put("button_color", buttonColor);
        pageCustomizationAttributes.put("font_name", fontName);
        pageCustomizationAttributes.put("is_cardholder_visible", isCardholderVisible);
        pageCustomizationAttributes.put("terms_url", termsUrl);
        pageCustomizationAttributes.put("back_url", backUrl);

        JsonObject orderJson = new Gson().toJsonTree(orderAttributes).getAsJsonObject();
        JsonObject pageCustomJson = new Gson().toJsonTree(pageCustomizationAttributes).getAsJsonObject();
        JsonObject commonJson = new JsonObject();
        commonJson.add("order", orderJson);
        commonJson.add("page_customization", pageCustomJson);
        return commonJson;
    }

}
