package com.otus.data.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CourseData {
  Highload_Architect("Highload Architect", CourseTypeData.Online);

  private String name;
  private CourseTypeData courseTypeData;

}
