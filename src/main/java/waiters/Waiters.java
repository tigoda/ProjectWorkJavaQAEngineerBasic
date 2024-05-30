package waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiters {

    private  WebDriver driver;

    public Waiters(WebDriver driver) {
        this.driver = driver;
    }

    public boolean waitForCondition(ExpectedCondition condition) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(condition);
            return true;
        } catch (TimeoutException ignored) {
            return false;
        }
    }

    public Boolean waitForElementNotVisible(By by) {
        return this.waitForCondition(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public Boolean waitForElementVisible(By by) {
        return this.waitForCondition(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
