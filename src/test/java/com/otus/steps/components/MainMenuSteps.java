package com.otus.steps.components;

import com.google.inject.Inject;
import com.otus.components.HeaderMenuComponent;
import com.otus.data.menu.CourseTypeData;
import com.otus.data.menu.ItemData;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;

import java.util.Arrays;

public class MainMenuSteps {

  @Inject
  private HeaderMenuComponent headerMenuComponent;

  private ItemData getSubMenuItem(String name) {
    assert Arrays.stream(ItemData.values()).anyMatch(item -> item.getName().equals(name)) :
        String.format("Error: submenu %s doesn't suupported", name);

    return Arrays.stream(ItemData.values()).filter(item -> item.getName().equals(name)).findFirst().get();
  }

  @Тогда("Список с курсами меню {string} не открыт")
  public void subMenuItemShouldNotBeVisible(String courseName) {
    headerMenuComponent.checkSubMenuListNotVisible(getSubMenuItem(courseName));
  }

  @Если("Кликнуть на пункт меню {string}")
  public void clickMenuItem(String courseName) {
    headerMenuComponent.clickToMenuItem(getSubMenuItem(courseName));
  }

  @Тогда("Список с курсами меню {string} открыт")
  public void subMenuItemShouldBeVisible(String courseName) {
    headerMenuComponent.checkSubMenuListVisible(getSubMenuItem(courseName));
  }

  @Если("Кликнуть на пункт подменю {string}")
  public void clickSubMenuItem(String courseName) {
    //    CourseData courseData = Arrays.stream(CourseData.values()).filter(item -> item.getName().equals(courseName)).findFirst().get();
    CourseTypeData courseTypeData = Arrays.stream(CourseTypeData.values()).filter(item -> item.getName().equals(courseName)).findFirst().get();
    headerMenuComponent.clickCourseItem(courseTypeData);
  }
}
