package com.fiorellaviaggi.tourscanner.domain.usecase;

import com.fiorellaviaggi.tourscanner.domain.TourUrl;
import com.fiorellaviaggi.tourscanner.domain.TravelInfo;
import com.fiorellaviaggi.tourscanner.domain.TravelInfoExtractor;
import com.fiorellaviaggi.tourscanner.domain.UrlExtractor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WeRoadUseCase
{
  private UrlExtractor urlExtractor;
  private TravelInfoExtractor travelInfoExtractor;
  private WeRoadPageCollector weRoadPageCollector;
  private TourRepository tourRepository;

  public WeRoadUseCase(UrlExtractor urlExtractor,
                        TravelInfoExtractor travelInfoExtractor,
                        WeRoadPageCollector weRoadPageCollector,
                        TourRepository tourRepository)
  {
    this.urlExtractor = urlExtractor;
    this.travelInfoExtractor = travelInfoExtractor;
    this.weRoadPageCollector = weRoadPageCollector;
    this.tourRepository = tourRepository;
  }

  public void execute(HtmlPage homePage)
  {
    List<TravelInfo> travelInfoList = new ArrayList<>();
    Set<TourUrl> toursUrls = urlExtractor.execute(homePage);

    List<Page> pages = weRoadPageCollector.execute(toursUrls);
    pages.forEach(page -> travelInfoList.add(travelInfoExtractor.execute(page.getHtmlPage(), page.getTourUrl())));

    travelInfoList.forEach(travelInfo -> tourRepository.save(travelInfo));
  }

}
