package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class EventsCalendarPage extends MainPage {

    public EventsCalendarPage(WebDriver driver) {
        super(driver);
    }

    public EventsCalendarPage pageHeaderShouldBeSameAs(String expectedHeader) {
        By headerSelection = By.cssSelector("body>div>div>div>section>div");
        waiters.waitForCondition(ExpectedConditions.presenceOfElementLocated(headerSelection));

        assertThat($(headerSelection).getText())
                .as("Header of page should be same as {%s}", expectedHeader)
                .isEqualTo(expectedHeader);
        return this;
    }

    public void checkEventCardsAreDisplayed() {
        By eventsSelector = By.cssSelector("a[href^='https://otus.ru/lessons/']");
        assertThat(waiters.waitForCondition(ExpectedConditions.visibilityOfAllElements(
                getListOfElements(eventsSelector))))
                .as("Error")
                .isTrue();
    }

    public void checkEventDate() {
        By dateSelector = By.cssSelector("div>div>span>span~span");
        List<String> eventDataAndTime = getListTextValueOfElements(dateSelector);
        List<String> eventData = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM");
        String text = dtf.format(LocalDate.now());

        eventDataAndTime.forEach(e -> {
            if (!(e.equals("Сейчас в эфире") || e.contains(":")))
                eventData.add(e);
        });

        eventData.forEach(e -> {
            try {
                if (new SimpleDateFormat("dd MMMM").parse(text)
                        .before(new SimpleDateFormat("dd MMMM").parse(e)))
                    assertThat(true).isTrue();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void clickAllEvents() {
        By allEventsLocator = By.xpath("//div/div/div[./span[text()='Все мероприятия']]");
        $(allEventsLocator).click();
    }

    public EventsCalendarPage clickOpenEvents() {
        By openEventsLocator = By.xpath("//section/header/div/div//div/a[@title='Открытый вебинар']");
        $(openEventsLocator).click();
        return new EventsCalendarPage(driver);
    }

    public void sortingCheckOpenWebinar() {
        By sortingOpenWebinar = By.xpath("//a/div/div/div/div[text()='Открытый вебинар']");
        Set<String> uniqueValue = new HashSet<>(getListTextValueOfElements(sortingOpenWebinar));
        assertThat(uniqueValue.size() == 1)
                .as(String.format("Sorting open webinars not successfully %s", uniqueValue))
                .isTrue();
    }
}
