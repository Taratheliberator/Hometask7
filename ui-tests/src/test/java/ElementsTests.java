
import assertions.PageAssert;
import config.Configuration;

import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.*;
import java.lang.invoke.MethodHandles;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ElementsTests {
    private Configuration config;

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private StartPage startPage;


@BeforeEach
public void setUp() {
    config = ConfigFactory.create(Configuration.class);
    startPage = new StartPage();
    startPage.openPage(config.baseUrl());

}
    @Test
    @Story("Перейти на страницу Checkboxes. Выделить первый чекбокс, снять выделение со второго чекбокса. Вывести в консоль состояние атрибута checked для каждого чекбокса.")
    void checkboxTest() {
        CheckboxPage checkboxPage = startPage.goToCheckboxPage()
                .setFirstCheckbox(true)
                .setSecondCheckbox(false);

        // Используем PageAssert для проверки состояния первого чекбокса
        PageAssert.assertThat(checkboxPage.getFirstCheckboxElement()).isSelected();

        // Используем PageAssert для проверки, что второй чекбокс не выбран
        PageAssert.assertThat(checkboxPage.getSecondCheckboxElement()).isNotSelected();

        log.info("Проверка состояний чекбоксов выполнена успешно");
    }

    @Test
    @Story("Перейти на страницу Dropdown. Выбрать первую опцию, вывести в консоль текущий текст элемента dropdown, выбрать вторую опцию, вывести в консоль текущий текст элемента dropdown.")
    void dropdownTest() {
        DropdownPage dropdownPage = startPage.goToDropdownPage()
                .selectOptionInDropdown("Option 1");
        PageAssert.assertThat($(dropdownPage.DROPDOWN)).hasSelectedOptionText("Option 1");
        log.info("Текущий текст Dropdown'а - " + dropdownPage.getCurrentTextInDropdown());

        dropdownPage.selectOptionInDropdown("Option 2");
        PageAssert.assertThat($(dropdownPage.DROPDOWN)).hasSelectedOptionText("Option 2");
        log.info("Текущий текст Dropdown'а - " + dropdownPage.getCurrentTextInDropdown());
    }

    @Test
    @Story("Перейти на страницу Disappearing Elements. Добиться отображения 5 элементов, максимум за 10 попыток, если нет, провалить тест с ошибкой.")
    void disappearingElementsTest() {
        DisappearingElementsPage disappearingElementsPage = startPage.goToDisappearingElementsPage();
        int i = 0;
        while (i < 10) {
            if (disappearingElementsPage.getAmountOfElements() == 5)
                break;
            refresh();
            i++;
        }
       //log.info("Для отображения 5 элементов понадобилоась %s попыток".formatted(i));
        assertThat(i).as("За 10 попыток не добились отображения 5 элементов").isLessThan(10);
    }

    @Test
    void inputsTest() {

        int randNumber = new Random().nextInt(10000) + 1;
        InputsPage inputsPage = startPage.goToInputsPage()
                .inputNumber(randNumber);
        // Проверяем, что поле ввода содержит ожидаемое значение
        PageAssert.assertThat(inputsPage.getNumberInputElement()).hasValue(String.valueOf(randNumber));

        log.info("Значение элемента Input - " + inputsPage.getValue());
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

}
