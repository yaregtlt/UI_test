package ru.stepup;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Selenide.$;

public class FindTicketEntity extends PobedaHome{

    private SelenideElement findTicketField = $("div[class=\"dp-buadeg-root-card\"]");

    private SelenideElement findTicketFrom =
            $("div.dp-1dr6zbu-root > input.dp-zu3w2f-root-control[placeholder=\"Откуда\"]");

    private SelenideElement findTicketTo =
        $("div.dp-1dr6zbu-root > input.dp-zu3w2f-root-control[placeholder=\"Куда\"]");

    private SelenideElement findTicketToFirstResult = $("div[class=\"dp-20s1up-root-suggestionName\"]");

    SelenideElement findTicketDateThere =
        $(By.xpath("//div[@class='dp-1dr6zbu-root' and ./input[@class='dp-zu3w2f-root-control' and @placeholder='Туда']]"));

    private SelenideElement findTicketDateBack =
        $("div.dp-1dr6zbu-root > input.dp-zu3w2f-root-control[placeholder=\"Обратно\"]");

    private SelenideElement findTicketFindButton = $("button[class=\"dp-k64vy3-root-root-root\"]");

    public boolean isFindTicketPresent() {
        return findTicketField.isDisplayed();
    }

    public boolean isFindTicketFromFieldPresent() {
        return findTicketFrom.isDisplayed();
    }

    public boolean isFindTicketToFieldPresent() {
        return findTicketTo.isDisplayed();
    }

    public boolean isFindTicketDateTherePresent() {
        return findTicketDateThere.isDisplayed();
    }

    public boolean isFindTicketDateBackPresent() {
        return findTicketDateBack.isDisplayed();
    }

    public void setFindTicketFromField(String city) {
        findTicketFrom.sendKeys(Keys.CONTROL + "a");
        findTicketFrom.sendKeys(Keys.DELETE);
        findTicketFrom.sendKeys(city);
        findTicketFrom.sendKeys(Keys.ARROW_DOWN);
        findTicketFrom.sendKeys(Keys.ENTER);
    }

    public void setFindTicketToField(String city) {
        findTicketTo.sendKeys(Keys.CONTROL + "a");
        findTicketTo.sendKeys(Keys.DELETE);
        findTicketTo.sendKeys(city);
        findTicketTo.sendKeys(Keys.ARROW_DOWN);
        findTicketTo.sendKeys(Keys.ENTER);
    }

    public void clickFindTicketFindButton() {
        findTicketFindButton.click();
    }

    public boolean isFindTicketDateThereBorderColoredWith(String color) {
        findTicketDateThere.shouldHave(cssValue("border-color", color));
        String borderColor = findTicketDateThere.getCssValue("border-color");
        return borderColor.equals(color);
    }

}
