package com.fiorellaviaggi.tourscanner.domain;

import java.net.URL;
import java.util.Objects;

public class TourUrl
{
  private String tourName;
  private URL url;

  public TourUrl(String tourName, URL url)
  {
    this.tourName = tourName;
    this.url = url;
  }

  public String getTourName()
  {
    return tourName;
  }

  public URL getUrl()
  {
    return url;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TourUrl tourUrl = (TourUrl) o;
    return Objects.equals(tourName, tourUrl.tourName) &&
      Objects.equals(url, tourUrl.url);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(tourName, url);
  }
}
