package ru.stepup;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindTicketEntity extends PobedaHome{
    public FindTicketEntity(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "div[class=\"dp-buadeg-root-card\"]")
    WebElement findTicketField;

    @FindBy(css = "div.dp-1dr6zbu-root > input.dp-zu3w2f-root-control[placeholder=\"Откуда\"]")
    WebElement findTicketFrom;

    @FindBy(css = "div[class=\"dp-20s1up-root-suggestionName\"]")
    WebElement findTicketFromFirstResult;

    @FindBy(css = "div.dp-1dr6zbu-root > input.dp-zu3w2f-root-control[placeholder=\"Куда\"]")
    WebElement findTicketTo;

    @FindBy(css = "div[class=\"dp-20s1up-root-suggestionName\"]")
    WebElement findTicketToFirstResult;

    @FindBy(xpath = "//div[@class='dp-1dr6zbu-root' and ./input[@class='dp-zu3w2f-root-control' and @placeholder='Туда']]")
    WebElement findTicketDateThere;

    @FindBy(css = "div.dp-1dr6zbu-root > input.dp-zu3w2f-root-control[placeholder=\"Обратно\"]")
    WebElement findTicketDateBack;

    @FindBy(css = "button[class=\"dp-k64vy3-root-root-root\"]")
    WebElement findTicketFindButton;

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
//        findTicketFromFirstResult.click();
    }

    public void setFindTicketToField(String city) {
        findTicketTo.sendKeys(Keys.CONTROL + "a");
        findTicketTo.sendKeys(Keys.DELETE);
        findTicketTo.sendKeys(city);
        findTicketTo.sendKeys(Keys.ARROW_DOWN);
        findTicketTo.sendKeys(Keys.ENTER);
//        findTicketToFirstResult.click();
    }

    public void clickFindTicketFindButton() {
        findTicketFindButton.click();
    }

    public boolean isFindTicketDateThereBorderColoredWith(String color) {
        String borderColor = findTicketDateThere.getCssValue("border-color");
        return borderColor.equals(color);
    }

}
