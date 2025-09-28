package ru.stepup;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

public class PobedaHome
{

    private SelenideElement pageHeader = $("meta[property=\"og:title\"]");

    private SelenideElement pageLogo = $("a[class=\"dp-1rojma8-root-root-root\"] > img");

    public String getLoadedPageURL() {
        return url();
    }

    public String getPobedaHomeTitleText() {
        return pageHeader.attr("content");
    }

    public boolean isPobedaHomeLogoPresent() {
        return pageLogo.isDisplayed();
    }

}
