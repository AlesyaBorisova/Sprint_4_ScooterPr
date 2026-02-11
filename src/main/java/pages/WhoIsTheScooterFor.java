package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class WhoIsTheScooterFor {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nextButton = By.cssSelector("div.Order_NextButton__1_rCA > button.Button_Button__ra12g.Button_Middle__1CSJM"); // Кнопка "Далее"
    private final By topButtonOrder = By.cssSelector("div.Header_Nav__AGCXC > button.Button_Button__ra12g"); // Кнопка "Заказать" вверху страницы
    private final By bottomButtonOrder = By.cssSelector("div.Home_FinishButton__1_cWm button.Button_Button__ra12g.Button_Middle__1CSJM"); // Кнопка "Заказать" внизу страницы
    private final By nameInput = By.cssSelector("input[placeholder='* Имя']"); // Поле "Имя"
    private final By secondNameInput = By.xpath("//input[@placeholder='* Фамилия']"); // Поле "Фамилия"
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']"); // Поле "Адрес"
    private final By metroStationInput = By.xpath("//input[@placeholder='* Станция метро']"); // Поле "Станция метро"
    private final By telephoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']"); // Поле "Телефон"
    private final By header = By.className("Order_Header__BZXOb"); // Заголовок "Для кого самокат"
    private final By listMetroStations = By.className("select-search__select"); // Список станций метро

    public WhoIsTheScooterFor(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public AboutRent fillFormAndGoToAboutRent(String firstName, String secondName, String address, String telephone, String metroStation, String buttonLocation) {  // Заполняет первую форму заказа

        if ("top".equals(buttonLocation)) {
            WebElement topButton = wait.until(ExpectedConditions.elementToBeClickable(topButtonOrder));
            topButton.click();
        } else {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            WebElement bottomButton = wait.until(ExpectedConditions.elementToBeClickable(bottomButtonOrder));
            bottomButton.click();

        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(header));

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        nameField.sendKeys(firstName);

        WebElement secondNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(secondNameInput));
        secondNameField.sendKeys(secondName);

        WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(addressInput));
        addressField.sendKeys(address);

        WebElement telephoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(telephoneInput));
        telephoneField.sendKeys(telephone);

        WebElement metroStationField = wait.until(ExpectedConditions.visibilityOfElementLocated(metroStationInput));
        metroStationField.sendKeys(metroStation);
        wait.until(ExpectedConditions.visibilityOfElementLocated(listMetroStations));
        String optionXpath = String.format("//div[@class='select-search__select']//div[text()='%s']", metroStation);
        WebElement stationOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXpath)));
        stationOption.click();

        return clickNextButton();

    }

    public AboutRent clickNextButton() { // Нажимает на кнопку "Далее"

        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();

        return new AboutRent(driver);
    }

}
