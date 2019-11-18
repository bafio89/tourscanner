package com.fiorellaviaggi.tourscanner.domain.usecase;

import com.fiorellaviaggi.tourscanner.domain.ScraperService;
import com.fiorellaviaggi.tourscanner.domain.TravelInfoExtractor;
import com.fiorellaviaggi.tourscanner.domain.UrlExtractor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class WeRoadScraperUseCase
{
  private ScraperService scraperService;
  private UrlExtractor urlExtractor;
  private TravelInfoExtractor travelInfoExtractor;

  public WeRoadScraperUseCase(ScraperService scraperService,
                              UrlExtractor urlExtractor,
                              TravelInfoExtractor travelInfoExtractor)
  {
    this.scraperService = scraperService;
    this.urlExtractor = urlExtractor;
    this.travelInfoExtractor = travelInfoExtractor;
  }

  public void execute(HtmlPage page) {

    urlExtractor.execute(page);

  }
}
