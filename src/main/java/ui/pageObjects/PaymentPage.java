package ui.pageObjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.enabled;

public class PaymentPage extends BasePage {

    private final SelenideElement cardNumberField = $("input[name='cardNumber']");
    private final SelenideElement cardExpiryDateField = $("input[name='cardExpiryDate']");
    private final SelenideElement cardCvvField = $("input[name='cardCvv']");
    private final SelenideElement cardHolderField = $("input[name='cardHolder']");
    private final SelenideElement submitButton = $("button[data-testid='submit']");

    public PaymentPage typeCardNumber(String cardNumber) {
        typeIntoField(cardNumberField, cardNumber);
        return this;
    }

    public PaymentPage typeCardExpiryDate(String expiryDate) {
        typeIntoField(cardExpiryDateField, expiryDate);
        return this;
    }

    public PaymentPage typeCardCvv(String cvv) {
        typeIntoField(cardCvvField, cvv);
        return this;
    }

    public PaymentPage typeCardHolder(String cardHolder) {
        typeIntoField(cardHolderField, cardHolder);
        return this;
    }

    public void clickSubmitButton() {
        submitButton.shouldBe(enabled).click();
    }

}
