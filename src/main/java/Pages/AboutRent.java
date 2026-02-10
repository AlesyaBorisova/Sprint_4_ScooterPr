package Pages;


import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class AboutRent {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public AboutRent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By confirmOrderButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g') " + "and contains(@class, 'Button_Middle__1CSJM') " + "and text()='Заказать']"); // Кнопка "Заказать" после формы Об аренде
    private final By orderPicture = By.cssSelector(".Order_Modal__YZ-d3"); // Изображение подтверждения заказа
    private final By yesButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g') " + "and contains(@class, 'Button_Middle__1CSJM') " + "and text()='Да']"); // Кнопка согласия заказа
    private final By inputWhen = By.cssSelector("input[placeholder='* Когда привезти самокат']"); // Когда привезти самокат
    private final By inputRentPeriod = By.className("Dropdown-root"); // Срок аренды
    private final By inputComment = By.cssSelector("input[placeholder='Комментарий для курьера']"); // Комментарий для курьера

    public OrderConfirmation fillFormAndSubmitOrder(String whenScooter, String rentPeriod, String scooterColor, String commentForTheCourier) {  // Заполняет вторую часть заказа


        WebElement whenInput = wait.until(ExpectedConditions.visibilityOfElementLocated(inputWhen));
        whenInput.sendKeys(whenScooter);
        whenInput.sendKeys(Keys.ENTER);

        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(inputRentPeriod));
        dropdown.click();
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'Dropdown-option') and text()='" + rentPeriod + "']")));
        option.click();

        selectScooterColor(scooterColor);

        wait.until(ExpectedConditions.visibilityOfElementLocated(inputComment)).sendKeys(commentForTheCourier);

        clickOrderButton();
        return submitOrder();
    }

    private void selectScooterColor(String color) { // Выбирает цвет самоката
        By colorLabel = By.xpath("//label[contains(text(), '" + color + "')]");
        WebElement colorElement = wait.until(ExpectedConditions.elementToBeClickable(colorLabel));
        colorElement.click();
    }


    private void clickOrderButton() { // Нажимает на кнопку "Заказать"
        WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton));
        orderButton.click();
    }

    private OrderConfirmation submitOrder() { // Подтверждает заказ
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderPicture));
        wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
        return new OrderConfirmation(driver);

    }
}

