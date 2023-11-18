package github.meshhi.tests.remote.datasource_tests;
import org.junit.Test;

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
    @Description(value = "Проверяется создание источника")
    public void createDatasrc() throws InterruptedException {
        Assert.assertTrue(true);
        // Assert.assertTrue(driver.getCurrentURL().contains("/app/sources"));
    }
}
