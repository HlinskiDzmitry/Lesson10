import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MtsByTests {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://www.mts.by");

            //wait.wait(1000);



            WebElement cookieAcceptButton = driver.findElement(By.xpath("//button[@id='cookie-agree']"));
            cookieAcceptButton.click();

            WebElement blockTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='pay__wrapper']/child::h2")));
            Assert.isTrue(blockTitle.getText().contains("Онлайн пополнение"),"Название блока некорректно.");
//            if (blockTitle.getText().contains("Онлайн пополнение")) {
//                System.out.println("Название блока корректно.");
//            } else {
//                System.err.println("Название блока некорректно.");
//            }

            List<WebElement> paymentLogos = driver.findElements(
                    By.xpath("//div[@class='pay__wrapper']//child::img"));
            Assert.isTrue(paymentLogos.size()>0,"Логотипы платёжных систем отсутствуют.");
//            if (!paymentLogos.isEmpty()) {
//                System.out.println("Логотипы платёжных систем отображаются.");
//            } else {
//                System.err.println("Логотипы платёжных систем отсутствуют.");
//            }

            WebElement moreInfoLink = driver.findElement(
                    By.xpath("//div[@class='pay__wrapper']//child::a"));
            moreInfoLink.click();
            wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));
            Assert.isTrue(driver.getCurrentUrl().contains("poryadok-oplaty-i-bezopasnost-internet-platezhey"), "Ссылка 'Подробнее о сервисе' не работает.");

//            if (driver.getCurrentUrl().contains("poryadok-oplaty-i-bezopasnost-internet-platezhey")) {
//                System.out.println("Ссылка 'Подробнее о сервисе' работает корректно.");
//            } else {
//                System.err.println("Ссылка 'Подробнее о сервисе' не работает.");
//            }
            driver.navigate().back();
            //wait.wait(5000);


            WebElement serviceOptionButtonText = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@class='select__header']/child::span[@class='select__now']")));
            Assert.isTrue(serviceOptionButtonText.getText().contains("Услуги связи"),"Не выбраны услуги связи");


            WebElement phoneNumberField = driver.findElement(
                    By.xpath("//input[@id='connection-phone']"));
            phoneNumberField.sendKeys("297777777");


            WebElement continueButton = driver.findElement(
                    By.xpath("//input[@id='connection-email']/parent::div[@class='input-wrapper']//following-sibling::button"));


            WebElement sumNumberField = driver.findElement(By.xpath("//input[@id='connection-sum']"));
            sumNumberField.sendKeys("1");
            continueButton.click();


            WebElement paymentModal = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='bepaid-app']")
            ));
            Assert.isTrue(paymentModal.isDisplayed(),"Кнопка 'Продолжить' не работает.");
//            if (paymentModal.isDisplayed()) {
//                System.out.println("Кнопка 'Продолжить' работает корректно.");
//            } else {
//                System.err.println("Кнопка 'Продолжить' не работает.");
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}