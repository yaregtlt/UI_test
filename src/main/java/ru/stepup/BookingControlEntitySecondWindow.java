package ru.stepup;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class BookingControlEntitySecondWindow extends BookingControlEntity{

    private SelenideElement bookingNumberField = $("[placeholder=\"Номер брони или билета\"]");

    private SelenideElement surnameField = $("[placeholder=\"Фамилия\"]");

    private SelenideElement agreeCheckBox = $("[ng-bind-html=\"'web.searchOrderAgree' | aliasStatic\"]");

    private SelenideElement findButton =
        $("[class=\"btn btn_search btn_search--order btn_formSearch btn_formSearch_js\"]");

    private SelenideElement errorMessage = $("[ng-if=\"vm.errorMessage\"]");

    public boolean isNumberAndSurnameFieldsPresent() {
        bookingNumberField.shouldBe(visible);
        return bookingNumberField.isDisplayed() && surnameField.isDisplayed();
    }

    public void setCheckBox() {
        agreeCheckBox.click();
    }

    public void clickFindButton() {
        findButton.click();
    }

    public boolean isErrorMessagePresent() {
        return errorMessage.isDisplayed();
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

}
