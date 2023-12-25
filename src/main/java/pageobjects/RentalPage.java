package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RentalPage {
    private final WebDriver driver;


    public static final By rentalHeader = By.className("Order_Header__BZXOb");
    private static final By deliveryDateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private static final By rentalDurationDropdown = By.className("Dropdown-placeholder");
    private static final By rentalDuration = By.xpath("//div[contains(text(), 'трое суток')]");
    private static final By scooterColorCheckboxBlack = By.xpath("//label[@for='black']/input");
    private static final By scooterColorCheckboxGrey = By.xpath("//label[@for='grey']/input");
    private static final By courierCommentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private static final By orderButton = By.xpath("//button[text()='Заказать']");
    private static final By orderButtonRental = By.xpath("//*[@id='root']/div/div[2]/div[3]/button[2]");
    public static final By confirmButton = By.xpath("//button[text()='Да']");
    private static final By orderConfirmation = By.xpath("//div[@class='Order_ModalHeader__3FDaJ']");


    public RentalPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterDeliveryDate(String date) {
        driver.findElement(deliveryDateInput).sendKeys(date);
    }

    public void selectScooterColor(String color) {
        if (color.equalsIgnoreCase("black")) {
            driver.findElement(scooterColorCheckboxBlack).click();
        } else if (color.equalsIgnoreCase("grey")) {
            driver.findElement(scooterColorCheckboxGrey).click();
        }
    }

    public void openRentalDurationDropdown() {
        driver.findElement(rentalDurationDropdown).click();
    }

    public void selectRentalDuration() {
        driver.findElement(rentalDuration).click();
    }

    public void enterCourierComment(String comment) {
        driver.findElement(courierCommentInput).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void clickOrderButtonRental() {
        driver.findElement(orderButtonRental).click();
    }

    public void clickConfirmButton() {
        driver.findElement(confirmButton).click();
    }

    public boolean isRentalHeaderVisible() {
        return driver.findElement(rentalHeader).isDisplayed();
    }

    public boolean isOrderConfirmationVisible() {
        return driver.findElement(orderConfirmation).isDisplayed();
    }

    public void fillRentalForm(String date, String color, String comment) {
        enterDeliveryDate(date);
        clickOrderButton();
        openRentalDurationDropdown();
        selectRentalDuration();
        selectScooterColor(color);
        enterCourierComment(comment);
        clickOrderButtonRental();
    }
}


