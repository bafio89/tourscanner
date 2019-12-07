package com.fiorellaviaggi.tourscanner.domain;

import com.fiorellaviaggi.tourscanner.domain.usecase.UrlCreationException;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class UrlExtractor
{
  private static final Logger LOGGER = LoggerFactory.getLogger(UrlExtractor.class);
  private String BASE_PATH = "https://www.weroad.it";

  public Set<TourUrl> execute(HtmlPage homePage)
  {
    List<HtmlAnchor> tourUrls = homePage.getByXPath("//a[@class='flex items-start ']");

    return new HashSet<>(tourUrls.stream().map(this::buildTourUrls).collect(toList()));

  }

  private TourUrl buildTourUrls(HtmlAnchor it)
  {
    return new TourUrl(it.getFirstChild().getFirstChild().asText(), buildURLfrom(it));
  }

  private URL buildURLfrom(HtmlAnchor urlHtmlElement)
  {
    String url = urlHtmlElement.getHrefAttribute();
    if(!url.contains(BASE_PATH)){
      url = BASE_PATH + url;
    }
    try
    {
      return new URL(url);
    }
    catch (MalformedURLException e)
    {
      LOGGER.warn(String.format("Impossible to compose url from %s:", urlHtmlElement.getHrefAttribute()));
      throw new UrlCreationException(e.toString());
    }
  }
}
