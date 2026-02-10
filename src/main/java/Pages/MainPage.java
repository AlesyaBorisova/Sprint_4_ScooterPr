package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {


    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By acceptCookie = By.id("rcc-confirm-button"); // Кнопка "Принять куки"


    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openPage() { // Открывает стартовую страницу
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void AcceptCookie() { // Принимает куки

        WebElement element = driver.findElement(acceptCookie);
        element.click();

    }

    public void clickQuestion(String questionText) {   // Кликает по заголовку вопроса
        String questionXpath = "//div[@class='accordion__button' and contains(text(), '" + questionText + "')]";
        WebElement question = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(questionXpath)));
        question.click();
    }

    public String getAnswerText(String expectedAnswer) {   // Получает текст ответа
        String answerXpath = "//div[@class='accordion__panel']//p[contains(text(), '" + expectedAnswer + "')]";
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(answerXpath)));
        return answer.getText();
    }
}