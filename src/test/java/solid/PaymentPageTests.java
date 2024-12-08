package solid;

import http.CreatePaymentPage;
import http.PaymentStatusHelper;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ui.pageObjects.PaymentPage;
import ui.pageObjects.PaymentStatusPage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import static com.codeborne.selenide.Selenide.open;
import static java.util.Map.entry;

public class PaymentPageTests {

    private String paymentPageUrl;
    private String orderId;

    private static final String PUBLIC_KEY = System.getProperty("PUBLIC_KEY") != null ? System.getProperty("PUBLIC_KEY") : "api_pk_5d778083_145f_4ce9_b16c_ef3c35ab8e9a";
    private static final String SECRET_KEY = System.getProperty("SECRET_KEY") != null ? System.getProperty("SECRET_KEY") : "api_sk_cc75abdd_2b80_4986_8b23_9c5399f307ca";


    private PaymentPage paymentPage = new PaymentPage();
    private PaymentStatusPage paymentStatusPage = new PaymentStatusPage();
    private CreatePaymentPage createPaymentPage = new CreatePaymentPage("https://payment-page.solidgate.com", PUBLIC_KEY, SECRET_KEY);
    private PaymentStatusHelper paymentStatusHelper = new PaymentStatusHelper("https://pay.solidgate.com", PUBLIC_KEY, SECRET_KEY);

    @BeforeTest
    private void pageInit() {
        String[] googlePayAuthMethods = {"PAN_ONLY"};
        orderId = String.valueOf(UUID.randomUUID());
        paymentPageUrl = createPaymentPage
                .withOrderId(orderId)
                .withAmount(1023)
                .withCurrency("UAH")
                .withOrderDescription("Tratata order")
                .withOrderItems("item1 x 1", "item2 x 3")
                .withOrderDate(LocalDateTime.now())
                .withOrderNumber(123)
                .withType("auth")
                .withSettleInterval(120)
                .withRetryAttempt(100)
                .withForce3ds(false)
                .withGooglePayAllowedAuthMethods(googlePayAuthMethods)
                .withCustomerDateOfBirth(LocalDate.of(2001, 10, 28))
                .withCustomerEmail("test@gmail.com")
                .withCustomerFirstName("Bob")
                .withCustomerLastName("Brown")
                .withCustomerPhone("1234567")
                .withTrafficSource("google_ads")
                .withTransactionSource("main_menu")
                .withPurchaseCountry("FRA")
                .withGeoCountry("FRA")
                .withGeoCity("Paris")
                .withLanguage("en")
                .withWebsite("some-website.com")
                .withOrderMetadata(Map.ofEntries(entry("coupon_code", "FR2024"), entry("partner_id", "123989")))
//                .withSuccessUrl("success.com")
//                .withFailUrl("fail.com")
                .withPublicName("Public name test")
                .withOrderTitle("Precious Order")
                .withPaymentMethods(new String[]{"paypal"})
                .withButtonFontColor("#FFFFFF")
                .withButtonColor("#00816A")
                .withFontName("Open Sans")
                .withCardholderVisible(true)
                .withTermsUrl("https://solidgate.com/terms")
                .withBackUrl("https://solidgate.com")
                .build();
        System.out.printf("Testing on payment page: %s with order_id - %s.\n", paymentPageUrl, orderId);
        open(paymentPageUrl);
    }

    @Test
    void paymentTransactionSuccess() {
        paymentPage.typeCardNumber("4067429974719265");
        paymentPage.typeCardExpiryDate("12/26");
        paymentPage.typeCardCvv("123");
        paymentPage.typeCardHolder("Bob Brown");
        paymentPage.clickSubmitButton();
        paymentStatusPage.statusShouldBeVisible();
        paymentStatusPage.successTitleShouldBeVisible();
        Map<String, String> statusData = paymentStatusHelper.withOrderId(orderId).build();
        String expectedAmount = formatAmount(statusData.get("amount"));
        paymentStatusPage.priceShouldBe(expectedAmount);
        paymentStatusPage.currencyShouldBe(statusData.get("currency"));
    }

    private String formatAmount(String amount) {
        while (amount.length() <= 2) {
            amount = "0" + amount;
        }

        String result = amount.substring(0, amount.length() - 2) + "." + amount.substring(amount.length() - 2);
        return result;
    }

}
