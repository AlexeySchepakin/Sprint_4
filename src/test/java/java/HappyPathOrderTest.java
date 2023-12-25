import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.MainPage;
import pageobjects.OrderPage;
import pageobjects.RentalPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.assertTrue;
import static pageobjects.MainPage.orderButtonTop;

@RunWith(Parameterized.class)
public class HappyPathOrderTest {
    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;
    private RentalPage rentalPage;
    private String name;
    private String surname;
    private String address;
    private String metroStation;
    private String phone;
    private String date;
    private String color;
    private String comment;

    public HappyPathOrderTest(String name, String surname, String address, String metroStation, String phone, String date, String color, String comment) {
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
        return new Object[][] {
                {"Иван", "Иванов", "ул. Ленина, 1", "Автозаводская", "+79991234567", "01.01.2024", "black", "Комментарий 1"},
                {"Мария", "Петрова", "пр. Мира, 10", "Технопарк", "+79993215476", "12.12.2024", "grey", "Комментарий 2"},
                {"Алексей", "Сидоров", "пер. Садовый, 5", "Парк Культуры", "+79997654321", "23.12.2023", "black", "Комментарий 3"}
        };
}

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        /*WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();*/

        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
        rentalPage = new RentalPage(driver);
    }

    @Test
    public void testHappyPathOrderByTopButton() {
        mainPage.clickCookieButton();
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(orderButtonTop));
        mainPage.clickOrderButtonTop();
        assertTrue("Заголовок страницы заказа должен отображаться", orderPage.isOrderHeaderVisible());

        orderPage.fillOrderForm(name, surname, address, metroStation, phone);
        assertTrue("Заголовок страницы аренды должен отображаться", rentalPage.isRentalHeaderVisible());

        rentalPage.fillRentalForm(date, color, comment);
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(RentalPage.confirmButton));
        rentalPage.clickConfirmButton();
        assertTrue("Подтверждение заказа должно отображаться", rentalPage.isOrderConfirmationVisible());
    }

    @Test
    public void testHappyPathOrderByBottomButton() {
        mainPage.clickCookieButton();
        mainPage.scrollToOrderButtonBottom();
        mainPage.clickOrderButtonBottom();

        assertTrue("Заголовок страницы заказа должен отображаться", orderPage.isOrderHeaderVisible());
        orderPage.fillOrderForm(name, surname, address, metroStation, phone);
        assertTrue("Заголовок страницы аренды должен отображаться", rentalPage.isRentalHeaderVisible());

        rentalPage.fillRentalForm(date, color, comment);
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(RentalPage.confirmButton));
        rentalPage.clickConfirmButton();
        assertTrue("Подтверждение заказа должно отображаться", rentalPage.isOrderConfirmationVisible());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}

