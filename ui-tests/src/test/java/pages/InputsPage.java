package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class InputsPage extends AbstractPage {
    private static final By NUMBER_INPUT = By.cssSelector("input[type='number']");

    @Step("Получаем SelenideElement поля ввода")
    public SelenideElement getNumberInputElement() {
        return $(NUMBER_INPUT);
    }

    @Step("Получаем значение из поля ввода")
    public String getValue() {
        return getNumberInputElement().getValue();
    }

    @Step("Вводим число {number}")
    public InputsPage inputNumber(int number) {
        getNumberInputElement().setValue(String.valueOf(number));
        return this;
    }

    @Override
    protected void initComponents() {
        getNumberInputElement().should(Condition.visible);
    }
}
