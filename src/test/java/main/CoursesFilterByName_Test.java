package main;

import annotations.Driver;
import components.CoockiesComponent;
import components.SpecializationsComponent;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

@ExtendWith(UIExtension.class)
public class CoursesFilterByName_Test {
  @Driver
  private WebDriver driver;

  private final String findNameCourse = "Специализация iOS Developer";

  @Test
  public void clickCourseItem() {
    new MainPage(driver).open();
    new CoockiesComponent(driver).clickCookieAcceptOk();
    new SpecializationsComponent(driver)
        .clickCoursesItemByName(findNameCourse)
        .checkCourseTitle(findNameCourse);

  }
}
