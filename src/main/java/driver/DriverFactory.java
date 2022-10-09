package driver;

import driver.impl.ChromeWebDriver;
import exceptions.DriverTypeNoteSupported;
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
