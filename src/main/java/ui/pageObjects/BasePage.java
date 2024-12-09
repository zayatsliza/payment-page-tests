package ui.pageObjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class BasePage {

    protected static void typeIntoField(SelenideElement element, String value) {
        element.shouldBe(visible).type(value);
    }

    protected static void fieldShouldContainText(SelenideElement element, String value) {
        element.shouldBe(visible).shouldHave(text(value));
    }

}
