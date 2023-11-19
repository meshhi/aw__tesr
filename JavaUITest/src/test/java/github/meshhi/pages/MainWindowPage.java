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
    @FindBy(xpath = "//button[text()='Добавить']")
    private WebElement addBtn;

    public MainWindowPage(AbstractBaseDriver baseDriver) {
        PageFactory.initElements(baseDriver.driver, this);
        this.baseDriver = baseDriver;
        this.driver = baseDriver.driver;
    }

    @Step(value = "Переход к источникам данных")
    public void clickGoToDatasrc() {
        goToDatasrcBtn.click();
    }

    @Step(value = "Переход к моделям")
    public void clickGoToModel() {
        goToModelBtn.click();
    }

    @Step(value = "Добавить")
    public void clickAdd() {
        baseDriver.hoverElement(addBtn);
        addBtn.click();
    }
}