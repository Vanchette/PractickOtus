package com.otus.driver;

import com.otus.exceptions.DriverTypeNoteSupported;
import org.openqa.selenium.WebDriver;

public interface IDriverFactory {
  WebDriver getDriver() throws DriverTypeNoteSupported;
}
