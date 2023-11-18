package github.meshhi.webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;


public abstract class AbstractBaseDriver implements IBaseDriver {
    public WebDriver driver;

    public AbstractBaseDriver() {}

    @Step(value="Переход на {url}")
    public void goToURL(String url) {
        driver.get(url);
    }

    @Step(value="Закрытие сессии браузера")
    public void quit() {
        driver.quit();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public void setDriverSettings() {
        driver.manage().window().maximize();
        //wait for WebElement
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //wait for loading page
        driver.manage().timeouts().pageLoadTimeout(10000,
        TimeUnit.MILLISECONDS);
        //wait for an asynchronous script to finish execution
        driver.manage().timeouts().setScriptTimeout(5000,
        TimeUnit.MILLISECONDS);
    };
}
