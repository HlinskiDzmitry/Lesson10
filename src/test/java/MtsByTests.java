import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsByTests {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.mts.by");
        acceptCookies();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    private void acceptCookies() {
       try {
           WebElement cookieAcceptButton = driver.findElement(By.xpath("//button[@id='cookie-agree']"));
           cookieAcceptButton.click();
       } catch (Exception e){
           System.out.println("Cookie accept button not found or not displayed.");
       }
    }

    @Test
    public void testBlockTitle() {
        WebElement blockTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='pay__wrapper']/child::h2")));
        assertTrue(blockTitle.getText().contains("Онлайн пополнение"), "Название блока некорректно.");
    }

    @Test
    public void testPaymentLogos() {
        List<WebElement> paymentLogos = driver.findElements(By.xpath("//div[@class='pay__wrapper']//child::img"));
        assertTrue(paymentLogos.size() > 0, "Логотипы платёжных систем отсутствуют.");
    }

    @Test
    public void testMoreInfoLink() {
        WebElement moreInfoLink = driver.findElement(By.xpath("//div[@class='pay__wrapper']//child::a"));
        moreInfoLink.click();
        wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));
        assertTrue(driver.getCurrentUrl().contains("poryadok-oplaty-i-bezopasnost-internet-platezhey"),
                "Ссылка 'Подробнее о сервисе' не работает.");
    }

    @Test
    public void testServiceOptionButtonText() {
        WebElement serviceOptionButtonText = driver.findElement(
                By.xpath("//button[@class='select__header']/child::span[@class='select__now']"));
        assertTrue(serviceOptionButtonText.getText().contains("Услуги связи"), "Не выбраны услуги связи");
    }

    @Test
    public void testPhoneNumberFieldAndContinueButton() {
        WebElement phoneNumberField = driver.findElement(By.xpath("//input[@id='connection-phone']"));
        phoneNumberField.sendKeys("297777777");

        WebElement continueButton = driver.findElement(
                By.xpath("//input[@id='connection-email']/parent::div[@class='input-wrapper']//following-sibling::button"));

        WebElement sumNumberField = driver.findElement(By.xpath("//input[@id='connection-sum']"));
        sumNumberField.sendKeys("1");
        continueButton.click();

        WebElement paymentModal = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='bepaid-app']")
        ));
        assertTrue(paymentModal.isDisplayed(), "Кнопка 'Продолжить' не работает.");
    }
}