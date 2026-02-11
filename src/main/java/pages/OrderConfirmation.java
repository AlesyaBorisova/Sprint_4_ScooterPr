package Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class OrderConfirmation {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By successPopup = By.cssSelector(".Order_Modal__YZ-d3"); // Изображение успешного заказа

    public OrderConfirmation(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public boolean isOrderSuccessful() {  // Проверяет, что заказ успешно оформлен

        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(successPopup));
        return popup.isDisplayed();
    }
}