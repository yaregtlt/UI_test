package ru.stepup;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PobedaFullTests {
    WebDriver driver;
    WebDriverWait wait;

    PobedaHome pagePobedaHome;
    InformationEntity informationEntity;
    FindTicketEntity findTicket;

    @Before
    public void initializationDriver() {
        System.setProperty("webdriver.chrome.driver","C:/PerfLogs/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    }

    @Test
    public void goToPobedaHomeCheckHeaderLogoAndPopUpMenu() {
        driver.get("https://pobeda.aero/");
        pagePobedaHome = new PobedaHome(driver);
        informationEntity = new InformationEntity(driver);
        assertEquals("https://www.flypobeda.ru/", pagePobedaHome.getLoadedPageURL());
        assertEquals("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками",pagePobedaHome.getPobedaHomeTitleText());
        System.out.println("Title found");
        assertTrue(pagePobedaHome.isPobedaHomeLogoPresent());
        System.out.println("Logo is present");
        informationEntity.moveToInformation();
        System.out.println("Move cursor to Information");
        assertTrue(informationEntity.isPopUpMenuPresent());
        System.out.println("Popup appears");
        assertEquals("Подготовка к полёту", informationEntity.getPobedaHomePopupHeaderFlightPreparation());
        assertEquals("Полезная информация", informationEntity.getPobedaHomePopupHeaderUsefulInfo());
        assertEquals("О компании", informationEntity.getPobedaHomePopupHeaderAbout());
    }

    @Test
    public void goToPobedaHomeAndTryToFindTicket() throws InterruptedException {
        driver.get("https://pobeda.aero/");
        pagePobedaHome = new PobedaHome(driver);
        findTicket = new FindTicketEntity(driver);
        assertEquals("https://www.flypobeda.ru/", pagePobedaHome.getLoadedPageURL());
        assertEquals("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками",pagePobedaHome.getPobedaHomeTitleText());
        System.out.println("Title found");
        assertTrue(pagePobedaHome.isPobedaHomeLogoPresent());
        System.out.println("Logo is present");
        assertTrue(findTicket.isFindTicketPresent());
        assertTrue(findTicket.isFindTicketFromFieldPresent());
        assertTrue(findTicket.isFindTicketToFieldPresent());
        assertTrue(findTicket.isFindTicketDateTherePresent());
        assertTrue(findTicket.isFindTicketDateBackPresent());
        findTicket.setFindTicketFromField("Москва");
        findTicket.setFindTicketToField("Санкт-Петербург");
        findTicket.clickFindTicketFindButton();
//        Thread.sleep(5000);
        assertTrue(findTicket.isFindTicketDateThereBorderColoredWith("rgb(213, 0, 98)"));
    }

    @After
    public void killDriver() {
        driver.quit();
    }

}
