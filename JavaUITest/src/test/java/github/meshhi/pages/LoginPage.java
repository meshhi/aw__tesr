package github.meshhi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import github.meshhi.webdriver.AbstractBaseDriver;
import github.meshhi.utils.ConfProperties;
import github.meshhi.utils.Warehouse;

public class LoginPage {
    public WebDriver driver;
    @FindBy(css = "input[formcontrolname=\"username\"]")
    private WebElement loginField;
    @FindBy(css = "input[formcontrolname=\"password\"]")
    private WebElement passwordField;
    @FindBy(css = ".login-submit.aw-button")
    private WebElement loginButton;
    private WebElement transferSessionButton;

    public LoginPage(AbstractBaseDriver baseDriver) {
        PageFactory.initElements(baseDriver.driver, this);
        this.driver = baseDriver.driver;
    }

    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }

    public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickTransferSessionButton() {
        transferSessionButton.click();
    }

    public Boolean checkTransferSession() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Перенести сессию']")));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public void authorize() throws InterruptedException {
        inputLogin(ConfProperties.getProperty("login"));
        inputPassword(ConfProperties.getProperty("password"));
        clickLoginButton();
        if (checkTransferSession()) {
            transferSessionButton = driver.findElement(By.xpath("//button[text()='Перенести сессию']"));
            clickTransferSessionButton();
        }
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("/app/sources"));
    }
}
