package com.fiorellaviaggi.tourscanner.domain;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class SiVolaUrlExtractor extends UrlExtractor
{
  private List<String> nationList = asList("Islanda", "Lapponia",
                                           "Giordania", "Sri Lanka", "Stati Uniti", "Namibia", "Perù", "Marocco",
                                           "Lapponia", "Bolivia", "Giappone", "Messico", "Birmania", "Scozia",
                                           "Madagascar", "Svalbard", "Canada", "Norvegia", "Nepal", "India", "Cina",
                                           "Finlandia", "Vietnam", "Russia", "Groenlandia", "Mongolia");

  public static final String URLS_XPATH = "//h2[@class='font-weight-bolder h4']/a";
  private String BASE_PATH = "https://www.sivola.it";

  @Override
  public Set<TourUrl> execute(HtmlPage homePage)
  {
    List<HtmlAnchor> tourUrls = homePage.getByXPath(getUrlsXpath());

    return new HashSet<>(tourUrls.stream().map(this::buildTourUrls).collect(toList()));

  }

  @Override
  protected String getUrlsXpath()
  {
    return URLS_XPATH;
  }

  @Override
  protected String getBasePath()
  {
    return BASE_PATH;
  }

  @Override
  protected TourUrl buildTourUrls(HtmlAnchor it)
  {
    return new TourUrl(extractNationFrom(it.getFirstChild().asText()), buildURLfrom(it));
  }

  private String extractNationFrom(String title){

    for (String nation : nationList)
    {
      String nationName = nation;
      if (title.toLowerCase().contains(nationName.toLowerCase())){
        return nationName;
      }
    }
    return "Mondo";
  }
}
