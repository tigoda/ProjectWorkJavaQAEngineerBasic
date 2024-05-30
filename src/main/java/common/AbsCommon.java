package common;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import waiters.Waiters;

import java.util.ArrayList;
import java.util.List;

public abstract class AbsCommon {

    protected WebDriver driver = null;
    protected Waiters waiters = null;
    protected Actions actions = null;


    public AbsCommon(WebDriver driver) {
        this.driver = driver;
        this.waiters = new Waiters(driver);
        this.actions = new Actions(driver);

        PageFactory.initElements(driver, this);
    }

    public WebElement $(By locator) {
        return driver.findElement(locator);
    }


    public List<WebElement> getListOfElements(By by) {
        return driver.findElements(by);
    }

    public List<String> getListTextValueOfElements(By by) {
        List<String> listString = new ArrayList<>();
        getListOfElements(by).forEach(element -> listString.add(element.getText()));
        return listString;
    }
}
