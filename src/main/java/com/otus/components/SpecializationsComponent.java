package com.otus.components;

import com.google.inject.Inject;
import com.otus.annotations.Component;
import com.otus.diconfig.GuiceScoped;
import com.otus.pages.LessonsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component(".container.container-lessons")
public class SpecializationsComponent extends AbsComponent<SpecializationsComponent> {
  String regDate = "(0?[1-9]|[12][0-9]|3[01]) (янв(?:аря)?|фев(?:раля)?|мар(?:та)?|апр(?:еля)?|мая|июн(?:я)?|июл(?:я)?|авг(?:уста)?|сен(?:тября)?|окт(?:ября)?|ноя(?:бря)?|дек(?:абря)?)";
  Pattern pattern = Pattern.compile(regDate, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
  @FindBy(css = ".container.container-lessons>.container-padding-bottom>.lessons>.lessons__new-item .lessons__new-item-container")
  private List<WebElement> courses;

  @Inject
  public SpecializationsComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public LessonsPage clickCoursesItemByIndex(int i) {
    WebElement tile = courses.get(i - 1);
    standartWaiter.waitForCondition(ExpectedConditions.stalenessOf(tile));
    tile.click();
    return page(LessonsPage.class);
  }

  public void clickCoursesItemByName(String nameCourse) {

    WebElement courseItem = courses.stream()
        .filter(crs -> crs.getText().trim().toLowerCase(Locale.ROOT).contains(nameCourse.toLowerCase(Locale.ROOT)))
        .findFirst()
        .orElseThrow(NoSuchElementException::new);

    standartWaiter.waitForCondition(ExpectedConditions.stalenessOf(courseItem));
    courseItem.click();
    //    return page(LessonsPage.class);
  }

  public void startDateShouldBeEqualOrMore(String strDate) {

    Date date = getDateFromString(strDate);

    List<WebElement> webElements = courses.stream()
        .filter(f -> pattern.matcher(f.getText().toLowerCase(Locale.ROOT).trim()).find())
        .filter(f -> dateEqualOrMore(f, date))
        .collect(Collectors.toList());

    printCourses(webElements);
  }

  public void printCourses(List<WebElement> webElements) {
    for (WebElement webElement : webElements) {
      //      Matcher matcherDate = pattern.matcher(webElement.getText().toLowerCase(Locale.ROOT).trim());
      String courseName = webElement.findElement(By.cssSelector(".lessons__new-item-title")).getText();
      String courseDate = getStringDateFromWebElement(webElement);
      System.out.println(courseName + " " + courseDate);

    }
  }

  public Boolean dateEqualOrMore(WebElement webElement, Date date) {
    Date dateWebElement = getDateFromString(getStringDateFromWebElement(webElement));
    return date.equals(dateWebElement) || date.before(dateWebElement);
  }

  public LessonsPage clickCoursesItemByMinDate() {

    WebElement element = courses.stream()
        .filter(f -> pattern.matcher(f.getText().toLowerCase(Locale.ROOT).trim()).find())
        .reduce((webElement, webElement2) -> minDate(webElement, webElement2))
        .orElseThrow(NoSuchElementException::new);

    standartWaiter.waitForCondition(ExpectedConditions.stalenessOf(element));
    element.click();
    return page(LessonsPage.class);
  }

  public LessonsPage clickCoursesItemByMaxDate() {

    WebElement element = courses.stream()
        .filter(f -> pattern.matcher(f.getText().toLowerCase(Locale.ROOT).trim()).find())
        .reduce((webElement, webElement2) -> maxDate(webElement, webElement2))
        .orElseThrow(NoSuchElementException::new);

    standartWaiter.waitForCondition(ExpectedConditions.stalenessOf(element));
    element.click();
    return page(LessonsPage.class);
  }

  public WebElement maxDate(WebElement webElement, WebElement webElement2) {

    if (getDateFromString(getStringDateFromWebElement(webElement)).after(getDateFromString(getStringDateFromWebElement(webElement2)))) {
      return webElement;
    } else {
      return webElement2;
    }
  }

  public WebElement minDate(WebElement webElement, WebElement webElement2) {

    if (getDateFromString(getStringDateFromWebElement(webElement)).before(getDateFromString(getStringDateFromWebElement(webElement2)))) {
      return webElement;
    } else {
      return webElement2;
    }
  }

  public Date getDateFromString(String strDate) {
    SimpleDateFormat formatter = new SimpleDateFormat("d MMMM", new Locale("ru"));

    try {
      Date date = formatter.parse(strDate);
      return date;
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  public String getStringDateFromWebElement(WebElement webElement) {

    Matcher matcher = pattern.matcher(webElement.getText().toLowerCase(Locale.ROOT).trim());
    while (matcher.find()) {
      return matcher.group();
    }
    return "";
  }

}
