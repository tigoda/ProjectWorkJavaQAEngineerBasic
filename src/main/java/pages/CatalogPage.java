package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class CatalogPage extends AbsBasePage {
    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public CatalogPage pageHeaderShouldBeSameAs(String expectedHeader) {
        By headerSelection = By.cssSelector("h1 > div");
        waiters.waitForCondition(ExpectedConditions.presenceOfElementLocated(headerSelection));

        assertThat($(headerSelection).getText())
                .as("Header of page should be same as {%s}", expectedHeader)
                .isEqualTo(expectedHeader);

        return this;
    }
}
