package ru.stepup;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class InformationEntity extends PobedaHome{

    private SelenideElement informationMenu =
        $(By.xpath("//a[contains(@class, 'dp-1g2i3b6-root-root-root') and text()='Информация']"));

    private SelenideElement popupMenu = $("div[class=\"dp-1mbpq22-root\"]");

    private SelenideElement popupHeaderFlightPreparation =
        $(By.xpath("//a[contains(@class, 'dp-17i9q9s-root-root') and text()='Подготовка к полёту']"));

    private SelenideElement popupHeaderUsefulInfo =
        $(By.xpath("//a[contains(@class, 'dp-17i9q9s-root-root') and text()='Полезная информация']"));

      private SelenideElement popupHeaderAbout =
        $(By.xpath("//a[contains(@class, 'dp-17i9q9s-root-root') and text()='О компании']"));

    public void moveToInformation() {
        informationMenu.hover();
    }

    @Step("Проверить, что выподающее меню отобразилось")
    public boolean isPopUpMenuPresent() {
        return popupMenu.isDisplayed();
    }

    @Step("В выподающем меню присутствует \"Подготовка к полёту\"")
    public String getPobedaHomePopupHeaderFlightPreparation() {
        return this.popupHeaderFlightPreparation.getText();
    }

    @Step("В выподающем меню присутствует \"Полезная информация\"")
    public String getPobedaHomePopupHeaderUsefulInfo() {
        return this.popupHeaderUsefulInfo.getText();
    }

    @Step("В выподающем меню присутствует \"О компании\"")
    public String getPobedaHomePopupHeaderAbout() {
        return popupHeaderAbout.getText();
    }

}
