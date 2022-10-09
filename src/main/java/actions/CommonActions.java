package actions;

import static pages.AbsBasePage.convertInstanceOfObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import waiters.StandartWaiter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public abstract class CommonActions<T> {
  protected WebDriver driver;
  protected StandartWaiter standartWaiter;

  public CommonActions(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    standartWaiter = new StandartWaiter(driver);
  }

  public <T> T page(Class<T> clazz) {
    try {
      Constructor constructor = clazz.getConstructor(WebDriver.class);

      return convertInstanceOfObject(constructor.newInstance(driver), clazz);

    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
      e.printStackTrace();
    }

    return null;
  }

  //protected BiConsumer<By, Predicate<? super WebElement>> clickElementByPredicate = (By locator, Predicate<? super WebElement> predicate) -> {
  //    List<WebElement> elements = driver.findElements(locator).stream().filter(predicate).collect(Collectors.toList());

  //    if (!elements.isEmpty()) {
  //        elements.get(0).click();
  //    }
  //};
}
