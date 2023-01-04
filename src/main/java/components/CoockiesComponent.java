package components;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CoockiesComponent extends AbsComponent<CoockiesComponent> {

  @FindBy(css = ".js-cookie-accept.cookies__button")
  WebElement elementOkButton;

  public CoockiesComponent(WebDriver driver) {
    super(driver);
  }

  public void clickCookieAcceptOk() {
    assertThat(standartWaiter.waitForElementVisible(elementOkButton))
        .as("Error")
        .isTrue();
    elementOkButton.click();
  }
}
