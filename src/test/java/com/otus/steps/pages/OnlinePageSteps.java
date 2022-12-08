package com.otus.steps.pages;

import com.google.inject.Inject;
import com.otus.pages.OnlinePage;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;

public class OnlinePageSteps {

  @Inject
  private OnlinePage onlinePage;

  @Тогда("Откроется страница курсов для подготовки с заголовком {string}")
  public void onlinePageSouldBeOpened(String title) {
    onlinePage.titleShouldBeSameAs(title);
  }

  @Пусть("Выведем в консоль самый дорогой курс")
  public void printMostExpensiveCourse() {
    onlinePage.mostExpensiveCourse();
  }

  @Пусть("Выведем в консоль самый дешевый курс")
  public void printTheCheapestCourse() {
    onlinePage.theCheapestCourse();
  }
}
