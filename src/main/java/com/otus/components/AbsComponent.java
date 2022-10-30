package com.otus.components;

import com.otus.actions.CommonActions;
import com.otus.annotations.Component;
import com.otus.diconfig.GuiceScoped;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class AbsComponent<T> extends CommonActions<T> {
  protected String baseLocator;
  protected Actions actions;

  {
    this.standartWaiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(getComponentLocator()));
  }

  public AbsComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
    actions = new Actions(driver);
    PageFactory.initElements(driver, this);
  }

  private By getComponentLocator() {
    Component component = getClass().getAnnotation(Component.class);

    if (component != null) {
      String value = component.value();

      baseLocator = value;

      if (value.startsWith("/")) {
        return By.xpath(value);
      }
      return By.cssSelector(value);
    }

    return null;
  }
}
