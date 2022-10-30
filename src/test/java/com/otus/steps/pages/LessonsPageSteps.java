package com.otus.steps.pages;

import com.google.inject.Inject;
import com.otus.pages.LessonsPage;
import io.cucumber.java.ru.Тогда;

public class LessonsPageSteps {

  @Inject
  private LessonsPage lessonsPage;

  @Тогда("Откроется страница курса с заголовком {string}")
  public void lessonPageSouldBeOpened(String title) {
    lessonsPage.titleShouldBeSameAs(title);
  }

}
