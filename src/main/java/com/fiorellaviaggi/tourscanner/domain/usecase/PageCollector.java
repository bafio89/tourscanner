package com.fiorellaviaggi.tourscanner.domain.usecase;

import com.fiorellaviaggi.tourscanner.domain.ScraperService;
import com.fiorellaviaggi.tourscanner.domain.TourUrl;
import com.fiorellaviaggi.tourscanner.domain.TravelInfoExtractor;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PageCollector
{
  private static final Logger LOGGER = LoggerFactory.getLogger(PageCollector.class);

  private String BASE_PATH = "https://www.weroad.it";

  private ScraperService scraperService;

  public PageCollector(ScraperService scraperService)
  {
    this.scraperService = scraperService;
  }

  public List<HtmlPage> execute(List<TourUrl> toursUrls)
  {
    List<HtmlPage> pageLists = new ArrayList<>();
    toursUrls.forEach(it -> {
      try
      {
        HtmlPage page = scraperService.execute(it.getUrl());
        pageLists.addAll(evaluatePage(page));
      }
      catch (IOException e)
      {
        LOGGER.warn(String.format("Something goes wrong getting page from %s ", it.getUrl()));
      }
    });

    return pageLists;
  }

  private List<HtmlPage> evaluatePage(HtmlPage page)
  {
    List<HtmlPage> pageLists = new ArrayList<>();
    List<HtmlAnchor> toursAnchorElements = page.getByXPath("//div[@class='travel-card ']//a[@href]");

    if(toursAnchorElements.size() > 0){
      Set<URL> urls = extractTourUrls(toursAnchorElements);

      urls.forEach(it -> {
        try
        {
          pageLists.add(scraperService.execute(it));
        }
        catch (IOException e)
        {
          LOGGER.warn(String.format("Something goes wrong getting page from %s ", it));
        }
      });
    }else{
      pageLists.add(page);
    }

    return pageLists;
  }

  private Set<URL> extractTourUrls(List<HtmlAnchor> tours)
  {
    return tours.stream().map(it -> createUrlFrom(it.getHrefAttribute())).collect(Collectors.toSet());
  }

  private URL createUrlFrom(String hrefAttribute)
  {
    try
    {
      return new URL(BASE_PATH + hrefAttribute);
    }
    catch (MalformedURLException e)
    {
      LOGGER.warn(String.format("Impossible to compose url from %s:", hrefAttribute));
      throw new UrlCreationException(e.toString());
    }
  }
}
