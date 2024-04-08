package assertions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.AbstractAssert;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class PageAssert extends AbstractAssert<PageAssert, SelenideElement> {

    // Конструктор
    private PageAssert(SelenideElement actual) {
        super(actual, PageAssert.class);
    }

    // Метод для создания экземпляра класса
    public static PageAssert assertThat(SelenideElement actualElement) {
        return new PageAssert(actualElement);
    }

    // Проверка видимости элемента
    public PageAssert isVisible() {
        isNotNull();

        String errorMessage = String.format("Expected element to be visible but it was not. Element: %s", actual);
        if (!actual.isDisplayed()) {
            failWithMessage(errorMessage);
        }

        return this;
    }

    // Проверка отсутствия элемента
    public PageAssert isHidden() {
        isNotNull();

        String errorMessage = String.format("Expected element to be hidden but it was visible. Element: %s", actual);
        if (actual.isDisplayed()) {
            failWithMessage(errorMessage);
        }

        return this;
    }

    // Проверка текста элемента
    public PageAssert hasText(String expectedText) {
        isNotNull();

        actual.shouldHave(Condition.text(expectedText));

        return this;
    }

    // Проверка, что элемент выбран (для чекбоксов )
    public void isSelected() {
        isNotNull();

        actual.shouldBe(Condition.selected);

    }

    // Проверка, что элемент не выбран
    public void isNotSelected() {
        isNotNull();

        actual.shouldNotBe(Condition.checked);
    }
    public PageAssert hasSelectedOptionText(String expectedText) {
        isNotNull();

        String actualText = actual.getSelectedOption().getText();
        if (!actualText.equals(expectedText)) {
            failWithMessage("Expected the selected option to be '%s' but was '%s'", expectedText, actualText);
        }

        return this;
    }

    public PageAssert hasOptionWithText(String expectedText) {
        isNotNull();

        List<String> optionsTexts = actual.$$("option").texts(); // Получаем список текстов опций
        if (!optionsTexts.contains(expectedText)) { // Проверяем, содержит ли список текст expectedText
            failWithMessage("Expected dropdown to have an option with text '%s' but it does not", expectedText);
        }

        return this;
    }

    public PageAssert hasSize(int expectedSize) {
        isNotNull();

        if (actual.$$x("..").size() != expectedSize) { // Используем $$x для получения коллекции элементов
            failWithMessage("Expected size of elements collection to be '%s' but was '%s'", expectedSize, actual.$$x("..").size());
        }

        return this;
    }

    // Метод для проверки значения атрибута value элемента
    public PageAssert hasValue(String expectedValue) {
        isNotNull();

        String actualValue = actual.getValue();
        if (!actualValue.equals(expectedValue)) {
            failWithMessage("Expected element's value to be '%s' but was '%s'", expectedValue, actualValue);
        }

        return this;
    }



}
