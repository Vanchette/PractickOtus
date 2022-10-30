package com.otus.steps.common;

import com.google.inject.Inject;
import com.otus.diconfig.GuiceScoped;
import com.otus.driver.DriverFactory;
import io.cucumber.java.ru.Пусть;

public class CommonPageSteps {

  @Inject
  private DriverFactory driverFactory;
  @Inject
  private GuiceScoped guiceScoped;

  @Пусть("Открываем браузер {string}")
  public void setGuiceScoped(String browser){
    guiceScoped.browserName = browser;
    guiceScoped.driver = driverFactory.getDriver();
  }
}
