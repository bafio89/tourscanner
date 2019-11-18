package com.fiorellaviaggi.tourscanner.domain;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class UrlExtractor
{
  private String BASE_PATH = "https://www.weroad.it/";

  public List<TourUrls> execute(HtmlPage homePage)
  {

    List<HtmlAnchor> tourUrls = homePage.getByXPath("//a[@class='flex items-start ']");

    return tourUrls.stream().map(this::buildTourUrls).collect(toList());

  }

  private TourUrls buildTourUrls(HtmlAnchor it)
  {
    return new TourUrls(it.getFirstChild().getFirstChild().asText(), buildURLfrom(it));
  }

  private URL buildURLfrom(HtmlAnchor it)
  {
    URL url;
    try
    {
     url = new URL(BASE_PATH + it.getHrefAttribute());
    }
    catch (MalformedURLException e)
    {
      e.printStackTrace();
      throw new RuntimeException();
    }

    return url;
  }
}
