package com.fiorellaviaggi.tourscanner.domain;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;

import java.net.URL;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UrlExtractorTest
{
  private ScraperService scraperService = new ScraperService();

  private UrlExtractor urlExtractor;

  @Test
  public void extractUrl() throws Exception
  {
    urlExtractor = new UrlExtractor();

    HtmlPage homePage = scraperService.execute(new URL("https://www.weroad.it/"));

    List<TourUrl> result = urlExtractor.execute(homePage);
    assertThat(result.size(), is(126));
  }
}
