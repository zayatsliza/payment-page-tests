package http;

import enums.Currency;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class PaymentPageBuilder {
    private String upstreamURL;
    private String publicKey;
    private String secretKey;

    protected String orderId = String.valueOf(UUID.randomUUID());
    protected Integer amount = 1;
    protected String currency = Currency.USD.name();
    protected String orderDescription = "Simple order description as required field";
    protected String orderItems;
    protected String orderDate;
    protected Integer orderNumber;
    protected String type;
    protected Integer settleInterval;
    protected Integer retryAttempt;
    protected Boolean force3ds;
    protected String[] googlePayAllowedAuthMethods;
    protected String customerDateOfBirth;
    protected String customerEmail;
    protected String customerFirstName;
    protected String customerLastName;
    protected String customerPhone;
    protected String trafficSource;
    protected String transactionSource;
    protected String purchaseCountry;
    protected String geoCountry;
    protected String geoCity;
    protected String language;
    protected String website;
    protected Map<String, String> orderMetadata;
    protected String successUrl;
    protected String failUrl;

    protected String publicName = "Required public name";
    protected String orderTitle;
    protected String[] paymentsMethods;
    protected String buttonFontColor;
    protected String buttonColor;
    protected String fontName;
    protected Boolean isCardholderVisible;
    protected String termsUrl;
    protected String backUrl;

    public PaymentPageBuilder(String url, String pbKey, String sbKey) {
        this.upstreamURL = url;
        this.publicKey = pbKey;
        this.secretKey = sbKey;
    }

    public PaymentPageBuilder withOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public PaymentPageBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public PaymentPageBuilder withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public PaymentPageBuilder withOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
        return this;
    }

    public PaymentPageBuilder withOrderItems(String... orderItems) {
        this.orderItems = Arrays.stream(orderItems).collect(Collectors.joining(", "));
        return this;
    }

    public PaymentPageBuilder withOrderDate(LocalDateTime orderDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateFormat = orderDate.format(formatter);
        this.orderDate = dateFormat;
        return this;
    }

    public PaymentPageBuilder withOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public PaymentPageBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public PaymentPageBuilder withSettleInterval(int settleInterval) {
        this.settleInterval = settleInterval;
        return this;
    }

    public PaymentPageBuilder withRetryAttempt(int retryAttempt) {
        this.retryAttempt = retryAttempt;
        return this;
    }

    public PaymentPageBuilder withForce3ds(boolean force3ds) {
        this.force3ds = force3ds;
        return this;
    }

    public PaymentPageBuilder withGooglePayAllowedAuthMethods(String[] googlePayAllowedAuthMethods) {
        this.googlePayAllowedAuthMethods = googlePayAllowedAuthMethods;
        return this;
    }

    public PaymentPageBuilder withCustomerDateOfBirth(LocalDate dateOfBirth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.customerDateOfBirth = dateOfBirth.format(formatter);
        return this;
    }

    public PaymentPageBuilder withCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
        return this;
    }

    public PaymentPageBuilder withCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
        return this;
    }
    public PaymentPageBuilder withCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
        return this;
    }

    public PaymentPageBuilder withCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
        return this;
    }

    public PaymentPageBuilder withTrafficSource(String trafficSource) {
        this.trafficSource = trafficSource;
        return this;
    }

    public PaymentPageBuilder withTransactionSource(String transactionSource) {
        this.transactionSource = transactionSource;
        return this;
    }

    public PaymentPageBuilder withPurchaseCountry(String purchaseCountry) {
        this.purchaseCountry = purchaseCountry;
        return this;
    }

    public PaymentPageBuilder withGeoCountry(String geoCountry) {
        this.geoCountry = geoCountry;
        return this;
    }

    public PaymentPageBuilder withGeoCity(String geoCity) {
        this.geoCity = geoCity;
        return this;
    }

    public PaymentPageBuilder withLanguage(String language) {
        this.language = language;
        return this;
    }

    public PaymentPageBuilder withWebsite(String website) {
        this.website = website;
        return this;
    }

    public PaymentPageBuilder withOrderMetadata(Map<String, String> orderMetadata) {
        this.orderMetadata = orderMetadata;
        return this;
    }

    public PaymentPageBuilder withSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
        return this;
    }

    public PaymentPageBuilder withFailUrl(String failUrl) {
        this.failUrl = failUrl;
        return this;
    }

    public PaymentPageBuilder withPublicName(String publicName) {
        this.publicName = publicName;
        return this;
    }

    public PaymentPageBuilder withOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
        return this;
    }

    public PaymentPageBuilder withPaymentMethods(String[] paymentMethods) {
        this.paymentsMethods = paymentMethods;
        return this;
    }

    public PaymentPageBuilder withButtonFontColor(String buttonFontColor) {
        this.buttonFontColor = buttonFontColor;
        return this;
    }

    public PaymentPageBuilder withButtonColor(String buttonColor) {
        this.buttonColor = buttonColor;
        return this;
    }

    public PaymentPageBuilder withFontName(String fontName) {
        this.fontName = fontName;
        return this;
    }

    public PaymentPageBuilder withCardholderVisible(boolean isCardholderVisible) {
        this.isCardholderVisible = isCardholderVisible;
        return this;
    }

    public PaymentPageBuilder withTermsUrl(String termsUrl) {
        this.termsUrl = termsUrl;
        return this;
    }

    public PaymentPageBuilder withBackUrl(String backUrl) {
        this.backUrl = backUrl;
        return this;
    }

    public String build() {
        return new CreatePaymentPage(upstreamURL, publicKey, secretKey)
                .createPaymentPage(this);
    }

}
