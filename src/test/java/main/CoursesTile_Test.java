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
public class CoursesTile_Test {

  @Driver
  private WebDriver driver;

  @Test
  public void clickCourseItem() {
    new MainPage(driver).open();

    new CoockiesComponent(driver).clickCookieAcceptOk();

    new SpecializationsComponent(driver)
//        .clickCoursesItemByIndex(8);
            .clickCoursesItemByName("Специализация iOS Developer");
    //    new PopularCoursesComponent(driver)
    //        .clickCoursesItemByIndex(1);
    //            .teachersBlockShouldBeVisible();
    //        .getPageTitle());

  }
}
