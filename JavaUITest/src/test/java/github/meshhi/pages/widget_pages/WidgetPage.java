package github.meshhi.pages.widget_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import github.meshhi.utils.FieldClass;
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
    @FindBy(css = "app-widget-tab-data > *:nth-child(1)")
    private WebElement inputField;
    @FindBy(css = ".group-rows .group-body")
    private WebElement rowsContainer;
    @FindBy(css = ".group-columns .group-body")
    private WebElement columnsContainer;

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
        driver.findElement(By.xpath("//button[text()=\" Данные \"]")).click();
    }

    @Step(value = "Добавление поля {fieldName}")
    public void addField(String fieldName, FieldClass fieldClass) throws InterruptedException {
        Actions actions = new Actions(driver);
        for (int i = 0; i < 10; i++) {
            actions.moveToElement(inputField).click().sendKeys(Keys.chord(Keys.BACK_SPACE)).build().perform();
        };
        actions.moveToElement(inputField).click().sendKeys(fieldName).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + fieldName + "']")));
        WebElement block = driver.findElement(By.cssSelector(".cdk-drop-list.field-list > div:nth-child(1)"));
        actions.moveToElement(block).build().perform();

        switch(fieldClass) {
            case GROUP:
                WebElement group = driver.findElement(By.cssSelector(".button-group-action.hover > *:nth-child(2)"));
                actions.moveToElement(group).click().build().perform();
                break;
            case COLUMN:
                WebElement column = driver.findElement(By.cssSelector(".button-group-action.hover > *:nth-child(1)"));
                actions.moveToElement(column).click().build().perform();
                WebElement changeAggregate = driver.findElement(By.cssSelector("change-aggregate"));
                changeAggregate.click();
                WebElement sumAggregate = driver.findElement(By.cssSelector("*[role=\"menuitem\"]:last-child"));
                sumAggregate.click();
                break;
            default:
                break;
        }  
    }

    @Step(value = "Изменение вида диаграммы")
    public void changeView() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement viewTab = driver.findElement(By.xpath("//button[text()=\" Вид \"]"));
        actions.moveToElement(viewTab).click().build().perform();
        WebElement cycleView = driver.findElement(By.cssSelector(".widget-types__item:nth-child(15)"));
        actions.moveToElement(cycleView).click().build().perform();
    }
    
}
