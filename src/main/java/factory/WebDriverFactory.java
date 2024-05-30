package factory;


import data.BrowserNameData;
import factory.impl.ChromeSettings;
import factory.impl.FirefoxSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Locale;

public class WebDriverFactory {

    private String browserName = System.getProperty("browser.name", "chrome");
    private BrowserNameData browserNameData = BrowserNameData.valueOf(browserName.toUpperCase(Locale.ROOT));

    public WebDriver create() {
        switch (browserNameData) {
            case CHROME: {
                return new ChromeDriver((ChromeOptions) new ChromeSettings().settings());
            }
            case FIREFOX: {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver((FirefoxOptions) new FirefoxSettings().settings());
            }
        }
        return null;
    }

    public void init() {
        switch (browserNameData) {
            case CHROME: {
                WebDriverManager.chromedriver().setup();
            }
        }
    }
}
