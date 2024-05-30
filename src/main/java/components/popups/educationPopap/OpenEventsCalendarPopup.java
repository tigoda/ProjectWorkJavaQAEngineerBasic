package components.popups.educationPopap;

import common.AbsCommon;
import components.popups.IPopup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class OpenEventsCalendarPopup extends AbsCommon implements IPopup {
    public OpenEventsCalendarPopup(WebDriver driver) {
        super(driver);
    }

    By allEventsLocator = By.xpath("//div/div/div[./span[text()='Все мероприятия']]");

    @Override
    public void popupShouldNotBeVisible() {
        assertThat(waiters.waitForElementNotVisible(allEventsLocator))
                .as("Error")
                .isTrue();
    }

    @Override
    public void popupShouldBeVisible() {
        assertThat(waiters.waitForElementVisible(allEventsLocator))
                .as("Error")
                .isTrue();
    }
}
