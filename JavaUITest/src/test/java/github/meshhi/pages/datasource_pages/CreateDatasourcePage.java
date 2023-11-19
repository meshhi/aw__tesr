package github.meshhi.pages.datasource_pages;

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
import io.qameta.allure.Step;

public class CreateDatasourcePage {
    public WebDriver driver;
    public AbstractBaseDriver baseDriver;
    @FindBy(xpath = "//button[text()=' Сохранить ']")
    private WebElement saveBtn;
    @FindBy(css = "*[formcontrolname=\"type\"]")
    private WebElement connType;
    @FindBy(css = "input[formcontrolname=\"name\"]")
    private WebElement inputConnName;
    @FindBy(css = "input[formcontrolname=\"host\"]")
    private WebElement inputHost;
    @FindBy(css = "input[formcontrolname=\"db\"]")
    private WebElement inputDb;
    @FindBy(css = "input[formcontrolname=\"username\"]")
    private WebElement inputUsername;
    @FindBy(css = "input[formcontrolname=\"password\"]")
    private WebElement inputPassword;
    @FindBy(xpath = "//button[text()=' Проверить подключение ']")
    private WebElement checkConnBtn;
    @FindBy(xpath = "//span[text()='ClickHouse']")
    private WebElement optionClickhouse;
    @FindBy(css = ".aw-select-query input[placeholder='Поиск']")
    private WebElement inputFind;
    @FindBy(xpath = "//span[text()='Файл']")
    private WebElement optionYandexDisk;
    @FindBy(xpath = "//span[text()='Ссылка']")
    private WebElement changeFileSrcType;
    @FindBy(css = "input[formcontrolname=\"file_address\"]")
    private WebElement inputYandexAddress;
    @FindBy(css = ".notifier__notification")
    private WebElement notification;
    
    public CreateDatasourcePage(AbstractBaseDriver baseDriver) {
        PageFactory.initElements(baseDriver.driver, this);
        this.baseDriver = baseDriver;
        this.driver = baseDriver.driver;
    }

    @Step(value = "Заполнение формы подключения к Clickhouse")
    public void fillFormClickhouse() {
        connType.click();
        optionClickhouse.click();
        inputConnName.sendKeys(ConfProperties.getProperty("db_conn"));
        inputHost.sendKeys(ConfProperties.getProperty("db_host"));
        inputDb.sendKeys(ConfProperties.getProperty("db"));
        inputUsername.sendKeys(ConfProperties.getProperty("db_username"));
        inputPassword.sendKeys(ConfProperties.getProperty("db_password"));
    }

    @Step(value = "Заполнение формы подключения к Яндекс диску")
    public void fillFormYandexDisk() {
        connType.click();
        inputFind.sendKeys("Файл");
        optionYandexDisk.click();
        inputConnName.sendKeys(ConfProperties.getProperty("yndx_conn"));
        changeFileSrcType.click();
        inputYandexAddress.sendKeys(ConfProperties.getProperty("yndx_address"));
        inputPassword.sendKeys(ConfProperties.getProperty("yndx_password"));
        saveDatasrc();
    }

    @Step(value = "Проверка соединения")
    public void checkConnDb() {
        checkConnBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".notifier__notification")));
    }

    @Step(value = "Сохранение источника")
    public void saveDatasrc() {
        saveBtn.click();
    }

    @Step(value = "Проверка уведомления об успешном подключении")
    public Boolean validLinkDatasrc() {
        String classes = notification.getAttribute("class");
        for (String c : classes.split(" ")) {
            if (c.equals("notifier__notification--error")) {
                return false;
            }
        };
        return true;
    }

    @Step(value = "Проверка уведомления о неуспешном подключении")
    public Boolean invalidLinkDatasrc() {
        String classes = notification.getAttribute("class");
        for (String c : classes.split(" ")) {
            if (c.equals("notifier__notification--error")) {
                return true;
            }
        };
        return false;
    }
}
