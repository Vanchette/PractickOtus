package pages;

import static org.assertj.core.api.Assertions.assertThat;

import annotations.UrlPrefix;
import annotations.UrlTemplate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Locale;

@UrlTemplate("/{1}/{2}")
@UrlPrefix("/lessons")
public class LessonsPage extends AbsBasePage<LessonsPage> {
  public LessonsPage(WebDriver driver) {
    super(driver);
  }

  //  @FindBy(css = ".course-header2__title")
  @FindBy(css = "head title")
  private WebElement headTitle;

  public LessonsPage headTitleShouldBeVisible() {
    assertThat(standartWaiter.waitForElementVisible(headTitle))
        .as("Error")
        .isTrue();
    return this;
  }

  public LessonsPage checkCourseTitle(String title) {
    assertThat(title.toLowerCase(Locale.ROOT).trim())
        .isEqualTo(getPageTitle().toLowerCase(Locale.ROOT).trim());
    return this;
  }

  public String getPageTitle() {
    return driver.getTitle();
  }

}
