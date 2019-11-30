package com.fiorellaviaggi.tourscanner.domain;

import java.net.URL;
import java.util.List;
import java.util.Objects;

public class TravelInfo
{
  private String nation;
  private String travelName;
  private String duration;
  private Services services;
  private String price;
  private List<String> itinerary;
  private CommonCash commonCash;
  private Integer companyId;
  private URL tourLink;


  public void setNation(String nation)
  {
    this.nation = nation;
  }

  public Integer getCompanyId()
  {
    return companyId;
  }

  public URL getTourLink()
  {
    return tourLink;
  }

  public TravelInfo(String nation, String travelName, String duration, Services services, String price,
                    List<String> itinerary,
                    CommonCash commonCash, Integer companyId, URL tourLink)
  {
    this.nation = nation;
    this.travelName = travelName;
    this.duration = duration;
    this.services = services;
    this.price = price;
    this.itinerary = itinerary;
    this.commonCash = commonCash;
    this.companyId = companyId;
    this.tourLink = tourLink;
  }

  public String getNation()
  {
    return nation;
  }

  public String getTravelName()
  {
    return travelName;
  }

  public String getDuration()
  {
    return duration;
  }

  public Services getServices()
  {
    return services;
  }

  public String getPrice()
  {
    return price;
  }

  public List<String> getItinerary()
  {
    return itinerary;
  }

  public CommonCash getCommonCash()
  {
    return commonCash;
  }

  @Override
  public String toString()
  {
    return "TravelInfo{" +
      "nation='" + nation + '\'' +
      ", travelName='" + travelName + '\'' +
      ", duration='" + duration + '\'' +
      ", services=" + services +
      ", price='" + price + '\'' +
      ", itinerary=" + itinerary +
      ", commonCash=" + commonCash +
      ", companyId=" + companyId +
      ", tourLink=" + tourLink +
      '}';
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TravelInfo that = (TravelInfo) o;
    return Objects.equals(nation, that.nation) &&
      Objects.equals(travelName, that.travelName) &&
      Objects.equals(duration, that.duration) &&
      Objects.equals(services, that.services) &&
      Objects.equals(price, that.price) &&
      Objects.equals(itinerary, that.itinerary) &&
      Objects.equals(commonCash, that.commonCash);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(nation, travelName, duration, services, price, itinerary, commonCash);
  }
}
