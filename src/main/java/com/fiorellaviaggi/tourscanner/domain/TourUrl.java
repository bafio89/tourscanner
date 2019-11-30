package com.fiorellaviaggi.tourscanner.domain;

import java.net.URL;

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
}
