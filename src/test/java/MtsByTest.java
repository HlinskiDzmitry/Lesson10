import dev.failsafe.internal.util.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;

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
    @Description("Тест названия блока пополнения")
    @Epic("Тесты главной страницы")
    @Severity(SeverityLevel.TRIVIAL)
    public void testBlockTitle() {
        mainPage.acceptCookies();
        Assert.isTrue(mainPage.getBlockTitleText().contains("Онлайн пополнение"), "Название блока некорректно.");
    }

    @Test
    @Description("Тест на наличие логотипов платежных систем в блоке пополнения")
    @Epic("Тесты главной страницы")
    @Severity(SeverityLevel.NORMAL)
    public void testPaymentLogos(){
        mainPage.acceptCookies();
        Assert.isTrue(mainPage.getPaymentLogosCount() > 0, "Логотипы платёжных систем отсутствуют.");
    }

    @Test
    @Description("Тест ссылки дополнительной информации")
    @Epic("Тесты главной страницы")
    @Severity(SeverityLevel.MINOR)
    public void testMoreInfoLink() {
        mainPage.acceptCookies();
        mainPage.clickMoreInfoLink();
        Assert.isTrue(mainPage.waitForUrlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"), "Ссылка 'Подробнее о сервисе' не работает.");
    }

    @Test
    @Description("Тест текста опции 'Услуги связи' ")
    @Epic("Тесты главной страницы")
    @Severity(SeverityLevel.TRIVIAL)
    public void testServiceOptionText() {
        mainPage.acceptCookies();
        Assert.isTrue(mainPage.getServiceOptionText().contains("Услуги связи"), "Не выбраны услуги связи.");
    }

    @Test
    @Description("Тест вызова модального окна оплаты")
    @Epic("Тесты главной страницы")
    @Severity(SeverityLevel.CRITICAL)
    public void testPhoneNumberInputAndPaymentModal() {
        mainPage.acceptCookies();
        mainPage.enterPhoneNumber("297777777");
        mainPage.enterSum("1");
        mainPage.clickContinueButton();
        Assert.isTrue(mainPage.isPaymentModalDisplayed(), "Кнопка 'Продолжить' не открывает модальное окно.");
    }

    @Test
    @Description("Тест названия всех опций формы пополнения")
    @Epic("Тесты главной страницы")
    @Severity(SeverityLevel.NORMAL)
    public void testServiceOptionsText() {
        mainPage.acceptCookies();

        List<String> expectedOptions = List.of("Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность");
        driver.findElement(By.xpath("//button[@class='select__header']")).click();
        List<String> actualOptions = mainPage.getServiceOptionsText();
        Assert.isTrue(expectedOptions.containsAll(actualOptions), "Список услуг некорректен.");
    }

    @Test
    @Description("Тест соответствия введенной и в модальном окне оплаты суммы")
    @Epic("Тесты модального окна оплаты")
    @Severity(SeverityLevel.NORMAL)
    public void testPayDescriptionCost() {
        mainPage.acceptCookies();
        mainPage.enterPhoneNumber("297777777");
        mainPage.enterSum("1");
        mainPage.clickContinueButton();
        Assert.isTrue(mainPage.getPayDescriptionCost().contains("1.00 BYN"),"Сумма в блоке оплаты некорректна.");
    }

    @Test
    @Description("Тест соответствия введенного и в модальном окне оплаты номера телефона")
    @Epic("Тесты модального окна оплаты")
    @Severity(SeverityLevel.NORMAL)
    public void testPayDescriptionText() {
        mainPage.acceptCookies();
        mainPage.enterPhoneNumber("297777777");
        mainPage.enterSum("1");
        mainPage.clickContinueButton();
        String description = mainPage.getPayDescriptionText();
        Assert.isTrue(description.contains("Оплата: Услуги связи") && description.contains("Номер:375297777777"),
                "Текст в блоке оплаты некорректен. Ожидается: 'Оплата: Услуги связи' и 'Номер: 375297777777', фактическое: " + description);
    }

    @Test
    @Description("Тест соответствия введенной и в модальном окне оплаты, на кнопке оплаты суммы")
    @Epic("Тесты модального окна оплаты")
    @Severity(SeverityLevel.NORMAL)
    public void testPayButtonCost() {
        mainPage.acceptCookies();
        mainPage.enterPhoneNumber("297777777");
        mainPage.enterSum("1");
        mainPage.clickContinueButton();
        String cost = mainPage.getPayButtonCost();
        Assert.isTrue(cost.contains("Оплатить 1.00 BYN"), "Сумма на кнопке оплаты некорректна.");
    }

    @Test
    @Description("Тест плейсхолдера номера карты в модальном окне оплаты")
    @Epic("Тесты модального окна оплаты")
    @Severity(SeverityLevel.TRIVIAL)
    public void testPlaceHolderNumber(){
        mainPage.acceptCookies();
        mainPage.enterPhoneNumber("297777777");
        mainPage.enterSum("1");
        mainPage.clickContinueButton();
        String number = mainPage.getPlaceHolderCardNumber();
        Assert.isTrue(number.contains("Номер карты"),"Неверный текст в плейсхолдере номера карты");

    }

    @Test
    @Description("Тест плейсхолдера си-ви-си карты в модальном окне оплаты")
    @Epic("Тесты модального окна оплаты")
    @Severity(SeverityLevel.TRIVIAL)
    public void testPlaceHolderCVC(){
        mainPage.acceptCookies();
        mainPage.enterPhoneNumber("297777777");
        mainPage.enterSum("1");
        mainPage.clickContinueButton();
        String cvc = mainPage.getPlaceHolderCardCVC();
        Assert.isTrue(cvc.contains("CVC"),"Неверный текст в плейсхолдере CVC карты");
    }

    @Test
    @Description("Тест плейсхолдера срока действия карты в модальном окне оплаты")
    @Epic("Тесты модального окна оплаты")
    @Severity(SeverityLevel.TRIVIAL)
    public void testPlaceHolderValidity(){
        mainPage.acceptCookies();
        mainPage.enterPhoneNumber("297777777");
        mainPage.enterSum("1");
        mainPage.clickContinueButton();
        String validity = mainPage.getPlaceHolderCardValidity();
        Assert.isTrue(validity.contains("Срок действия"),"Неверный текст в плейсхолдере срока действия карты");
    }

    @Test
    @Description("Тест плейсхолдера имени держателя карты в модальном окне оплаты")
    @Epic("Тесты модального окна оплаты")
    @Severity(SeverityLevel.TRIVIAL)
    public void testPlaceHolderName(){
        mainPage.acceptCookies();
        mainPage.enterPhoneNumber("297777777");
        mainPage.enterSum("1");
        mainPage.clickContinueButton();
        String name = mainPage.getPlaceHolderCardName();
        Assert.isTrue(name.contains("Имя держателя (как на карте)"),"Неверный текст в плейсхолдере имени держателя карты");
    }

    @Test
    @Description("Тест логотипов платежных систем в модальном окне оплаты")
    @Epic("Тесты модального окна оплаты")
    @Severity(SeverityLevel.TRIVIAL)
    public void testCountPaymentCardPage(){
        mainPage.acceptCookies();
        mainPage.enterPhoneNumber("297777777");
        mainPage.enterSum("1");
        mainPage.clickContinueButton();
        Assert.isTrue(mainPage.getPaymentCardPageLogosCount() > 0,"Логотипы платёжных в форме оплаты систем отсутствуют.");
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}