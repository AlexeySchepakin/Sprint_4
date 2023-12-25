import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjects.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;
    private final int questionIndex;
    private final int answerIndex;

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {1, 1},
                {2, 2},
                {3, 3},
                {4, 4},
                {5, 5},
                {6, 6},
                {7, 7}
        };
    }

    public MainPageTest(int questionIndex, int answerIndex) {
        this.questionIndex = questionIndex;
        this.answerIndex = answerIndex;
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
    }

    @Test
    public void testFaqFunctionality() throws InterruptedException {
        mainPage.clickCookieButton();
        mainPage.clickQuestion(questionIndex);
        Thread.sleep(500);
        assertTrue("Ответ на вопрос должен быть видим", mainPage.isAnswerVisible(answerIndex));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
