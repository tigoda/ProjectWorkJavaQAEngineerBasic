package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class СourseСardPage extends MainPage {

    private  By titleSelector = By.cssSelector("div>h1");
    private  By descriptionSelector = By.cssSelector("div>div>div>div>div>h2");
    private  By infoPanelSelector = By.cssSelector("section>div>div>div>p");
    public СourseСardPage(WebDriver driver) {
        super(driver);
    }

    public void checkContent() {
        checkTitle();
        checkDescription();
        checkFormat();
        checkDurationOfTraining();
    }

    public void checkDurationOfTraining() {
        List<WebElement> elements = getListOfElements(infoPanelSelector);
        String expected = "месяц";
        assertThat(elements)
                .extracting(WebElement::getText)
                .describedAs("None of elements contains sub-string %s!", expected)
                .anyMatch(text -> text.contains(expected));
    }

    public void checkFormat() {
        List<WebElement> elements = getListOfElements(infoPanelSelector);
        String expected = "Онлайн";
        assertThat(elements)
                .extracting(WebElement::getText)
                .describedAs("None of elements contains sub-string %s!", expected)
                .anyMatch(text -> text.contains(expected));
    }

    public void checkDescription() {
        List<WebElement> elements = getListOfElements(descriptionSelector);

        String pattern = "(?i)(\\W|^)(Необходимые\\sзнания|Для\\sкого\\sэтот" +
                "\\sкурс|Для\\sкого\\sподходит\\sспециализация)(\\W|$)";
        assertThat(elements)
                .extracting(WebElement::getText)
                .describedAs("The course %s card does not contain a description", $(titleSelector).getText())
                .anyMatch(text -> Pattern.matches(pattern, text));
    }

    public void checkTitle() {
        assertThat(!$(titleSelector).getText().isEmpty());
    }
}
