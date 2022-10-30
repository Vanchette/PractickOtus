package com.otus.components;

import com.google.inject.Inject;
import com.otus.diconfig.GuiceScoped;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CoockiesComponent extends AbsComponent<CoockiesComponent> {

  @FindBy(css = ".js-cookie-accept.cookies__button")
  WebElement elementOkButton;

  @Inject
  public CoockiesComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public void clickCookieAcceptOk() {
    Assertions.assertThat(standartWaiter.waitForElementVisible(elementOkButton))
        .as("Error")
        .isTrue();
    elementOkButton.click();
  }
}
