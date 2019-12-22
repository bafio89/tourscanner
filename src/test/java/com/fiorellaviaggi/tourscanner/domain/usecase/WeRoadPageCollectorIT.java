package com.fiorellaviaggi.tourscanner.domain.usecase;

import com.fiorellaviaggi.tourscanner.domain.ScraperService;
import com.fiorellaviaggi.tourscanner.domain.TourUrl;
import org.junit.Test;

import java.net.URL;

import static org.hibernate.validator.internal.util.CollectionHelper.asSet;

public class WeRoadPageCollectorIT
{
  private ScraperService scraperService = new ScraperService();

  private WeRoadPageCollector weRoadPageCollector;

  @Test
  public void collectPage() throws Exception
  {
    weRoadPageCollector = new WeRoadPageCollector(scraperService);

    weRoadPageCollector
      .execute(asSet(new TourUrl("Thailandia", new URL("https://www.weroad.it/viaggi/viaggio-di-gruppo-islanda-trekking-adventure"))));
  }
}
