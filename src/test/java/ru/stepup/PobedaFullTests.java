package ru.stepup;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    BookingControlEntity bookingControlEntity;
    BookingControlEntitySecondWindow bookingControlEntitySecondWindow;

    @Before
    public void setUp() {
        initializationDriver();
        goToPobedaHomeCheckHeaderAndLogo();
    }

    public void initializationDriver() {
        System.setProperty("webdriver.chrome.driver","C:/PerfLogs/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    }

    public void goToPobedaHomeCheckHeaderAndLogo() {
        driver.get("https://pobeda.aero/");
        pagePobedaHome = new PobedaHome(driver);
        assertEquals("https://www.flypobeda.ru/", pagePobedaHome.getLoadedPageURL());
        assertEquals(
                "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками",
                pagePobedaHome.getPobedaHomeTitleText());
        System.out.println("Title found");
        assertTrue(pagePobedaHome.isPobedaHomeLogoPresent());
        System.out.println("Logo is present");
    }

    @Test
    public void goToPobedaHomeCheckHeaderLogoAndPopUpMenu() {
        informationEntity = new InformationEntity(driver);
        informationEntity.moveToInformation();
        System.out.println("Move cursor to Information");
        assertTrue(informationEntity.isPopUpMenuPresent());
        System.out.println("Popup appears");
        assertEquals("Подготовка к полёту", informationEntity.getPobedaHomePopupHeaderFlightPreparation());
        assertEquals("Полезная информация", informationEntity.getPobedaHomePopupHeaderUsefulInfo());
        assertEquals("О компании", informationEntity.getPobedaHomePopupHeaderAbout());
    }

    @Test
    public void goToPobedaHomeAndTryToFindTicket() {
        findTicket = new FindTicketEntity(driver);
        assertTrue(findTicket.isFindTicketPresent());
        assertTrue(findTicket.isFindTicketFromFieldPresent());
        assertTrue(findTicket.isFindTicketToFieldPresent());
        assertTrue(findTicket.isFindTicketDateTherePresent());
        assertTrue(findTicket.isFindTicketDateBackPresent());
        findTicket.setFindTicketFromField("Москва");
        findTicket.setFindTicketToField("Санкт-Петербург");
        findTicket.clickFindTicketFindButton();
        assertTrue(findTicket.isFindTicketDateThereBorderColoredWith("rgb(213, 0, 98)"));
    }

    @Test
    public void goToPobedaHomeAndCheckBooking() throws InterruptedException {
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        bookingControlEntity = new BookingControlEntity(driver);
        bookingControlEntitySecondWindow = new BookingControlEntitySecondWindow(driver);
        bookingControlEntity.skrollToBookingControlAndClick();
        wait.until(ExpectedConditions.urlContains("https://www.flypobeda.ru/services/booking-management"));
        assertEquals("https://www.flypobeda.ru/services/booking-management",
                bookingControlEntity.getLoadedPageURL());
        assertTrue(bookingControlEntity.isBookingControlTitlePresent());
        assertTrue(bookingControlEntity.isOrderNumberPresent());
        assertTrue(bookingControlEntity.isClientSurnamePresent());
        assertTrue(bookingControlEntity.isFindButtonPresent());
        bookingControlEntity.setOrderNumberField("XXXXXX");
        bookingControlEntity.setClientSurnameField("Qwerty");
        bookingControlEntity.clickFindButtonAndGoToNewWindow();
        wait.until(ExpectedConditions.urlContains("https://ticket.flypobeda.ru/websky/?lang=ru#/search-order/XXXXXX/Qwerty"));
        assertEquals("https://ticket.flypobeda.ru/websky/?lang=ru#/search-order/XXXXXX/Qwerty",
                bookingControlEntity.getLoadedPageURL());
        bookingControlEntitySecondWindow.isNumberAndSurnameFieldsPresent();
        bookingControlEntitySecondWindow.setCheckBox();
        bookingControlEntitySecondWindow.clickFindButton();
        assertTrue(bookingControlEntitySecondWindow.isErrorMessagePresent());
        assertEquals("Заказ с указанными параметрами не найден",
                bookingControlEntitySecondWindow.getErrorMessageText());
    }

    @After
    public void killDriver() {
        driver.quit();
    }

}
