package com.fiorellaviaggi.tourscanner.domain.repository;

public class TourRapresentation
{
  private Integer nationId;
  private String travelName;
  private String duration;
  private String includedServices;
  private String notIncludedServices;
  private String price;
  private String itinerary;
  private String commonCashDescription;
  private String commonCashIncludedServices;
  private Integer companyId;
  private String tourLink;

  public TourRapresentation(Integer nationId, String travelName, String duration, String includedServices,
                            String notIncludedServices, String price, String itinerary,
                            String commonCashDescription, String commonCashIncludedServices,
                            Integer companyId, String tourLink)
  {
    this.nationId = nationId;
    this.travelName = travelName;
    this.duration = duration;
    this.includedServices = includedServices;
    this.notIncludedServices = notIncludedServices;
    this.price = price;
    this.itinerary = itinerary;
    this.commonCashDescription = commonCashDescription;
    this.commonCashIncludedServices = commonCashIncludedServices;
    this.companyId = companyId;
    this.tourLink = tourLink;
  }

  public Integer getNationId()
  {
    return nationId;
  }

  public String getTravelName()
  {
    return travelName;
  }

  public String getDuration()
  {
    return duration;
  }

  public String getIncludedServices()
  {
    return includedServices;
  }

  public String getNotIncludedServices()
  {
    return notIncludedServices;
  }

  public String getPrice()
  {
    return price;
  }

  public String getItinerary()
  {
    return itinerary;
  }

  public String getCommonCashDescription()
  {
    return commonCashDescription;
  }

  public String getCommonCashIncludedServices()
  {
    return commonCashIncludedServices;
  }

  public Integer getCompanyId()
  {
    return companyId;
  }

  public String getTourLink()
  {
    return tourLink;
  }
}
