package github.meshhi.tests.remote.model_tests;
import org.junit.Test;

import github.meshhi.pages.MainWindowPage;
import github.meshhi.pages.model_pages.ModelPage;
import github.meshhi.tests.remote.BaseStartTestDecorator;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Assert;

public class ModelCreateTest extends BaseStartTestDecorator {
    @Epic(value = "AW UI тесты")
    @Feature(value = "Модели")
    @Story(value = "Создание модели")
    @Test
    @Description(value = "Проверяется создание модели")
    public void testCreateLogicalModel() throws InterruptedException {
        Assert.assertTrue(driver.getCurrentURL().contains("/app/sources"));
        MainWindowPage mainWindowPage = new MainWindowPage(driver);
        mainWindowPage.clickGoToModel();
        mainWindowPage.clickAdd();
        mainWindowPage.chooseLogicaModel();
        ModelPage modelPage = new ModelPage(driver);
        modelPage.renameModel("Test model Selenium");
        modelPage.clickSchema();
        Thread.sleep(1000);
        Assert.assertTrue(modelPage.getTitle().contains("Test model Selenium"));
    }

    @Epic(value = "AW UI тесты")
    @Feature(value = "Модели")
    @Story(value = "Создание модели")
    @Test
    @Description(value = "Проверяется добавление блока на схему модели")
    public void testAddBlock() throws InterruptedException {
        testCreateLogicalModel();
        ModelPage modelPage = new ModelPage(driver);
        modelPage.addBlockModel("Анализ продаж");
        modelPage.dndBlockOnScheme("Анализ продаж");
        Assert.assertTrue(modelPage.checkBlockOnSchema("Анализ продаж"));
    }
}
