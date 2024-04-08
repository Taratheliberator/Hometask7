package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

//стартовая страница
@Slf4j
public class StartPage extends AbstractPage {
    private static final By AVAILABLE_EXAMPLES_LABEL = By.xpath("//h1[contains(text(), 'Welcome to the-internet')]");
    private static final By CHECKBOX = By.xpath("//a[contains(text(), 'Checkboxes')]");
    private static final By DROPDOWN = By.xpath("//a[contains(text(), 'Dropdown')]");
    private static final By DISAPPEARING_ELEMENTS = By.xpath("//a[contains(text(), 'Disappearing Elements')]");
    private static final By INPUTS = By.xpath("//a[contains(text(), 'Inputs')]");



    @Step("Переходим на страницу Checkboxes")
    public CheckboxPage goToCheckboxPage() {
        $(CHECKBOX).click();
        return new CheckboxPage();
    }

    @Step("Переходим на страницу Dropdown")
    public DropdownPage goToDropdownPage() {
        $(DROPDOWN).click();
        return new DropdownPage();
    }

    @Step("Переходим на страницу Disappearing Elements")
    public DisappearingElementsPage goToDisappearingElementsPage() {
        $(DISAPPEARING_ELEMENTS).click();
        return new DisappearingElementsPage();
    }

    @Step("Переходим на страницу Inputs")
    public InputsPage goToInputsPage() {
        $(INPUTS).click();
        return new InputsPage();
    }


    @Override
    protected void initComponents() {
        $(AVAILABLE_EXAMPLES_LABEL).shouldBe(Condition.visible);
    }
}
