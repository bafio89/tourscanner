package com.fiorellaviaggi.tourscanner.domain;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;

import java.net.URL;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WeRoadTravelInfoExtractorIT
{
  private ScraperService scraperService = new ScraperService();

  private TravelInfoExtractor travelInfoExtractor;

  @Test
  public void extractTourInfo() throws Exception
  {
    travelInfoExtractor = new TravelInfoExtractor();

    HtmlPage travelPage = scraperService.execute(new URL("https://www.weroad.it/viaggi/tour-sicilia-palermo-riserva-dello-zingaro"));

    TourUrl cambogiaUrl = new TourUrl("Cambogia", new URL("https://www.weroad.it/viaggi/tour-sicilia-palermo-riserva-dello-zingaro"));
    TravelInfo cambogia = travelInfoExtractor.execute(travelPage, cambogiaUrl);
    assertThat(cambogia, is(new TravelInfo("Cambogia", "", "", null, "1.299 €", asList("Phnom Penh"),
                                           null, 1, cambogiaUrl.getUrl())));
  }
}
