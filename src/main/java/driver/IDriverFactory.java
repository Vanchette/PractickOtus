package driver;

import exceptions.DriverTypeNoteSupported;
import org.openqa.selenium.WebDriver;

public interface IDriverFactory {
  WebDriver getDriver() throws DriverTypeNoteSupported;
}
