package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.NewsPage;

import java.util.List;

public class DayNewsComponent extends AbsComponent<DayNewsComponent> {
  @FindBy(css = "[data-module='TrackBlocks'] table .daynews__item")
  private List<WebElement> dayNewsItems;

  public DayNewsComponent(WebDriver driver) {
    super(driver);
  }

  public NewsPage clickDayNewsItemByIndex(int index) {
    dayNewsItems.get(index + 1).click();
    return new NewsPage(driver);
  }
}
