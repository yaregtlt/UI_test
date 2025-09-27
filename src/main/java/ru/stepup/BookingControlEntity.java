package ru.stepup;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class BookingControlEntity extends PobedaHome{

    WebDriverWait wait;

    public BookingControlEntity(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "a.dp-1il0417-root-root[href='/services/booking-management']")
    WebElement bookingControl;

    @FindBy(css = "h1[class=\"dp-1dsqh26-root\"]")
    WebElement bookingTitle;

    @FindBy(css = "input.dp-zu3w2f-root-control[placeholder=\"Фамилия клиента\"]")
    WebElement clientSurnameField;

    @FindBy(css = "input.dp-zu3w2f-root-control[placeholder=\"Номер бронирования или билета\"]")
    WebElement orderNumberField;

    @FindBy(css = "button[class=\"dp-18h4yts-root-root-submitBtn\"]")
    WebElement findButton;

    public void skrollToBookingControlAndClick() {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).keyUp(Keys.CONTROL).perform();
        bookingControl.click();
    }

    public boolean isBookingControlTitlePresent() {
        return bookingTitle.isDisplayed();
    }

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
        String originalWindow = driver.getWindowHandle();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        findButton.click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

}
