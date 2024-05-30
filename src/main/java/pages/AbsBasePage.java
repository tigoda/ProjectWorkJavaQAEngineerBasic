package pages;

import common.AbsCommon;
import org.openqa.selenium.WebDriver;

public abstract class AbsBasePage extends AbsCommon {

    private final String BASE_URL = System.getProperty("base.url").endsWith("/") ?
            System.getProperty("base.url").replaceAll("/$", "") :
            System.getProperty("base.url");
    private String pass = "";

    public AbsBasePage(WebDriver driver, String pass) {
        super(driver);
        this.pass = pass.startsWith("/") ? pass : "/" + pass;
    }

    public AbsBasePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + pass);
    }
}
