package com.fiorellaviaggi.tourscanner.domain.usecase;

import com.fiorellaviaggi.tourscanner.domain.TourUrl;
import com.fiorellaviaggi.tourscanner.domain.TravelInfo;
import com.fiorellaviaggi.tourscanner.domain.TravelInfoExtractor;
import com.fiorellaviaggi.tourscanner.domain.UrlExtractor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SiVolaScraperUseCase
{
  private UrlExtractor urlExtractor;
  private TravelInfoExtractor travelInfoExtractor;
  private SiVolaPageCollector siVolaPageCollector;
  private TourRepository tourRepository;

  public SiVolaScraperUseCase(UrlExtractor urlExtractor,
                        TravelInfoExtractor travelInfoExtractor,
                        SiVolaPageCollector siVolaPageCollector,
                        TourRepository tourRepository)
  {
    this.urlExtractor = urlExtractor;
    this.travelInfoExtractor = travelInfoExtractor;
    this.siVolaPageCollector = siVolaPageCollector;
    this.tourRepository = tourRepository;
  }

  public void execute(HtmlPage firstTravelPage, HtmlPage secondTravelPage)
  {
    List<TravelInfo> travelInfoList = new ArrayList<>();
    Set<TourUrl> toursUrls = urlExtractor.execute(firstTravelPage);
    toursUrls.addAll(urlExtractor.execute(secondTravelPage));

    List<Page> pages = siVolaPageCollector.execute(toursUrls);
    pages.forEach(page -> travelInfoList.add(travelInfoExtractor.execute(page.getHtmlPage(), page.getTourUrl())));

    travelInfoList.forEach(travelInfo -> tourRepository.save(travelInfo));
  }

}
