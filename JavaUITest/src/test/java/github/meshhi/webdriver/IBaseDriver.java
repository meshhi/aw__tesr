package github.meshhi.webdriver;

import org.openqa.selenium.WebElement;

public interface IBaseDriver {
    public void goToURL(String url);
    public void quit();
    public String getCurrentURL();
    public void setDriverSettings();
    public void hoverElement(WebElement element);
}
