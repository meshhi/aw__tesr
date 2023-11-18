package github.meshhi.webdriver;
import github.meshhi.utils.ConfProperties;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseDriver extends AbstractBaseDriver {
    public BaseDriver() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        setDriverSettings();

        
    }
}
