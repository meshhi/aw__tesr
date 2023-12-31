package github.meshhi.tests.remote.datasource_tests;
import org.junit.Test;

import github.meshhi.pages.MainWindowPage;
import github.meshhi.pages.datasource_pages.CreateDatasourcePage;
import github.meshhi.tests.remote.BaseStartTestDecorator;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;


import org.junit.Assert;
// comment
public class DataSrcCreateTest extends BaseStartTestDecorator {
    @Epic(value = "AW UI тесты")
    @Feature(value = "Источники")
    @Story(value = "Создание источника")
    @Test
    @Description(value = "Тест проверяет неуспешное подключение к источнику ClickHouse")
    public void testDatasrcClickhouse() throws InterruptedException {
        MainWindowPage mainWindowPage = new MainWindowPage(driver);
        mainWindowPage.clickGoToDatasrc();
        mainWindowPage.clickAdd();
        CreateDatasourcePage createDatasourcePage = new CreateDatasourcePage(driver);
        createDatasourcePage.fillFormClickhouse();
        createDatasourcePage.checkConnDb();
        Assert.assertTrue(createDatasourcePage.invalidLinkDatasrc());
    }

    @Epic(value = "AW UI тесты")
    @Feature(value = "Источники")
    @Story(value = "Создание источника")
    @Test
    @Description(value = "Тест проверяет создание файлового источника Яндекс диск")
    public void testDatasrcYandexDisk() throws InterruptedException {
        MainWindowPage mainWindowPage = new MainWindowPage(driver);
        mainWindowPage.clickGoToDatasrc();
        mainWindowPage.clickAdd();
        CreateDatasourcePage createDatasourcePage = new CreateDatasourcePage(driver);
        createDatasourcePage.fillFormYandexDisk();
        Thread.sleep(5000);
        Assert.assertTrue(driver.getCurrentURL().contains("/app/sources"));
    }
}
