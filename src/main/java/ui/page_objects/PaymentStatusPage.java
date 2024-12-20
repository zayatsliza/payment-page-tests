package ui.page_objects;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class PaymentStatusPage extends BasePage {
    private final SelenideElement statusPage = $("div[class*='PaymentPage_status_']");
    private final SelenideElement statusSuccessTitle = $("[class*='StatusSuccess_title']");
    private final SelenideElement priceView = $("div[data-testid*='status-order-price'] div[data-testid='price_major']");
    public void statusShouldBeVisible() {
        statusPage.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void priceShouldBe(String price) {
        fieldShouldContainText(priceView, price);
    }

    public void currencyShouldBe(String currency) {
        fieldShouldContainText(priceView, currency);
    }

    public void successTitleShouldBeVisible() {
        statusSuccessTitle.shouldBe(visible);
    }

}
