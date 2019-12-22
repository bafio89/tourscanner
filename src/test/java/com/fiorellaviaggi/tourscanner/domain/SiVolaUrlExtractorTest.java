package com.fiorellaviaggi.tourscanner.domain;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;

import java.net.URL;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SiVolaUrlExtractorTest
{
  private ScraperService scraperService = new ScraperService();

  private SiVolaUrlExtractor urlExtractor;

  @Test
  public void extractUrl() throws Exception
  {
    urlExtractor = new SiVolaUrlExtractor();

    HtmlPage homePage = scraperService.execute(new URL("https://www.sivola.it/viaggi"));

    Set<TourUrl> result = urlExtractor.execute(homePage);

    homePage = scraperService.execute(new URL("https://www.sivola.it/viaggi?page=2"));

    result.addAll(urlExtractor.execute(homePage));
    assertThat(result.size(), is(37));
  }

}
