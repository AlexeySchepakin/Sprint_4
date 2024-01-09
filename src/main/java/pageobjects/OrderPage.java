package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private static final By orderHeader = By.className("Order_Header__BZXOb");
    private static final By nameInput = By.cssSelector(".Input_Input__1iN_Z.Input_Responsible__1jDKN[placeholder='* Имя']");
    private static final By surnameInput = By.cssSelector(".Input_Input__1iN_Z.Input_Responsible__1jDKN[placeholder='* Фамилия']");
    private static final By addressInput = By.cssSelector(".Input_Input__1iN_Z.Input_Responsible__1jDKN[placeholder='* Адрес: куда привезти заказ']");
    private static final By phoneInput = By.cssSelector(".Input_Input__1iN_Z.Input_Responsible__1jDKN[placeholder='* Телефон: на него позвонит курьер']");
    private static final By nextButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    public void enterSurname(String surname) {
        driver.findElement(surnameInput).sendKeys(surname);
    }

    public void enterAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    public void selectStation(String station) {
        driver.findElement(By.cssSelector("[placeholder='* Станция метро']")).click();
        driver.findElement(By.xpath(".//button/div[text() = '" + station + "']")).click();
    }

    public void enterPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public boolean isOrderHeaderVisible() {
        return driver.findElement(orderHeader).isDisplayed();
    }

    public void fillOrderForm(String name, String surname, String address, String station, String phone) {
        enterName(name);
        enterSurname(surname);
        enterAddress(address);
        selectStation(station);
        enterPhone(phone);
        clickNextButton();
    }
}

