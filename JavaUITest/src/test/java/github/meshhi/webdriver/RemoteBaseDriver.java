package github.meshhi.webdriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class RemoteBaseDriver extends AbstractBaseDriver {
    private URL gridURL;
    private String selenoidURL = "http://localhost:4444/";

    public RemoteBaseDriver() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        gridURL = getURL();         
        options.setCapability("browserVersion", "115.0"); 
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("enableVNC", true);
        }});
        driver = new RemoteWebDriver(gridURL, options);
        setDriverSettings();
    }

    private URL getURL() throws MalformedURLException {
        return new URL(new StringBuilder().append(selenoidURL).append("wd").append(File.separatorChar).append("hub").toString());
    }
}