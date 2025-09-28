package ru.stepup;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class BookingControlEntitySecondWindow extends BookingControlEntity{

    private SelenideElement bookingNumberField = $("[placeholder=\"Номер брони или билета\"]");

    private SelenideElement surnameField = $("[placeholder=\"Фамилия\"]");

    private SelenideElement agreeCheckBox = $("[ng-bind-html=\"'web.searchOrderAgree' | aliasStatic\"]");

    private SelenideElement findButton =
        $("[class=\"btn btn_search btn_search--order btn_formSearch btn_formSearch_js\"]");

    private SelenideElement errorMessage = $("[ng-if=\"vm.errorMessage\"]");

    @Step("Присутствует поле Номер заказа и Фамилия в новом окне")
    public boolean isNumberAndSurnameFieldsPresent() {
        bookingNumberField.shouldBe(visible);
        return bookingNumberField.isDisplayed() && surnameField.isDisplayed();
    }

    @Step("Установить check box")
    public void setCheckBox() {
        agreeCheckBox.click();
    }

    @Step("Кликнуть кнопку Найти")
    public void clickFindButton() {
        findButton.click();
    }

    @Step("Появилось сообщение об ошибке")
    public boolean isErrorMessagePresent() {
        return errorMessage.isDisplayed();
    }

    @Step("Сообщение об ошибке соответствует тексту: \"Заказ с указанными параметрами не найден\"")
    public String getErrorMessageText() {
        return errorMessage.getText();
    }

}
