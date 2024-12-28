import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsByTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("web-driver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.mts.by");
        mainPage = new MainPage(driver);
    }

    @Test
    public void testBlockTitleAndLogos() {
        mainPage.acceptCookies();
        Assert.isTrue(mainPage.getBlockTitleText().contains("Онлайн пополнение"), "Название блока некорректно.");
        Assert.isTrue(mainPage.getPaymentLogosCount() > 0, "Логотипы платёжных систем отсутствуют.");
    }

    @Test
    public void testMoreInfoLink() {
        mainPage.acceptCookies();
        mainPage.clickMoreInfoLink();
        Assert.isTrue(mainPage.waitForUrlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"), "Ссылка 'Подробнее о сервисе' не работает.");
    }

    @Test
    public void testServiceOptionText() {
        mainPage.acceptCookies();
        Assert.isTrue(mainPage.getServiceOptionText().contains("Услуги связи"), "Не выбраны услуги связи.");
    }

    @Test
    public void testPhoneNumberInputAndPaymentModal() {
        mainPage.acceptCookies();
        mainPage.enterPhoneNumber("297777777");
        mainPage.enterSum("1");
        mainPage.clickContinueButton();
        assertTrue(mainPage.isPaymentModalDisplayed(), "Кнопка 'Продолжить' не открывает модальное окно.");
    }

    @Test
    public void testServiceOptionsText() {
        mainPage.acceptCookies();

        List<String> expectedOptions = List.of("Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность");

        List<String> actualOptions = mainPage.getServiceOptionsText();

        Assert.isTrue(expectedOptions.equals(actualOptions), "Список услуг некорректен. Ожидаемое: " + expectedOptions + ", фактическое: " + actualOptions);
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}