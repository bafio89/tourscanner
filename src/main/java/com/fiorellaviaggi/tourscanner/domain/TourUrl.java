package com.fiorellaviaggi.tourscanner.domain;

import java.net.URL;
import java.util.Objects;

public class TourUrl
{
  private String nation;
  private URL url;

  public TourUrl(String nation, URL url)
  {
    this.nation = nation;
    this.url = url;
  }

  public String getNation()
  {
    return nation;
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
    return Objects.equals(nation, tourUrl.nation) &&
      Objects.equals(url, tourUrl.url);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(nation, url);
  }
}
