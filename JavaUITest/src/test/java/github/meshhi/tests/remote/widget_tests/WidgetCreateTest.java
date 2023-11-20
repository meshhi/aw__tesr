package github.meshhi.tests.remote.widget_tests;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import github.meshhi.pages.MainWindowPage;
import github.meshhi.pages.model_pages.ModelPage;
import github.meshhi.pages.widget_pages.WidgetPage;
import github.meshhi.tests.remote.BaseStartTestDecorator;
import github.meshhi.utils.ConfProperties;
import github.meshhi.utils.FieldClass;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Assert;

public class WidgetCreateTest extends BaseStartTestDecorator {
    @Epic(value = "AW UI тесты")
    @Feature(value = "Виджеты")
    @Story(value = "Создание виджеты")
    @Test
    @Description(value = "Проверяется создание виджета")
    public void testCreateWidget() throws InterruptedException {
        Assert.assertTrue(driver.getCurrentURL().contains("/app/sources"));
        MainWindowPage mainWindowPage = new MainWindowPage(driver);
        mainWindowPage.clickGoToWidget();
        mainWindowPage.clickAdd();
        WidgetPage widgetPage = new WidgetPage(driver);
        String modelName = "Анализ продаж";
        widgetPage.chooseModel(modelName);
        widgetPage.waitWidgetCreated();
        Assert.assertTrue(widgetPage.getTitle().contains("Новый виджет"));
    }

    @Epic(value = "AW UI тесты")
    @Feature(value = "Виджеты")
    @Story(value = "Создание виджеты")
    @Test
    @Description(value = "Переименовывание виджета")
    public void testRenameDiagram() throws InterruptedException {
        testCreateWidget();
        WidgetPage widgetPage = new WidgetPage(driver);
        String widgetName = "test_cycle";
        widgetPage.renameWidget(widgetName);
        Assert.assertTrue(widgetPage.getTitle().contains(widgetName));
    }

    @Epic(value = "AW UI тесты")
    @Feature(value = "Виджеты")
    @Story(value = "Создание виджеты")
    @Test
    @Description(value = "Создание кольцевой диаграммы")
    public void testCreateCycleDiagram() throws InterruptedException {
        testRenameDiagram();
        WidgetPage widgetPage = new WidgetPage(driver);
        widgetPage.addField("Клиенты", FieldClass.GROUP);
        widgetPage.addField("Выручка", FieldClass.COLUMN);
        widgetPage.changeView();
        String widgetName = "test_cycle__success";
        widgetPage.renameWidget(widgetName);
        Assert.assertTrue(widgetPage.getTitle().contains(widgetName));
    }
}
