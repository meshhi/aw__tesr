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
import io.qameta.allure.Step;
public class MainWindowPage {
    public WebDriver driver;
    public AbstractBaseDriver baseDriver;
    @FindBy(css = "*[href=\"/app/sources\"]")
    private WebElement goToDatasrcBtn;
    @FindBy(css = "*[href=\"/app/models\"]")
    private WebElement goToModelBtn;
    @FindBy(css = "*[href=\"/app/widgets\"]")
    private WebElement goToWidgetBtn;
    @FindBy(xpath = "//*[text()='Добавить']")
    private WebElement addBtn;
    @FindBy(css = "input[placeholder='Поиск']")
    private WebElement inputSearch;
    @FindBy(xpath = "//button[text()=' Удалить']")
    private WebElement deleteBtn;
    @FindBy(xpath = "//*[text()='Добавить логическую модель']")
    private WebElement optionAddLogicalModel;
    @FindBy(css = ".cascading-deletion__buttons button:nth-child(1)")
    private WebElement approveDeleteBtn;

    public MainWindowPage(AbstractBaseDriver baseDriver) {
        PageFactory.initElements(baseDriver.driver, this);
        this.baseDriver = baseDriver;
        this.driver = baseDriver.driver;
    }

    @Step(value = "Переход к источникам данных")
    public void clickGoToDatasrc() {
        goToDatasrcBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("app/sources"));
    }

    @Step(value = "Переход к моделям")
    public void clickGoToModel() {
        goToModelBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("app/models"));
    }

    @Step(value = "Выбор логической модели")
    public void chooseLogicaModel() {
        optionAddLogicalModel.click();
    }
    
    @Step(value = "Переход к виджетам")
    public void clickGoToWidget() {
        goToWidgetBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("app/widgets"));
    }

    @Step(value = "Добавить")
    public void clickAdd() {
        baseDriver.hoverElement(addBtn);
        addBtn.click();
    }

    @Step(value = "Поиск объекта {item}")
    public void searchItem(String item) {
        inputSearch.sendKeys(item);
        checkItem(item);
    }

    @Step(value = "Выбор чекбокса {item}")
    public void checkItem(String item) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='" + item + "']/preceding-sibling::*")));
        WebElement checkbox = driver.findElement(By.xpath("//td[text()='" + item + "']/preceding-sibling::*"));
        checkbox.click();
    }

    @Step(value = "Удалить объект")
    public void clickDelete() {
        deleteBtn.click();
        approveDeleteBtn.click();
    }

    @Step(value = "Проверка существования {item} в списке")
    public Boolean isItemInList(String item) {
        try {
            driver.findElement(By.xpath("//td[text()='" + item + "']"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
