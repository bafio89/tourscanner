package com.fiorellaviaggi.tourscanner.domain.usecase;

import com.fiorellaviaggi.tourscanner.domain.ScraperService;
import com.fiorellaviaggi.tourscanner.domain.TourUrl;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SiVolaPageCollector
{
  private static final Logger LOGGER = LoggerFactory.getLogger(SiVolaPageCollector.class);

  private ScraperService scraperService;

  public SiVolaPageCollector(ScraperService scraperService)
  {
    this.scraperService = scraperService;
  }

  public List<Page> execute(Set<TourUrl> toursUrls)
  {
    List<Page> pageLists = new ArrayList<>();

    toursUrls.forEach(tourUrl -> {
      try
      {
        Page page = new Page(scraperService.execute(tourUrl.getUrl()), tourUrl);
        if(!soldOutOrComingSoonPage(page))
        {
          pageLists.add(page);
          LOGGER.info(String.format("Getting page from: %s", tourUrl.getUrl()));

        }else{
          LOGGER.info(String.format("Skipped page for sold out: %s", tourUrl.getUrl()));
        }
      }
      catch (IOException e)
      {
        LOGGER.warn(String.format("Something goes wrong getting page from %s ", tourUrl.getUrl()));
      }
    });

    return pageLists;
  }

  private boolean soldOutOrComingSoonPage(Page page){

    List<HtmlElement> soldOutElements = page.getHtmlPage().getByXPath("//button[@class='btn btn-primary btn-reverse d-block btn-disabled w-100']");

    if(soldOutElements.size() > 0){
      return true;
    }

    return false;
  }
}
