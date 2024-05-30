package components;

import common.AbsCommon;
import data.EducationData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HeaderComponent extends AbsCommon {
    public HeaderComponent(WebDriver driver) {
        super(driver);
    }


    public void clickEducationButton(EducationData educationData) {
        String educationButton = "//a[text()='%s']";
        switch (educationData) {
            case EVENTSCALENDAR: {
                $(By.xpath(String.format(educationButton, educationData.getName()))).click();
            }
            case INTENSIEVS:
                break;
            case COURSELAUNCHCALENDAR:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + educationData);
        }
    }
}
