package com.fiorellaviaggi.tourscanner.domain;

import java.net.URL;

public class TourUrls
{
  private String tourName;
  private URL url;

  public TourUrls(String tourName, URL url)
  {
    this.tourName = tourName;
    this.url = url;
  }
}
