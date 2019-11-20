package com.fiorellaviaggi.tourscanner.domain;

import java.util.List;

public class CommonCash
{
  private String description;
  private List<String> includedServices;

  public CommonCash(String description, List<String> includedServices)
  {
    this.description = description;
    this.includedServices = includedServices;
  }
}
