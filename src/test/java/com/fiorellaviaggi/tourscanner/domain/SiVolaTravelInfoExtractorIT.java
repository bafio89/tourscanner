package com.fiorellaviaggi.tourscanner.domain;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;

import java.net.URL;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SiVolaTravelInfoExtractorIT
{
  private ScraperService scraperService = new ScraperService();

  private SiVolaTravelInfoExtractor travelInfoExtractor;

  @Test
  public void extractTourInfo() throws Exception
  {
    travelInfoExtractor = new SiVolaTravelInfoExtractor();

    HtmlPage travelPage = scraperService.execute(new URL("https://www.sivola.it/viaggi/canada-on-the-road"));

    TourUrl cambogiaUrl = new TourUrl("Islanda", new URL("https://sivola.it/viaggi/islanda-il-grande-nord"));
    TravelInfo cambogia = travelInfoExtractor.execute(travelPage, cambogiaUrl);
    assertThat(cambogia, is(new TravelInfo("Islanda", "Islanda - Il grande nord", "", null, "1.299 €", asList("Phnom Penh"),
                                           null, 2, cambogiaUrl.getUrl())));
  }
}
