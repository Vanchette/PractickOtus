package com.otus.exceptions;

import io.github.bonigarcia.wdm.config.DriverManagerType;

public class DriverTypeNoteSupported extends Exception {
  public DriverTypeNoteSupported(DriverManagerType driverManagerType) {
    super(String.format("Driver type %s doesn't support", driverManagerType.name()));
  }

  public DriverTypeNoteSupported(String driverType) {
    super(String.format("Driver type %s doesn't support", driverType));
  }
}
