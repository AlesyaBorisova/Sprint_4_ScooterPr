package tests;

import drivers.FactoryDriver;
import pages.MainPage;
import pages.WhoIsTheScooterFor;
import pages.AboutRent;
import pages.OrderConfirmation;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class ChromeScooterTests {

    private final String firstName;
    private final String secondName;
    private final String address;
    private final String telephone;
    private final String metroStation;
    private final String buttonLocation;
    private final String whenScooter;
    private final String rentPeriod;
    private final String scooterColor;
    private final String commentForCourier;


    public ChromeScooterTests(
            String firstName, String secondName, String address, String telephone,
            String metroStation, String buttonLocation, String whenScooter,
            String rentPeriod, String scooterColor, String commentForCourier) {

        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        this.telephone = telephone;
        this.metroStation = metroStation;
        this.buttonLocation = buttonLocation;
        this.whenScooter = whenScooter;
        this.rentPeriod = rentPeriod;
        this.scooterColor = scooterColor;
        this.commentForCourier = commentForCourier;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1}")
    public static Object[][] testOrderForm() {
        return new Object[][]{
                {"Наташа", "Иванова", "Обручева 3", "89991234567", "Профсоюзная", "top",
                        "23 февраля", "сутки", "чёрный жемчуг", "Хорошего дня"},
                {"Кирилл", "Петров", "Маяковского 25", "89992345678", "Курская", "bottom",
                        "3 марта", "трое суток", "серая безысходность", "Спасибо"},
                {"Анна", "Седлецкая", "Ленина 6", "89999876543", "Спортивная", "top",
                        "20 февраля", "шестеро суток", "чёрный жемчуг", "Свежий самокат, пожалуйста"}
        };
    }

    @Rule
    public FactoryDriver factoryDriver = new FactoryDriver();

    @Test
    public void testFullOrderFlow() { // Проверяет полный путь заказа
        WebDriver driver = factoryDriver.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.AcceptCookie();

        WhoIsTheScooterFor whoIsTheScooterFor = new WhoIsTheScooterFor(driver);
        AboutRent aboutRent = whoIsTheScooterFor.fillFormAndGoToAboutRent(firstName, secondName, address, telephone, metroStation, buttonLocation);
        OrderConfirmation confirmation = aboutRent.fillFormAndSubmitOrder(whenScooter, rentPeriod, scooterColor, commentForCourier);
        assertTrue(confirmation.isOrderSuccessful());
        driver.quit();
    }
}



