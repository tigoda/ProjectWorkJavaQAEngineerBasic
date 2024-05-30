package components.popups.educationPopap;

import common.AbsCommon;
import components.popups.IPopup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class EventsCalendarPopup extends AbsCommon implements IPopup {
    public EventsCalendarPopup(WebDriver driver) {
        super(driver);
    }
    private String eventsCalendarComponentLocator = "//a[text()='Календарь мероприятий']";


    @Override
    public void popupShouldNotBeVisible() {
        assertThat(waiters.waitForElementNotVisible(By.xpath(eventsCalendarComponentLocator)))
                .as("Error")
                .isTrue();
    }

    @Override
    public void popupShouldBeVisible() {
        assertThat(waiters.waitForElementVisible(By.xpath(eventsCalendarComponentLocator)))
                .as("Error")
                .isTrue();
    }
}
