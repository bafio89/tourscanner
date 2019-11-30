package com.fiorellaviaggi.tourscanner.domain;

import java.util.List;
import java.util.Objects;

public class CommonCash
{
  private String description;
  private List<String> includedServices;

  public CommonCash(String description, List<String> includedServices)
  {
    this.description = description;
    this.includedServices = includedServices;
  }

  public String getDescription()
  {
    return description;
  }

  public List<String> getIncludedServices()
  {
    return includedServices;
  }

  @Override
  public String toString()
  {
    return "CommonCash{" +
      "description='" + description + '\'' +
      ", includedServices=" + includedServices +
      '}';
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CommonCash that = (CommonCash) o;
    return Objects.equals(description, that.description) &&
      Objects.equals(includedServices, that.includedServices);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(description, includedServices);
  }
}
