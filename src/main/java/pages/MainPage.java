package pages;

import annotations.UrlPrefix;
import org.openqa.selenium.WebDriver;

@UrlPrefix("/")
public class MainPage extends AbsBasePage<MainPage> {
  public MainPage(WebDriver driver) {
    super(driver);
  }

}
