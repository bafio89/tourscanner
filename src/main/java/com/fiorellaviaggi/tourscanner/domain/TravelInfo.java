package com.fiorellaviaggi.tourscanner.domain;

import java.util.List;

public class TravelInfo
{
  private String country;
  private String travelName;
  private String duration;
  private Services services;
  private String price;
  private List<String> itinerary;
  private CommonCash commonCashDescription;

  public TravelInfo(String country, String travelName, String duration, Services services, String price,
                    List<String> itinerary,
                    CommonCash commonCashDescription)
  {
    this.country = country;
    this.travelName = travelName;
    this.duration = duration;
    this.services = services;
    this.price = price;
    this.itinerary = itinerary;
    this.commonCashDescription = commonCashDescription;
  }
}
