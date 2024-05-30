package components;

import common.AbsCommon;
import data.menu.CoursesMenuData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.СourseСardPage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CoursesFilterComponent extends AbsCommon {
    public CoursesFilterComponent(WebDriver driver) {
        super(driver);
    }

    private String filterMenuItemLocatorTemplate = "//*[@aria-hidden='false']//div[./label ='%s']";
    private By numberOfCoursesLocator = By.xpath("//div[text()='Каталог']/../../following-sibling::div//a");

    public CoursesFilterComponent checkCheckboxFilterStateShouldBeSameAs(CoursesMenuData coursesMenuData, boolean isExpectedState) {
        String locator = String.format(filterMenuItemLocatorTemplate, coursesMenuData.getName());

        assertThat(waiters.waitForCondition(ExpectedConditions.attributeToBe(
                $(By.xpath(locator)),
                "value",
                String.valueOf(isExpectedState)
        )))
                .as("Error")
                .isTrue();

        return this;
    }

    public CoursesFilterComponent checkNumberOfCourses(Integer count) {
        int factNumberOfCourses = getListOfElements(numberOfCoursesLocator).size();

        assertThat(factNumberOfCourses == count)
                .as(String.format("Expected %d courses, but was %d courses", factNumberOfCourses, count))
                .isTrue();

        return this;
    }

    public void checkAllAvailableCourses() {
        String catalogPage = driver.getCurrentUrl();
        List<WebElement> elements = getListOfElements(numberOfCoursesLocator);
        for (int i = 0; i < elements.size(); i++) {
            clickCourse(i);
            driver.get(catalogPage);
        }
    }

    public void clickCourse(int idx) {
        waiters.waitForElementVisible(numberOfCoursesLocator);
        WebElement element = getListOfElements(numberOfCoursesLocator).get(idx);
        String strRandomUrlCourseHref = element.getAttribute("href") + "/";
        actions.moveToElement(element).click().build().perform();
        waiters.waitForElementNotVisible(numberOfCoursesLocator);
        String srtCurrentUrl = driver.getCurrentUrl();

        assertThat(srtCurrentUrl.equals(strRandomUrlCourseHref))
                .as(String.format("Expected %s page, but was %s", strRandomUrlCourseHref, srtCurrentUrl))
                .isTrue();
        СourseСardPage courseСardPage = new СourseСardPage(driver);
        courseСardPage.checkContent();
    }
}
