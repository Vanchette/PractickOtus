package com.otus.pages;

import com.google.inject.Inject;
import com.otus.annotations.UrlPrefix;
import com.otus.diconfig.GuiceScoped;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UrlPrefix("/online")
public class OnlinePage extends AbsBasePage<OnlinePage> {

  String regCost = "[0-9]*";
  Pattern pattern = Pattern.compile(regCost, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
  @FindBy(tagName = "h1")
  private WebElement headTitle;
  @FindBy(css = ".lessons>.lessons__new-item .lessons__new-item-container")
  private List<WebElement> videoCourses;
  @Inject
  public OnlinePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public void theCheapestCourse() {

    WebElement webElement = videoCourses.stream()
        .filter(f -> pattern.matcher(f.getText().toLowerCase(Locale.ROOT).trim()).find())
        .sorted(Comparator.comparingInt(o -> getCostFromWebElement(o)))
        .findFirst()
        .get();

    System.out.println("Самый дешевый курс:");
    printCourse(webElement);
  }

  public void mostExpensiveCourse() {

    WebElement webElement = videoCourses.stream()
        .filter(f -> pattern.matcher(f.getText().toLowerCase(Locale.ROOT).trim()).find())
        .sorted(Comparator.comparingInt(o -> getCostFromWebElement((WebElement) o)).reversed())
        //        .sorted((o1, o2) -> getCostFromWebElement(o2) - getCostFromWebElement(o1))
        .findFirst()
        .get();

    System.out.println("Самый дорогой курс:");
    printCourse(webElement);
  }

  public void printCourse(WebElement webElement) {
    String courseName = webElement.findElement(By.cssSelector(".lessons__new-item-title")).getText().trim();

    String coursePrice = webElement.findElement(By.cssSelector(".lessons__new-item-price")).getText().trim();
    System.out.println(courseName + " " + coursePrice);
  }

  public Integer getCostFromWebElement(WebElement webElement) {

    Matcher matcher = pattern.matcher(webElement.findElement(By.cssSelector(".lessons__new-item-price")).getText().toLowerCase(Locale.ROOT).trim());
    while (matcher.find()) {
      return Integer.parseInt(matcher.group());
    }
    return null;
  }

}
