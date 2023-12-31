import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.MainPage;
import pageobjects.OrderPage;
import pageobjects.RentalPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class HappyPathOrderTest extends BaseTest {
    private MainPage mainPage;
    private OrderPage orderPage;
    private RentalPage rentalPage;

    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String date;
    private final String color;
    private final String comment;

    public HappyPathOrderTest(String name, String surname, String address,
                              String metroStation, String phone, String date,
                              String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"Иван", "Иванов", "ул. Ленина, 1", "Автозаводская", "+79991234567", "01.01.2024", "black", "Комментарий 1"},
                {"Мария", "Петрова", "пр. Мира, 10", "Технопарк", "+79993215476", "12.12.2024", "grey", "Комментарий 2"},
                {"Алексей", "Сидоров", "пер. Садовый, 5", "Парк Культуры", "+79997654321", "23.12.2023", "black", "Комментарий 3"}
        };
    }

    @Test
    public void testHappyPathOrderByTopButton() {
        initializePageObjects();

        mainPage.clickCookieButton();
        mainPage.clickOrderButtonTop();
        assertOrderHeaderVisible();

        orderPage.fillOrderForm(name, surname, address, metroStation, phone);
        assertRentalHeaderVisible();

        rentalPage.fillRentalForm(date, color, comment);
        waitForConfirmButtonAndClick();
        assertOrderConfirmationVisible();
    }

    @Test
    public void testHappyPathOrderByBottomButton() {
        initializePageObjects();
        mainPage.goToAndClickBottomOrderButton();
        assertOrderHeaderVisible();

        orderPage.fillOrderForm(name, surname, address, metroStation, phone);
        assertRentalHeaderVisible();

        rentalPage.fillRentalForm(date, color, comment);
        waitForConfirmButtonAndClick();
        assertOrderConfirmationVisible();
    }

    private void initializePageObjects() {
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
        rentalPage = new RentalPage(driver);
    }

    private void assertOrderHeaderVisible() {
        assertTrue("Заголовок страницы заказа должен отображаться", orderPage.isOrderHeaderVisible());
    }

    private void assertRentalHeaderVisible() {
        assertTrue("Заголовок страницы аренды должен отображаться", rentalPage.isRentalHeaderVisible());
    }

    private void assertOrderConfirmationVisible() {
        assertTrue("Подтверждение заказа должно отображаться", rentalPage.isOrderConfirmationVisible());
    }

    private void waitForConfirmButtonAndClick() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(RentalPage.confirmButton));
        rentalPage.clickConfirmButton();
    }
}
