package com.otus.pages;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.inject.Inject;
import com.otus.annotations.UrlPrefix;
import com.otus.annotations.UrlTemplate;
import com.otus.diconfig.GuiceScoped;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Locale;

@UrlTemplate("/{1}/{2}")
@UrlPrefix("/lessons")
public class LessonsPage extends AbsBasePage<LessonsPage> {
  //  @FindBy(css = ".course-header2__title")
  @FindBy(css = "head title")
  private WebElement headTitle;

  @Inject
  public LessonsPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public void titleShouldBeSameAs(String title) {
    assertThat(getPageTitle().toLowerCase(Locale.ROOT).trim())
        .as(String.format("Title on lesson page should be %s", title))
        .isEqualTo(title.toLowerCase(Locale.ROOT).trim());
  }

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
