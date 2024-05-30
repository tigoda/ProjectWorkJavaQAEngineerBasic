import components.CoursesFilterComponent;
import components.HeaderComponent;
import components.popups.educationPopap.EventsCalendarPopup;
import components.popups.educationPopap.OpenEventsCalendarPopup;
import data.EducationData;
import data.menu.CoursesMenuData;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.EventsCalendarPage;
import pages.MainPage;


public class OtusWebsite_Test {

    private WebDriver driver = null;

    @BeforeEach
    public void init() {
        driver = new WebDriverFactory().create();
    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void coursesCategoriesMenu() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        mainPage
                .clickCoursesCategoriesMenu(CoursesMenuData.TESTING)
                .pageHeaderShouldBeSameAs("Каталог");

        CoursesFilterComponent coursesFilterComponent = new CoursesFilterComponent(driver);

        coursesFilterComponent
                .checkCheckboxFilterStateShouldBeSameAs(CoursesMenuData.TESTING, true)
                .checkNumberOfCourses(10)
                .checkAllAvailableCourses();
    }

    @Test
    public void eventsCalendarMenu() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickEducationsCategories();

        EventsCalendarPopup eventsCalendarPopup = new EventsCalendarPopup(driver);
        eventsCalendarPopup.popupShouldBeVisible();

        new HeaderComponent(driver).clickEducationButton(EducationData.EVENTSCALENDAR);
        eventsCalendarPopup.popupShouldNotBeVisible();

        EventsCalendarPage eventsCalendarPage = new EventsCalendarPage(driver);

        eventsCalendarPage
                .pageHeaderShouldBeSameAs("Календарь мероприятий")
                .checkEventCardsAreDisplayed();

        eventsCalendarPage.checkEventDate();

        eventsCalendarPage.clickAllEvents();
        OpenEventsCalendarPopup openEventsCalendarPopup = new OpenEventsCalendarPopup(driver);
        openEventsCalendarPopup.popupShouldBeVisible();


        eventsCalendarPage.clickOpenEvents();
        openEventsCalendarPopup.popupShouldNotBeVisible();

        eventsCalendarPage.sortingCheckOpenWebinar();
    }
}
