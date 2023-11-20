package github.meshhi.pages.widget_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import github.meshhi.webdriver.AbstractBaseDriver;
import github.meshhi.utils.ConfProperties;
import github.meshhi.utils.Warehouse;
import io.qameta.allure.Step;

public class WidgetPage {
    public WebDriver driver;
    public AbstractBaseDriver baseDriver;
    @FindBy(css = "page-title[name=\"Модели\"] + * input[placeholder='Поиск']")
    private WebElement inputSearchModel;
    @FindBy(xpath = "//button[text()=' Выбрать модель ']")
    private WebElement chooseModelBtn;
    @FindBy(xpath = "//button[text()=' Добавить ']")
    private WebElement approveChooseModelBtn;
    @FindBy(css = ".editable-input")
    private WebElement inputTitle;

    public WidgetPage(AbstractBaseDriver baseDriver) {
        PageFactory.initElements(baseDriver.driver, this);
        this.baseDriver = baseDriver;
        this.driver = baseDriver.driver;
    }

    @Step(value = "Выбор модели")
    public void chooseModel(String modelName) {
        chooseModelBtn.click();
        inputSearchModel.sendKeys(modelName);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//tr[1]//td[text()='" + modelName + "']/preceding-sibling::*")));
        WebElement checkbox = driver
                .findElement(By.xpath("//tr[1]//td[text()='" + modelName + "']/preceding-sibling::*"));
        checkbox.click();
        approveChooseModelBtn.click();
    }

    @Step(value = "Ожидание готовности виджета к редактированию")
    public void waitWidgetCreated() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//*[text()='Виджет не сформирован']")));
    }

    public String getTitle() {
        return inputTitle.getAttribute("value");
    }

    @Step(value = "Переименовывание виджета в {widgetTitle}")
    public void renameWidget(String widgetTitle) {
        inputTitle.click();
        inputTitle.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputTitle.sendKeys(widgetTitle);
        driver.findElement(By.xpath("//*[text()='Виджет не сформирован']")).click();
    }
}
