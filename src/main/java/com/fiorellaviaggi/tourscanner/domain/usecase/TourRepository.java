package com.fiorellaviaggi.tourscanner.domain.usecase;

import com.fiorellaviaggi.tourscanner.domain.TravelInfo;

public interface TourRepository
{
  void save(TravelInfo travelInfo);
}
