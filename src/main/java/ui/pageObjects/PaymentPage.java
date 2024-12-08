package ui.pageObjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.enabled;

public class PaymentPage {

    private SelenideElement cardNumberField = $("input[name='cardNumber']");
    private SelenideElement cardExpiryDateField = $("input[name='cardExpiryDate']");
    private SelenideElement cardCvvField = $("input[name='cardCvv']");
    private SelenideElement cardHolderField = $("input[name='cardHolder']");
    private SelenideElement submitButton = $("button[data-testid='submit']");

    public void typeCardNumber(String cardNumber) {
        cardNumberField.shouldBe(visible).type(cardNumber);
    }

    public void typeCardExpiryDate(String expiryDate) {
        cardExpiryDateField.shouldBe(visible).type(expiryDate);
    }

    public void typeCardCvv(String cvv) {
        cardCvvField.shouldBe(visible).type(cvv);
    }

    public void typeCardHolder(String cardHolder) {
        cardHolderField.shouldBe(visible).type(cardHolder);
    }

    public void clickSubmitButton() {
        submitButton.shouldBe(enabled).click();
    }

}
