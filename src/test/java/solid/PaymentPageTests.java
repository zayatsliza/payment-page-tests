package solid;

import enums.Currency;
import rest_api.services.payment_page_service.PaymentPageBuilder;
import rest_api.services.payment_status_service.PaymentStatusBuilder;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ui.page_objects.PaymentPage;
import ui.page_objects.PaymentStatusPage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import static com.codeborne.selenide.Selenide.open;
import static java.util.Map.entry;

public class PaymentPageTests extends BaseTest {

    private String paymentPageUrl;
    private String orderId;

    private PaymentPage paymentPage = new PaymentPage();
    private PaymentStatusPage paymentStatusPage = new PaymentStatusPage();
    private PaymentPageBuilder paymentPageBuilder  = new PaymentPageBuilder("https://payment-page.solidgate.com", PUBLIC_KEY, SECRET_KEY);
    private PaymentStatusBuilder paymentStatusBuilder = new PaymentStatusBuilder("https://pay.solidgate.com", PUBLIC_KEY, SECRET_KEY);

    @BeforeTest
    private void pageInit() {
        String[] googlePayAuthMethods = {"PAN_ONLY"};
        orderId = String.valueOf(UUID.randomUUID());
        paymentPageUrl = paymentPageBuilder
                .withOrderId(orderId)
                .withAmount(1023)
                .withCurrency(Currency.UAH.name())
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
        paymentPage.typeCardNumber("4067429974719265")
                .typeCardExpiryDate("12/26")
                .typeCardCvv("123")
                .typeCardHolder("Bob Brown")
                .clickSubmitButton();
        paymentStatusPage.statusShouldBeVisible();
        paymentStatusPage.successTitleShouldBeVisible();
        Map<String, String> statusData = paymentStatusBuilder.withOrderId(orderId).build();
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
