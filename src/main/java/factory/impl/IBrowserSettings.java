package factory.impl;

import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public interface IBrowserSettings {
     String isBrowserMode = System.getProperty("mode","fullscreen");
     AbstractDriverOptions settings();
}
