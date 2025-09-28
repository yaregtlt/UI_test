package ru.stepup;


import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.junit.Test;


import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PobedaFullTests {

    PobedaHome pagePobedaHome;
    InformationEntity informationEntity;
    FindTicketEntity findTicket;
    BookingControlEntity bookingControlEntity;
    BookingControlEntitySecondWindow bookingControlEntitySecondWindow;

    @Before
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
                "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками",
                pagePobedaHome.getPobedaHomeTitleText());
        System.out.println("Title found");
        assertTrue(pagePobedaHome.isPobedaHomeLogoPresent());
        System.out.println("Logo is present");
    }

    @Test
    public void goToPobedaHomeCheckHeaderLogoAndPopUpMenu() {
        informationEntity = new InformationEntity();
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
        sleep(10000);
        assertTrue(bookingControlEntitySecondWindow.isErrorMessagePresent());
        assertEquals("Заказ с указанными параметрами не найден",
                bookingControlEntitySecondWindow.getErrorMessageText());
    }

}
