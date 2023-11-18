package github.meshhi.webdriver;

public interface IBaseDriver {
    public void goToURL(String url);
    public void quit();
    public String getCurrentURL();
    public void setDriverSettings();
}
