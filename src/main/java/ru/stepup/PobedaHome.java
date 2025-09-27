package ru.stepup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PobedaHome
{
    WebDriver driver;

    @FindBy(css = "meta[property=\"og:title\"]")
    WebElement pageHeader;

    @FindBy(css = "a[class=\"dp-1rojma8-root-root-root\"] > img")
    WebElement pageLogo;

    public PobedaHome(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getLoadedPageURL() {
        return driver.getCurrentUrl();
    }

    public String getPobedaHomeTitleText() {
        return pageHeader.getAttribute("content");
    }

    public boolean isPobedaHomeLogoPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait.until(ExpectedConditions.visibilityOf(this.pageLogo));
        return pageLogo.isDisplayed();
    }

}
