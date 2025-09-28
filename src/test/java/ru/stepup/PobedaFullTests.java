package ru.stepup;


import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Тесты для сайт авиакомпании Победа")
public class PobedaFullTests {

    PobedaHome pagePobedaHome;
    InformationEntity informationEntity;
    FindTicketEntity findTicket;
    BookingControlEntity bookingControlEntity;
    BookingControlEntitySecondWindow bookingControlEntitySecondWindow;

    @BeforeEach
    public void start() {
        setup();
        goToPobedaHomeCheckHeaderAndLogo();
    }

    public void setup() {
        Configuration.reportsFolder = "C:\\PerfLogs\\screen";
        Configuration.screenshots = true;
    }

    public void goToPobedaHomeCheckHeaderAndLogo() {
        open("https://pobeda.aero/");
        pagePobedaHome = new PobedaHome();
        assertEquals("https://www.flypobeda.ru/", pagePobedaHome.getLoadedPageURL());
        assertEquals(
                "Авиакомпания «Победа» - купить авиабилеты онлайн, " +
                        "дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками",
                pagePobedaHome.getPobedaHomeTitleText());
        assertTrue(pagePobedaHome.isPobedaHomeLogoPresent());
    }

    @Test
    @Description("Проверить, что на главной странице есть заголовок и лого, " +
            "навести курсор на Информация и проверить содержимое")
    @Feature("POBEDA-TEST")
    public void goToPobedaHomeCheckHeaderLogoAndPopUpMenu() {
        informationEntity = new InformationEntity();
        informationEntity.moveToInformation();
        assertTrue(informationEntity.isPopUpMenuPresent());
        assertEquals("Подготовка к полёту", informationEntity.getPobedaHomePopupHeaderFlightPreparation());
        assertEquals("Полезная информация", informationEntity.getPobedaHomePopupHeaderUsefulInfo());
        assertEquals("О компании", informationEntity.getPobedaHomePopupHeaderAbout());
    }

    @Test
    @Description("Проверить, что на главной странице есть заголовок и лого, " +
            "в поля блока Поиск билетов ввести данные Откуда и Куда и проверить цвет рамки поля Откуда")
    @Feature("POBEDA-TEST")
    public void goToPobedaHomeAndTryToFindTicket() {
        findTicket = new FindTicketEntity();
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
    @Description("Проверить, что на главной странице есть заголовок и лого, " +
            "перейти на страницу брони ввести данные и проверить сообщение об ошибке")
    @Feature("POBEDA-TEST")
    public void goToPobedaHomeAndCheckBooking() throws InterruptedException {
        bookingControlEntity = new BookingControlEntity();
        bookingControlEntitySecondWindow = new BookingControlEntitySecondWindow();
        bookingControlEntity.skrollToBookingControlAndClick();
        assertEquals("https://www.flypobeda.ru/services/booking-management",
                bookingControlEntity.getLoadedPageURL());
//        assertTrue(bookingControlEntity.isBookingControlTitlePresent());
        assertTrue(bookingControlEntity.isOrderNumberPresent());
        assertTrue(bookingControlEntity.isClientSurnamePresent());
        assertTrue(bookingControlEntity.isFindButtonPresent());
        bookingControlEntity.setOrderNumberField("XXXXXX");
        bookingControlEntity.setClientSurnameField("Qwerty");
        bookingControlEntity.clickFindButtonAndGoToNewWindow();
        assertEquals("https://ticket.flypobeda.ru/websky/?lang=ru#/search-order/XXXXXX/Qwerty",
                bookingControlEntity.getLoadedPageURL());
        bookingControlEntitySecondWindow.isNumberAndSurnameFieldsPresent();
        bookingControlEntitySecondWindow.setCheckBox();
        bookingControlEntitySecondWindow.clickFindButton();
        //задерка чтобы пройти "Я робот"
        sleep(20000);
        assertTrue(bookingControlEntitySecondWindow.isErrorMessagePresent());
        assertEquals("Заказ с указанными параметрами не найден",
                bookingControlEntitySecondWindow.getErrorMessageText());
    }

    @Test
    @Description("Проверить, что на главной странице есть заголовок и лого, " +
            "в поля блока Поиск билетов ввести данные Откуда и Куда и проверить цвет рамки поля Откуда; Тест НЕУСПЕШНЫЙ")
    @Feature("POBEDA-TEST")
    public void goToPobedaHomeAndTryToFindTicketDummyFailed() {
        findTicket = new FindTicketEntity();
        assertTrue(findTicket.isFindTicketPresent());
        assertTrue(findTicket.isFindTicketFromFieldPresent());
        assertTrue(findTicket.isFindTicketToFieldPresent());
        assertTrue(findTicket.isFindTicketDateTherePresent());
        assertTrue(findTicket.isFindTicketDateBackPresent());
        findTicket.setFindTicketFromField("Москва");
        findTicket.setFindTicketToField("Санкт-Петербург");
        findTicket.clickFindTicketFindButton();
        assertTrue(findTicket.isFindTicketDateThereBorderColoredWith("rgb(213, 0, 95)"));
    }

}
