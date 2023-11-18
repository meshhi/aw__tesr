package github.meshhi.tests.remote;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;

import github.meshhi.pages.LoginPage;
import github.meshhi.webdriver.AbstractBaseDriver;
import github.meshhi.webdriver.BaseDriver;
import github.meshhi.webdriver.RemoteBaseDriver;
import io.qameta.allure.Step;
import github.meshhi.utils.ConfProperties;
import github.meshhi.utils.Warehouse;

public class BaseStartTestDecorator {
    public AbstractBaseDriver driver;

    @Before
    @Step(value="Test init with new driver and authorization pass")
    public void setup() throws InterruptedException, MalformedURLException {
        // driver = new RemoteBaseDriver();
        driver = new BaseDriver();
        authorize();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public void authorize() throws InterruptedException {
        driver.goToURL(Warehouse.getAwUrl());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorize();
    }
}