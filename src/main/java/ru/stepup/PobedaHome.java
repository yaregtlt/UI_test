package ru.stepup;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

public class PobedaHome
{

    private SelenideElement pageHeader = $("meta[property=\"og:title\"]");

    private SelenideElement pageLogo = $("a[class=\"dp-1rojma8-root-root-root\"] > img");

    public String getLoadedPageURL() {
        return url();
    }

    @Step("Проверить заголовок страницы")
    public String getPobedaHomeTitleText() {
        return pageHeader.attr("content");
    }

    @Step("Проверить присутствие логотипа")
    public boolean isPobedaHomeLogoPresent() {
        return pageLogo.isDisplayed();
    }

}
