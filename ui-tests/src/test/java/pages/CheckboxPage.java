package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CheckboxPage extends AbstractPage {
    private static final By PAGE_LABEL = By.xpath("//h3[contains(text(), 'Checkboxes')]");
    public static final By FIRST_CHECKBOX = By.cssSelector("input[type='checkbox']:nth-child(1)");
    public static final By SECOND_CHECKBOX = By.cssSelector("input[type='checkbox']:nth-child(3)");

    @Step("Устанавливаем значение {selected} у первого чекбокса")
    public CheckboxPage setFirstCheckbox(boolean selected) {
        $(FIRST_CHECKBOX).setSelected(selected);
        return this;
    }

    @Step("Устанавливаем значение {selected} у второго чекбокса")
    public CheckboxPage setSecondCheckbox(boolean selected) {
        $(SECOND_CHECKBOX).setSelected(selected);
        return this;
    }

    @Step("Получаем элемент первого чекбокса")
    public SelenideElement getFirstCheckboxElement() {
        return $(FIRST_CHECKBOX);
    }

    @Step("Получаем элемент второго чекбокса")
    public SelenideElement getSecondCheckboxElement() {
        return $(SECOND_CHECKBOX);
    }

    @Override
    protected void initComponents() {
        $(PAGE_LABEL).shouldBe(Condition.visible);
    }
}