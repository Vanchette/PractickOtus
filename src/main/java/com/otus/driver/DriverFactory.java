package com.otus.driver;

import com.otus.driver.impl.ChromeWebDriver;
import com.otus.exceptions.DriverTypeNoteSupported;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Locale;

public class DriverFactory implements IDriverFactory {
  private final String browserType = System.getProperty("browser", "chrome").toLowerCase(Locale.ROOT);

  @Override
  public EventFiringWebDriver getDriver() {
    switch (this.browserType) {
      case "chrome": {
        return new EventFiringWebDriver(new ChromeWebDriver().newDriver());
      }
      default:
        try {
          throw new DriverTypeNoteSupported(this.browserType);
        } catch (DriverTypeNoteSupported ex) {
          ex.printStackTrace();
          return null;
        }
    }
  }
}
