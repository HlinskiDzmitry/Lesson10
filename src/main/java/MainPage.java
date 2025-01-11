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

    @FindBy(xpath = "//div[@class='app-wrapper']")
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

    @FindBy(xpath = "//div[@class='pay-description__cost']//child::span[1]")
    private WebElement payDescriptionCost;

    @FindBy(xpath = "//div[@class='pay-description__text']//child::span[1]")
    private WebElement payDescriptionText;

    @FindBy(xpath = "//div[@class='card-page__card']//child::button")
    private WebElement payButtonCost;

    @FindBy(xpath = "//div[@class='content ng-tns-c46-1']//child::label[@class='ng-tns-c46-1 ng-star-inserted']")
    private WebElement placeholderCardNumber;

    @FindBy(xpath = "//div[@class='content ng-tns-c46-4']//child::label[@class='ng-tns-c46-4 ng-star-inserted']")
    private WebElement placeholderCardValidity;

    @FindBy(xpath = "//div[@class='content ng-tns-c46-5']//child::label[@class='ng-tns-c46-5 ng-star-inserted']")
    private WebElement placeholderCardCVC;

    @FindBy(xpath = "//div[@class='content ng-tns-c46-3']//child::label[@class='ng-tns-c46-3 ng-star-inserted']")
    private WebElement placeholderCardName;

    @FindBy(xpath = "//div[@class='cards-brands cards-brands__container ng-tns-c61-0 ng-trigger ng-trigger-brandsState ng-star-inserted']//child::img")
    private List<WebElement> paymentLogosCardPage;

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
        new WebDriverWait(driver,Duration.ofSeconds(60)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='bepaid-iframe']")));
        new WebDriverWait(driver,Duration.ofSeconds(60)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'switch-track')]")));
    }

    public boolean isPaymentModalDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(paymentModal)).isDisplayed();
    }

    public List<String> getServiceOptionsText() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[@class='select__option']")));
        List<String> optionsText = new ArrayList<>();
        for (WebElement option : serviceOptions) {
            optionsText.add(option.getText());
        }
        return optionsText;
    }

    public String getPayDescriptionCost() {
        return wait.until(ExpectedConditions.visibilityOf(payDescriptionCost)).getText();
    }

    public String getPayDescriptionText() {
        return wait.until(ExpectedConditions.visibilityOf(payDescriptionText)).getText();
    }

    public String getPayButtonCost() {
        return wait.until(ExpectedConditions.visibilityOf(payButtonCost)).getText();
    }

    public String getPlaceHolderCardNumber() {
        return wait.until(ExpectedConditions.visibilityOf(placeholderCardNumber)).getText();
    }

    public String getPlaceHolderCardValidity() {
        return wait.until(ExpectedConditions.visibilityOf(placeholderCardValidity)).getText();
    }

    public String getPlaceHolderCardCVC() {
        return wait.until(ExpectedConditions.visibilityOf(placeholderCardCVC)).getText();
    }

    public String getPlaceHolderCardName() {
        return wait.until(ExpectedConditions.visibilityOf(placeholderCardName)).getText();
    }

    public int getPaymentCardPageLogosCount() {
        return paymentLogosCardPage.size();


    }
}