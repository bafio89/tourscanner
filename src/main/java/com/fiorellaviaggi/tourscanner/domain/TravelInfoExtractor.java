package com.fiorellaviaggi.tourscanner.domain;

import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlUnorderedList;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class TravelInfoExtractor
{

  public TravelInfo execute(HtmlPage page, String country)
  {

    return new TravelInfo(country, extractTitle(page), extractTravelDuration(page),
                          extractServices(page),
                          extractPrice(page), extractItinerary(page),
                          extractCommonCash(page));
  }

  private CommonCash extractCommonCash(HtmlPage page){
    return new CommonCash(extractCommonCashDescription(page), extractCommonCashServices(page));
  }

  private String extractCommonCashDescription(HtmlPage page)
  {
    List<HtmlDivision> servicesDiv = page.getByXPath("//div[@class='lg-col-4 desktop-pr-40px']");
    List<HtmlSpan> serviceElement = servicesDiv.get(1).getByXPath("descendant::span");
    return serviceElement.get(0).asText();
  }

  private List<String> extractCommonCashServices(HtmlPage page)
  {
    List<HtmlUnorderedList> services = page.getByXPath("//div[@class='lg-col-4 desktop-pr-40px']/descendant::ul");

    List<HtmlListItem> commonCashServicesHtmlElements = services.get(2).getByXPath("descendant::li");
    return commonCashServicesHtmlElements.stream().map(HtmlListItem::asText)
                                         .collect(toList());
  }

  private List<String> extractItinerary(HtmlPage page)
  {
    List<HtmlDivision> itinerary = page.getByXPath("//div[@class='left-testata']");
    return itinerary.stream().map(it -> it.getFirstChild().asText()).collect(toList());
  }

  private String extractPrice(HtmlPage page)
  {
    List<HtmlDivision> price = page.getByXPath("//div[@class='prezzo mt-15']");
    return price.get(0).asText();
  }

  private Services extractServices(HtmlPage page)
  {
    List<HtmlUnorderedList> services = page.getByXPath("//div[@class='lg-col-4 desktop-pr-40px']/descendant::ul");

    List<String> includedServices = extractIncludedServices(services);
    List<String> notIncludedServices = extractNotIncludedServices(services);

    return new Services(includedServices, notIncludedServices);
  }

  private List<String> extractNotIncludedServices(List<HtmlUnorderedList> services)
  {
    List<HtmlListItem> notIncludedServicesHtmlElements = services.get(1).getByXPath("descendant::li");
    return notIncludedServicesHtmlElements.stream().map(HtmlListItem::asText)
                                          .collect(toList());
  }

  private List<String> extractIncludedServices(List<HtmlUnorderedList> services)
  {
    List<HtmlListItem> includedServicesHtmlElements = services.get(0).getByXPath("descendant::li");
    return includedServicesHtmlElements.stream().map(HtmlListItem::asText).collect(toList());
  }

  private String extractTravelDuration(HtmlPage page)
  {
    List<HtmlDivision> duration = page.getByXPath("//div[@class='numeri-viaggio']/descendant::div[@class='text']");
    return duration.get(0).asText();
  }

  private String extractTitle(HtmlPage page)
  {
    List<HtmlHeading1> title = page.getByXPath("//h1[@class='titolo']");
    return title.get(0).asText();
  }

  private String getTextFromFirstElement(List<HtmlElement> elements)
  {
    return elements.get(0).asText();
  }

}
