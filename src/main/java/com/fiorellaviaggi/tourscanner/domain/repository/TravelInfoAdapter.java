package com.fiorellaviaggi.tourscanner.domain.repository;

import com.fiorellaviaggi.tourscanner.domain.TravelInfo;

public class TravelInfoAdapter
{
  public TourRapresentation fromDomainToRepository(TravelInfo travelInfo, Integer nationID)
  {
    return new TourRapresentation(nationID,
                                  travelInfo.getTravelName(),
                                  travelInfo.getDuration(),
                                  String.join("\n", travelInfo.getServices().getIncludedServices()),
                                  String.join("\n", travelInfo.getServices().getNotIncludedServices()),
                                  travelInfo.getPrice(),
                                  String.join("\n", travelInfo.getItinerary()),
                                  travelInfo.getCommonCash().getDescription(),
                                  String.join("\n", travelInfo.getCommonCash().getIncludedServices()),
                                  travelInfo.getCompanyId(),
                                  travelInfo.getTourLink().toString());
  }
}
