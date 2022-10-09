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
public class CoursesFilterByDate_Test {

  @Driver
  WebDriver driver;

  @Test
  public void clickCourseFilterByMinDate(){
    new MainPage(driver).open();
    new CoockiesComponent(driver).clickCookieAcceptOk();
    new SpecializationsComponent(driver)
        .clickCoursesItemByMinDate();
  }

  @Test
  public void clickCourseFilterByMaxDate(){
    new MainPage(driver).open();
    new CoockiesComponent(driver).clickCookieAcceptOk();
    new SpecializationsComponent(driver)
        .clickCoursesItemByMaxDate();
  }
}
