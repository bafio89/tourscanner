package com.fiorellaviaggi.tourscanner.domain.usecase;

import com.fiorellaviaggi.tourscanner.domain.ScraperService;
import com.fiorellaviaggi.tourscanner.domain.TourUrl;
import com.fiorellaviaggi.tourscanner.domain.TravelInfo;
import com.fiorellaviaggi.tourscanner.domain.TravelInfoExtractor;
import com.fiorellaviaggi.tourscanner.domain.UrlExtractor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.ArrayList;
import java.util.List;

public class WeRoadScraperUseCase
{
  private ScraperService scraperService;
  private UrlExtractor urlExtractor;
  private TravelInfoExtractor travelInfoExtractor;
  private PageCollector pageCollector;
  private TourRepository tourRepository;

  public WeRoadScraperUseCase(ScraperService scraperService,
                              UrlExtractor urlExtractor,
                              TravelInfoExtractor travelInfoExtractor,
                              PageCollector pageCollector,
                              TourRepository tourRepository)
  {
    this.scraperService = scraperService;
    this.urlExtractor = urlExtractor;
    this.travelInfoExtractor = travelInfoExtractor;
    this.pageCollector = pageCollector;
    this.tourRepository = tourRepository;
  }

  public void execute(HtmlPage homePage)
  {
    List<TravelInfo> travelInfoList = new ArrayList<>();
    List<TourUrl> toursUrls = urlExtractor.execute(homePage);

    toursUrls.forEach(tourUrl -> {

      List<HtmlPage> pages = pageCollector.execute(toursUrls);
      pages.forEach(page -> travelInfoList.add(travelInfoExtractor.execute(page, tourUrl)));

    });

    travelInfoList.forEach(travelInfo -> tourRepository.save(travelInfo));
  }

}
