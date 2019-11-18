package com.fiorellaviaggi.tourscanner.domain;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;

import java.net.URL;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TravelInfoExtractorIT
{
  private ScraperService scraperService = new ScraperService();

  private TravelInfoExtractor travelInfoExtractor;

  @Test
  public void extractTourInfo() throws Exception
  {
    travelInfoExtractor = new TravelInfoExtractor();

    HtmlPage travelPage = scraperService.execute(new URL("https://www.weroad.it/viaggi/viaggio-cambogia-tour-10-giorni"));

    assertThat(travelInfoExtractor.execute(travelPage), is(new TravelInfo("Cambogia", "", "1.299 €", asList("Phnom Penh"))));
  }
}
