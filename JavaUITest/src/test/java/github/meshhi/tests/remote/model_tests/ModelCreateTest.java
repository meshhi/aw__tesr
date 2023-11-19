package github.meshhi.tests.remote.model_tests;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import github.meshhi.pages.MainWindowPage;
import github.meshhi.pages.model_pages.ModelPage;
import github.meshhi.tests.remote.BaseStartTestDecorator;
import github.meshhi.utils.ConfProperties;
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
        String modelName = "Test model Selenium";
        modelPage.renameModel(modelName);
        modelPage.clickSchema();
        Thread.sleep(1000);
        Assert.assertTrue(modelPage.getTitle().contains(modelName));
    }

    @Epic(value = "AW UI тесты")
    @Feature(value = "Модели")
    @Story(value = "Удаление модели")
    @Test
    @Description(value = "Проверяется удаление модели")
    public void testDeleteModel() throws InterruptedException {
        Assert.assertTrue(driver.getCurrentURL().contains("/app/sources"));
        MainWindowPage mainWindowPage = new MainWindowPage(driver);
        mainWindowPage.clickGoToModel();
        mainWindowPage.searchItem("Test model Selenium");
        mainWindowPage.clickDelete();
        Thread.sleep(1000);
        Assert.assertTrue(!mainWindowPage.isItemInList("Test model Selenium"));
    }

    @Epic(value = "AW UI тесты")
    @Feature(value = "Модели")
    @Story(value = "Создание модели")
    @Test
    @Description(value = "Проверяется добавление блока на схему модели")
    public void testAddBlock() throws InterruptedException {
        testCreateLogicalModel();
        ModelPage modelPage = new ModelPage(driver);
        String blockName = "Анализ продаж";
        modelPage.addBlockModel(blockName);
        modelPage.dndBlockOnScheme(blockName);
        Assert.assertTrue(modelPage.checkBlockOnSchema(blockName));
    }

    @Epic(value = "AW UI тесты")
    @Feature(value = "Модели")
    @Story(value = "Создание модели")
    @Test
    @Description(value = "Проверяется старт процесса загрузки данных модели")
    public void loadModel() throws InterruptedException {
        testAddBlock();
        ModelPage modelPage = new ModelPage(driver);
        modelPage.synchroModel();
        Assert.assertTrue(driver.getCurrentURL().contains("app/models"));
    }
}
