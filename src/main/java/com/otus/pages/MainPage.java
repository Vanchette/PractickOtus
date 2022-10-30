package com.otus.pages;

import com.google.inject.Inject;
import com.otus.annotations.UrlPrefix;
import com.otus.diconfig.GuiceScoped;

@UrlPrefix("/")
public class MainPage extends AbsBasePage<MainPage> {
  @Inject
  public MainPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

}
