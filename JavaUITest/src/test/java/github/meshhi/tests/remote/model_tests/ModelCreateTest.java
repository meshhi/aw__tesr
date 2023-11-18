package github.meshhi.tests.remote.model_tests;
import org.junit.Test;

import github.meshhi.pages.MainWindowPage;
import github.meshhi.tests.remote.BaseStartTestDecorator;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;


import org.junit.Assert;
// comment
public class ModelCreateTest extends BaseStartTestDecorator {
    @Epic(value = "AW UI тесты")
    @Feature(value = "Источники")
    @Story(value = "Создание источника")
    @Test
    @Description(value = "Проверяется создание источника")
    public void createModel() throws InterruptedException {
        Assert.assertTrue(driver.getCurrentURL().contains("/app/sources"));
        MainWindowPage mainWindowPage = new MainWindowPage(driver);
        mainWindowPage.clickGoToModel();
    }
}
