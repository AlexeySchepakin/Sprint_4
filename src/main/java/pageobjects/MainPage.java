package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private final WebDriver driver;

    public static By orderButtonTop = By.className("Button_Button__ra12g");
    public static By orderButtonBottom = By.xpath("//div[contains(@class, 'Home_ThirdPart__LSTEE')]//button[text()='Заказать']");
    private final By cookieButton = By.className("App_CookieButton__3cvqF");

    public By getQuestionLocator(int questionIndex) {
        return By.id("accordion__heading-" + (questionIndex - 1));
    }
    public By getAnswerLocator(int answerIndex) {
        return By.id("accordion__panel-" + (answerIndex - 1));
    }

    public void clickQuestion(int questionIndex) {
        WebElement element = driver.findElement(getQuestionLocator(questionIndex));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public boolean isAnswerVisible(int answerIndex) {
        return driver.findElement(getAnswerLocator(answerIndex)).isDisplayed();
    }

    public MainPage (WebDriver driver) {
        this.driver = driver;
    }
    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }
    public void clickOrderButtonBottom() {
        WebElement element = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public void scrollToOrderButtonBottom() {
        WebElement orderButtonBottom = driver.findElement(By.xpath("//div[contains(@class, 'Home_ThirdPart__LSTEE')]//button[text()='Заказать']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderButtonBottom);
    }

    public void clickCookieButton() {
        driver.findElement(cookieButton).click();
    }
}
