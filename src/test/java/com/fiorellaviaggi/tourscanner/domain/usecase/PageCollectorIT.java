package com.fiorellaviaggi.tourscanner.domain.usecase;

import com.fiorellaviaggi.tourscanner.domain.ScraperService;
import com.fiorellaviaggi.tourscanner.domain.TourUrl;
import org.junit.Test;

import java.net.URL;

import static java.util.Arrays.asList;

public class PageCollectorIT
{
  private ScraperService scraperService = new ScraperService();

  private PageCollector pageCollector;

  @Test
  public void collectPage() throws Exception
  {
    pageCollector = new PageCollector(scraperService);

    pageCollector
      .execute(asList(new TourUrl("Thailandia", new URL("https://www.weroad.it/viaggi/itinerari-thailandia"))));
  }
}
