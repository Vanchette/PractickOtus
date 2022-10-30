package com.otus.pages;

import static org.assertj.core.api.Assertions.assertThat;

import com.otus.actions.CommonActions;
import com.otus.annotations.UrlPrefix;
import com.otus.annotations.UrlTemplate;
import com.otus.diconfig.GuiceScoped;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public abstract class AbsBasePage<T> extends CommonActions<T> {

  private final String hostname = System.getProperty("webdriver.base.url");
  @FindBy(tagName = "h1")
  private WebElement header;

  @FindBy(css = "head title")
  private WebElement headTitle;

  public AbsBasePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public void titleShouldBeSameAs(String title) {

    assertThat(header.getText().toLowerCase(Locale.ROOT).trim())
        .as(String.format("Title on lesson page should be %s", title))
        .isEqualTo(title.toLowerCase(Locale.ROOT).trim());
  }

  public String getPageTitle() {
    return driver.getTitle();
  }

  public static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
    try {
      return clazz.cast(o);
    } catch (ClassCastException e) {
      return null;
    }
  }

  public T pageHeaderShouldBeSameAs(String checkHeader) {
    assertThat(header).isEqualTo(checkHeader);
    //    assert header.equals(this.header.getText()) : "Error: header not valid";

    return (T) this;
  }

  String getUrlPrefix() {
    UrlPrefix urlAnnotation = getClass().getAnnotation(UrlPrefix.class);
    if (urlAnnotation != null) {
      return urlAnnotation.value();
    }
    return "";
  }

  private String getUrlTemplate() {
    UrlTemplate urlAnnotation = getClass().getAnnotation(UrlTemplate.class);
    if (urlAnnotation != null) {
      return urlAnnotation.value();
    }
    return "";
  }

  //    public T open() {
  //        driver.get(hostname + getUrlPrefix());
  //
  //        return (T) page(getClass());
  //    }

  //    public T open(){
  //        driver.get(hostname);
  //        return (T)this;
  //    }

  public T open() {
    String prefix = getUrlPrefix();
    if (!prefix.startsWith("/")) {
      prefix = "/" + prefix;
    }
    driver.get(hostname + prefix);
    return (T) this;
  }

  public T open(String... data) {
    String prefix = getUrlPrefix();
    String urlTemplate = getUrlTemplate();

    for (int i = 0; i < data.length - 1; i++) {
      urlTemplate = urlTemplate.replace(String.format("{%d}", i + 1), data[i]);
    }

    if (prefix.isEmpty()) {
      driver.get(hostname + urlTemplate);
    } else {
      driver.get(hostname + prefix + urlTemplate);
    }
    return (T) this;
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
}
