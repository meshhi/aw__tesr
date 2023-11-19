package github.meshhi.pages.model_pages;

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

public class ModelPage {
    public WebDriver driver;
    public AbstractBaseDriver baseDriver;
    @FindBy(css = ".editable-input")
    private WebElement inputTitle;
    @FindBy(css = ".name.green + button")
    private WebElement addModelBlock;
    @FindBy(css = ".schema")
    private WebElement schemaWindow;
    @FindBy(xpath = "//button[text()=' Добавить ']")
    private WebElement addModel;

    public ModelPage(AbstractBaseDriver baseDriver) {
        PageFactory.initElements(baseDriver.driver, this);
        this.baseDriver = baseDriver;
        this.driver = baseDriver.driver;
    }

    @Step(value = "Переименование модели")
    public void renameModel(String title) {
        inputTitle.click();
        inputTitle.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputTitle.sendKeys(title);
    }

    @Step(value = "Добавление блока с другой моделью")
    public void addBlockModel(String modelName) {
        addModelBlock.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//tr[1]//td[text()='" + modelName + "']/preceding-sibling::*")));
        WebElement checkbox = driver
                .findElement(By.xpath("//tr[1]//td[text()='" + modelName + "']/preceding-sibling::*"));
        checkbox.click();
        addModel.click();
    }

    @Step(value = "Перетаскивание блока на схему")
    public void dndBlockOnScheme(String modelName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + modelName + "']")));
        WebElement block = driver.findElement(By.xpath("//span[text()='" + modelName + "']"));
        
        Actions act = new Actions(driver);
        act.dragAndDrop(block, schemaWindow).build().perform();

        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[title=\"" + modelName + "\"]")));
    }

    @Step(value = "Проверка, что блок добавлен на схему")
    public Boolean checkBlockOnSchema(String blockName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[title=\"" + blockName + "\"]")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSchema() {
        schemaWindow.click();
    }

    public String getTitle() {
        return inputTitle.getAttribute("value");
    }
}
