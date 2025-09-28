package ru.stepup;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.switchTo;

public class BookingControlEntity extends PobedaHome{

    private SelenideElement bookingControl =
            $("a.dp-1il0417-root-root[href='/services/booking-management']");

    private SelenideElement bookingTitle = $("h1[class=\"dp-1dsqh26-root\"]");

    private SelenideElement clientSurnameField =
            $("input.dp-zu3w2f-root-control[placeholder=\"Фамилия клиента\"]");

    private SelenideElement orderNumberField =
        $("input.dp-zu3w2f-root-control[placeholder=\"Номер бронирования или билета\"]");

    private SelenideElement findButton = $("button[class=\"dp-18h4yts-root-root-submitBtn\"]");

    public void skrollToBookingControlAndClick() {
        bookingControl.scrollIntoView(true);
        bookingControl.click();
        bookingTitle.shouldBe(visible);
    }

//    public boolean isBookingControlTitlePresent() {
//        bookingTitle.shouldBe(visible);
//        return bookingTitle.isDisplayed();
//    }

    public boolean isOrderNumberPresent() {
        return orderNumberField.isDisplayed();
    }

    public boolean isClientSurnamePresent() {
        return clientSurnameField.isDisplayed();
    }

    public boolean isFindButtonPresent() {
        return findButton.isDisplayed();
    }

    public void setOrderNumberField(String text) {
        orderNumberField.sendKeys(text);
    }

    public void setClientSurnameField(String text) {
        clientSurnameField.sendKeys(text);
    }

    public void clickFindButtonAndGoToNewWindow() {
        findButton.click();
        switchTo().window(1);
    }

}
