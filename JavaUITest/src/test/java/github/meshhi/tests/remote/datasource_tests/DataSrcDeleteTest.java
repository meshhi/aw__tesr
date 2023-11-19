package github.meshhi.tests.remote.datasource_tests;
import org.junit.Test;
import github.meshhi.pages.MainWindowPage;
import github.meshhi.tests.remote.BaseStartTestDecorator;
import github.meshhi.utils.ConfProperties;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Assert;

public class DataSrcDeleteTest extends BaseStartTestDecorator {
    @Epic(value = "AW UI тесты")
    @Feature(value = "Источники")
    @Story(value = "Удаление источника")
    @Test
    @Description(value = "Тест проверяет удаление файлового источника Яндекс диск")
    public void testDatasrcYandexDisk() throws InterruptedException {
        MainWindowPage mainWindowPage = new MainWindowPage(driver);
        mainWindowPage.clickGoToDatasrc();
        mainWindowPage.searchItem(ConfProperties.getProperty("yndx_conn"));
        mainWindowPage.clickDelete();
        Assert.assertTrue(!mainWindowPage.isItemInList(ConfProperties.getProperty("yndx_conn")));
    }
}
