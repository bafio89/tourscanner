package com.fiorellaviaggi.tourscanner.domain.usecase;

import com.fiorellaviaggi.tourscanner.domain.ScraperService;
import com.fiorellaviaggi.tourscanner.domain.TourUrl;
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

import static java.util.Arrays.asList;

public class WeRoadPageCollector
{
  private static final Logger LOGGER = LoggerFactory.getLogger(WeRoadPageCollector.class);

  private String BASE_PATH = "https://www.weroad.it";

  private ScraperService scraperService;

  private List<String> urlToBeSkipped = asList("https://www.weroad.it/viaggi/asia",
                                               "https://www.weroad.it/viaggi/nord-america",
                                               "https://www.weroad.it/viaggi/centro-america",
                                               "https://www.weroad.it/viaggi/sud-america",
                                               "https://www.weroad.it/viaggi/europa",
                                               "https://www.weroad.it/viaggi/middle-east",
                                               "https://www.weroad.it/viaggi/oceania",
                                               "https://www.weroad.it/viaggi/africa,",
                                                "https://www.weroad.it/viaggi-gift-card");

  public WeRoadPageCollector(ScraperService scraperService)
  {
    this.scraperService = scraperService;
  }

  public List<Page> execute(Set<TourUrl> toursUrls)
  {
    List<Page> pageLists = new ArrayList<>();
    toursUrls.forEach(it -> {
      if (!urlToBeSkipped.contains(it.getUrl().toString()))
      {
        try
        {
          HtmlPage page = scraperService.execute(it.getUrl());
          pageLists.addAll(evaluatePage(page, it));
          LOGGER.info(String.format("Getting page from: %s", it.getUrl()));
        }
        catch (Exception e)
        {
          LOGGER.warn(String.format("Something goes wrong getting page from %s ", it.getUrl()));
        }
      }
    });

    return pageLists;
  }

  private List<Page> evaluatePage(HtmlPage page, TourUrl tourUrl)
  {
    List<Page> pageLists = new ArrayList<>();

    List<HtmlAnchor> toursAnchorElements = page.getByXPath("//div[@class='travel-card ']//a[@href]");

    if (notMultiTour(page))
    {
      pageLists.add(new Page(page, tourUrl));
      return pageLists;
    }

    Set<URL> urls = extractTourUrls(toursAnchorElements);

    urls.forEach(it -> {
      try
      {
        HtmlPage obtainedPage = scraperService.execute(it);
        if (notMultiTour(obtainedPage))
        {
          pageLists.add(new Page(obtainedPage, new TourUrl(tourUrl.getNation(), it)));
          LOGGER.info(String.format("Splitted page from: %s", it.toString()));

        }
      }
      catch (IOException e)
      {
        LOGGER.warn(String.format("Something goes wrong getting page from %s ", it));
      }
    });

    return pageLists;
  }

  private boolean notMultiTour(HtmlPage page)
  {
    List<HtmlAnchor> toursAnchorElements = page.getByXPath("//div[@class='travel-card ']//a[@href]");

    return toursAnchorElements.size() == 0;
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
