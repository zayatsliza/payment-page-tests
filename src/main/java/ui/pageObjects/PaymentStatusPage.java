package ui.pageObjects;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.text;

public class PaymentStatusPage {
    private SelenideElement statusPage = $("div[class*='PaymentPage_status_']");
    private SelenideElement statusSuccessTitle = $("[class*='StatusSuccess_title']");
    private SelenideElement priceView = $("div[data-testid*='status-order-price'] div[data-testid='price_major']");
    public void statusShouldBeVisible() {
        statusPage.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void priceShouldBe(String price) {
        priceView.shouldHave(text(price));
    }

    public void currencyShouldBe(String currency) {
        priceView.shouldHave(text(currency));
    }

    public void successTitleShouldBeVisible() {
        statusSuccessTitle.shouldBe(visible);
    }

}
