package com.fiorellaviaggi.tourscanner.domain.usecase;

import com.fiorellaviaggi.tourscanner.domain.TourUrl;
import com.fiorellaviaggi.tourscanner.domain.TravelInfo;
import com.fiorellaviaggi.tourscanner.domain.TravelInfoExtractor;
import com.fiorellaviaggi.tourscanner.domain.UrlExtractor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;

public class WeRoadScraperUseCase
{
  private UrlExtractor urlExtractor;
  private TravelInfoExtractor travelInfoExtractor;
  private PageCollector pageCollector;
  private TourRepository tourRepository;

  public WeRoadScraperUseCase(UrlExtractor urlExtractor,
                              TravelInfoExtractor travelInfoExtractor,
                              PageCollector pageCollector,
                              TourRepository tourRepository)
  {
    this.urlExtractor = urlExtractor;
    this.travelInfoExtractor = travelInfoExtractor;
    this.pageCollector = pageCollector;
    this.tourRepository = tourRepository;
  }

  public void execute(HtmlPage homePage)
  {
    List<TravelInfo> travelInfoList = new ArrayList<>();
    Set<TourUrl> toursUrls = urlExtractor.execute(homePage);

    List<Page> pages = pageCollector.execute(toursUrls);
    pages.forEach(page -> travelInfoList.add(travelInfoExtractor.execute(page.getHtmlPage(), page.getTourUrl())));

    travelInfoList.forEach(travelInfo -> tourRepository.save(travelInfo));
  }

}
