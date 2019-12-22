package com.fiorellaviaggi.tourscanner.domain.usecase;

import com.fiorellaviaggi.tourscanner.domain.ScraperService;
import com.fiorellaviaggi.tourscanner.domain.TourUrl;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hibernate.validator.internal.util.CollectionHelper.asSet;

public class SiVolaPageCollectorIT
{
  private ScraperService scraperService = new ScraperService();

  private SiVolaPageCollector collector;

  @Test
  public void collectSoldOutPage() throws MalformedURLException
  {
    collector = new SiVolaPageCollector(scraperService);

    collector
      .execute(asSet(new TourUrl("Giappone", new URL("https://sivola.it/viaggi/hanami-safari-zaino-in-spalla"))));
  }

  @Test
  public void collectNotSoldOutPage() throws MalformedURLException
  {
    collector = new SiVolaPageCollector(scraperService);

    collector
      .execute(asSet(new TourUrl("Groenlandia", new URL("https://www.sivola.it/viaggi/islanda-ghiacciai"))));
  }


}
