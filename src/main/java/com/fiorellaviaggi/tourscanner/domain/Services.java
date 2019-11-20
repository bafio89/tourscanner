package com.fiorellaviaggi.tourscanner.domain;

import java.util.List;

public class Services
{

  private List<String> includedServices;
  private List<String> notIncludedServices;

  public Services(List<String> includedServices, List<String> notIncludedServices)
  {
    this.includedServices = includedServices;
    this.notIncludedServices = notIncludedServices;
  }
}
