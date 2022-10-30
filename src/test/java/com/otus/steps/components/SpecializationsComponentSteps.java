package com.otus.steps.components;

import com.google.inject.Inject;
import com.otus.components.SpecializationsComponent;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Пусть;

public class SpecializationsComponentSteps {

  @Inject
  private SpecializationsComponent specializationsComponent;

  @Если("Кликнуть на курс {string}")
  public void clickCourseByName(String courseName) {
    specializationsComponent.clickCoursesItemByName(courseName);
  }

  @Пусть("Найдем все курсы с датой начала {string} или позже")
  public void startDateCoursesEqualOrMore(String courseDate) {
    specializationsComponent.startDateShouldBeEqualOrMore(courseDate);
  }

}
