package com.fiorellaviaggi.tourscanner.domain;

import java.util.List;
import java.util.Objects;

public class Services
{

  private List<String> includedServices;
  private List<String> notIncludedServices;

  public Services(List<String> includedServices, List<String> notIncludedServices)
  {
    this.includedServices = includedServices;
    this.notIncludedServices = notIncludedServices;
  }

  public List<String> getIncludedServices()
  {
    return includedServices;
  }

  public List<String> getNotIncludedServices()
  {
    return notIncludedServices;
  }

  @Override
  public String toString()
  {
    return "Services{" +
      "includedServices=" + includedServices +
      ", notIncludedServices=" + notIncludedServices +
      '}';
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Services services = (Services) o;
    return Objects.equals(includedServices, services.includedServices) &&
      Objects.equals(notIncludedServices, services.notIncludedServices);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(includedServices, notIncludedServices);
  }
}
