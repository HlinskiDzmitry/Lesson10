import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        Assert.isTrue(mainPage.isPaymentModalDisplayed(), "Кнопка 'Продолжить' не открывает модальное окно.");
    }

    @Test
    public void testServiceOptionsText() {
        mainPage.acceptCookies();

        List<String> expectedOptions = List.of("Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность");
        driver.findElement(By.xpath("//button[@class='select__header']")).click();
        List<String> actualOptions = mainPage.getServiceOptionsText();
        Assert.isTrue(expectedOptions.containsAll(actualOptions), "Список услуг некорректен.");
    }

    @Test
    public void testPayDescriptionCost() {
        mainPage.acceptCookies();
        mainPage.enterPhoneNumber("297777777");
        mainPage.enterSum("1");
        mainPage.clickContinueButton();
        Assert.isTrue(mainPage.getPayDescriptionCost().contains("1.00 BYN"),"Сумма в блоке оплаты некорректна.");
    }

    @Test
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
    public void testPayButtonCost() {
        mainPage.acceptCookies();
        mainPage.enterPhoneNumber("297777777");
        mainPage.enterSum("1");
        mainPage.clickContinueButton();
        String cost = mainPage.getPayButtonCost();
        Assert.isTrue(cost.contains("Оплатить 1.00 BYN"), "Сумма на кнопке оплаты некорректна.");
    }

    @Test
    public void testPlaceHolderNumber(){
        mainPage.acceptCookies();
        mainPage.enterPhoneNumber("297777777");
        mainPage.enterSum("1");
        mainPage.clickContinueButton();
        String number = mainPage.getPlaceHolderCardNumber();
        Assert.isTrue(number.contains("Номер карты"),"Неверный текст в плейсхолдере номера карты");

    }

    @Test
    public void testPlaceHolderCVC(){
        mainPage.acceptCookies();
        mainPage.enterPhoneNumber("297777777");
        mainPage.enterSum("1");
        mainPage.clickContinueButton();
        String cvc = mainPage.getPlaceHolderCardCVC();
        Assert.isTrue(cvc.contains("CVC"),"Неверный текст в плейсхолдере CVC карты");
    }

    @Test
    public void testPlaceHolderValidity(){
        mainPage.acceptCookies();
        mainPage.enterPhoneNumber("297777777");
        mainPage.enterSum("1");
        mainPage.clickContinueButton();
        String validity = mainPage.getPlaceHolderCardValidity();
        Assert.isTrue(validity.contains("Срок действия"),"Неверный текст в плейсхолдере срока действия карты");
    }

    @Test
    public void testPlaceHolderName(){
        mainPage.acceptCookies();
        mainPage.enterPhoneNumber("297777777");
        mainPage.enterSum("1");
        mainPage.clickContinueButton();
        String name = mainPage.getPlaceHolderCardName();
        Assert.isTrue(name.contains("Имя держателя (как на карте)"),"Неверный текст в плейсхолдере имени держателя карты");
    }

    @Test
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