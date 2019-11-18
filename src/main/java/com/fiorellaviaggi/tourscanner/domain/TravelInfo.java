package com.fiorellaviaggi.tourscanner.domain;

import java.util.List;

public class TravelInfo
{
  private String travelName;
  private String description;
  private String price;
  private List<String> itinerary;

  public TravelInfo(String travelName, String description, String price, List<String> itinerary)
  {
    this.travelName = travelName;
    this.description = description;
    this.price = price;
    this.itinerary = itinerary;
  }
}
