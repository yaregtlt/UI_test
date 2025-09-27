package ru.stepup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookingControlEntitySecondWindow extends BookingControlEntity{
    public BookingControlEntitySecondWindow(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[placeholder=\"Номер брони или билета\"]")
    WebElement bookingNumberField;

    @FindBy(css = "[placeholder=\"Фамилия\"]")
    WebElement surnameField;

    @FindBy(css = "[ng-bind-html=\"'web.searchOrderAgree' | aliasStatic\"]")
    WebElement agreeCheckBox;

    @FindBy(css = "[class=\"btn btn_search btn_search--order btn_formSearch btn_formSearch_js\"]")
    WebElement findButton;

    @FindBy(css = "[ng-if=\"vm.errorMessage\"]")
    WebElement errorMessage;

    public boolean isNumberAndSurnameFieldsPresent() {
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
