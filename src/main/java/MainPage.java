import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//div[@class='bepaid-app']")
    private WebElement paymentModal;


    @FindBy(xpath = "//button[@id='cookie-agree']")
    private WebElement cookieAcceptButton;

    @FindBy(xpath = "//div[@class='pay__wrapper']/child::h2")
    private WebElement blockTitle;

    @FindBy(xpath = "//div[@class='pay__wrapper']//child::img")
    private List<WebElement> paymentLogos;

    @FindBy(xpath = "//div[@class='pay__wrapper']//child::a")
    private WebElement moreInfoLink;

    @FindBy(xpath = "//button[@class='select__header']/child::span[@class='select__now']")
    private WebElement serviceOptionButtonText;

    @FindBy(id = "connection-phone")
    private WebElement phoneNumberField;

    @FindBy(xpath = "//input[@id='connection-email']/parent::div[@class='input-wrapper']//following-sibling::button")
    private WebElement continueButton;
    @FindBy(xpath = "//input[@id='connection-sum']")
    private WebElement sumNumberField;
    @FindBy(xpath = "//p[@class='select__option']")
    private List<WebElement> serviceOptions;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    public void acceptCookies() {
        try {
            if (wait.until(ExpectedConditions.visibilityOf(cookieAcceptButton)).isDisplayed()) {
                cookieAcceptButton.click();
            }
        } catch (Exception e) {
            System.out.println("Cookie accept button not found or not displayed.");
        }
    }
    public String getBlockTitleText() {
        return wait.until(ExpectedConditions.visibilityOf(blockTitle)).getText();
    }
    public int getPaymentLogosCount() {
        return paymentLogos.size();
    }
    public void clickMoreInfoLink() {
        moreInfoLink.click();
    }
    public boolean waitForUrlContains(String urlFragment) {
        return wait.until(ExpectedConditions.urlContains(urlFragment));
    }
    public String getServiceOptionText() {
        return wait.until(ExpectedConditions.elementToBeClickable(serviceOptionButtonText)).getText();
    }
    public void enterPhoneNumber(String phoneNumber) {
        WebElement phoneField = wait.until(ExpectedConditions.elementToBeClickable(phoneNumberField));
        phoneField.clear();
        phoneField.sendKeys(phoneNumber);
    }

    public void enterSum(String sum) {
        WebElement sumField = wait.until(ExpectedConditions.elementToBeClickable(sumNumberField));
        sumField.clear();
        sumField.sendKeys(sum);
    }
    public void clickContinueButton() {
        continueButton.click();
    }
    public boolean isPaymentModalDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(paymentModal)).isDisplayed();
        } catch (Exception e) {
            System.out.println("Модальное окно оплаты не отображается.");
            return false;
        }
    }

    public List<String> getServiceOptionsText() {
        wait.until(ExpectedConditions.visibilityOfAllElements(serviceOptions));
        List<String> optionsText = new ArrayList<>();
        for (WebElement option : serviceOptions) {
            optionsText.add(option.getText());
        }
        return optionsText;
    }

}
