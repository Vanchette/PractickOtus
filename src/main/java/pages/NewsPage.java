package pages;

import annotations.UrlPrefix;
import annotations.UrlTemplate;
import org.openqa.selenium.WebDriver;

@UrlTemplate("/{1}/{2}")
@UrlPrefix("/news")
public class NewsPage extends AbsBasePage<NewsPage> {
  public NewsPage(WebDriver driver) {
    super(driver);
  }
}
