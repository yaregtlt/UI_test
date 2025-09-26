package ru.stepup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InformationEntity extends PobedaHome{

    @FindBy(xpath = "//a[contains(@class, 'dp-1g2i3b6-root-root-root') and text()='Информация']")
    WebElement informationMenu;

    @FindBy(css = "div[class=\"dp-1mbpq22-root\"]")
    WebElement popupMenu;

    @FindBy(xpath = "//a[contains(@class, 'dp-17i9q9s-root-root') and text()='Подготовка к полёту']")
    WebElement popupHeaderFlightPreparation;

    @FindBy(xpath = "//a[contains(@class, 'dp-17i9q9s-root-root') and text()='Полезная информация']")
    WebElement popupHeaderUsefulInfo;

    @FindBy(xpath = "//a[contains(@class, 'dp-17i9q9s-root-root') and text()='О компании']")
    WebElement popupHeaderAbout;

    public InformationEntity(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void moveToInformation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(this.informationMenu));
        Actions actions = new Actions(driver);
        actions.moveToElement(informationMenu).perform();
    }

    public boolean isPopUpMenuPresent() {
        return this.popupMenu.isDisplayed();
    }

    public String getPobedaHomePopupHeaderFlightPreparation() {
        return this.popupHeaderFlightPreparation.getText();
    }

    public String getPobedaHomePopupHeaderUsefulInfo() {
        return this.popupHeaderUsefulInfo.getText();
    }

    public String getPobedaHomePopupHeaderAbout() {
        return popupHeaderAbout.getText();
    }

}
