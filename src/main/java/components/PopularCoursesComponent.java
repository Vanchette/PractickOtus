package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LessonsPage;

import java.util.List;

public class PopularCoursesComponent extends AbsComponent<PopularCoursesComponent> {

  @FindBy(css = ".container.container-lessons>.lessons>.lessons__new-item .lessons__new-item-container")
  private List<WebElement> courses;

  public PopularCoursesComponent(WebDriver driver) {
    super(driver);
  }

  public LessonsPage clickCoursesItemByIndex(int i) {
    WebElement tile = courses.get(i - 1);
    standartWaiter.waitForCondition(ExpectedConditions.stalenessOf(tile));
    tile.click();
    return page(LessonsPage.class);
  }
}
