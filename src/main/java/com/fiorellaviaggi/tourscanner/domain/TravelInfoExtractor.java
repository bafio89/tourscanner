package com.fiorellaviaggi.tourscanner.domain;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class TravelInfoExtractor
{

  private static final Logger LOGGER = LoggerFactory.getLogger(TravelInfoExtractor.class);

  public TravelInfo execute(HtmlPage page, String country)
  {
    List<HtmlElement> commonCashDescription = page.getByXPath("//div[@class='lg-col-4 desktop-pr-40px'][1]/descendant::span");


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
    List<HtmlElement> commonCashDescription = page.getByXPath("//div[@class='lg-col-4 desktop-pr-40px'][2]/descendant::span");
    checkElementPresence(commonCashDescription, "commonCashDescription");

    return getTextFromFirstElement(commonCashDescription);
  }

  private List<String> extractCommonCashServices(HtmlPage page)
  {
    List<HtmlElement> services = page.getByXPath("//div[@class='lg-col-4 desktop-pr-40px']/descendant::ul");
    checkElementPresence(services, "commonCashServices");
    List<HtmlElement> commonCashServicesHtmlElements = services.get(2).getByXPath("descendant::li");
   checkElementPresence(commonCashServicesHtmlElements, "commonCashServicesHtmlElements");
    return commonCashServicesHtmlElements.stream().map(HtmlElement::asText)
                                         .collect(toList());
  }

  private List<String> extractItinerary(HtmlPage page)
  {
    List<HtmlElement> itinerary = page.getByXPath("//div[@class='left-testata']");
    checkElementPresence(itinerary, "itinerario");
    return itinerary.stream().map(it -> it.getFirstChild().asText()).collect(toList());
  }

  private String extractPrice(HtmlPage page)
  {
    List<HtmlElement> price = page.getByXPath("//div[@class='prezzo mt-15']");
    checkElementPresence(price, "price");
    return getTextFromFirstElement(price);
  }

  private Services extractServices(HtmlPage page)
  {
    List<HtmlElement> services = page.getByXPath("//div[@class='lg-col-4 desktop-pr-40px']/descendant::ul");

    checkElementPresence(services, "services");

    List<String> includedServices = extractIncludedServices(services);
    List<String> notIncludedServices = extractNotIncludedServices(services);

    return new Services(includedServices, notIncludedServices);
  }

  private List<String> extractIncludedServices(List<HtmlElement> services)
  {
    List<HtmlElement> includedServicesHtmlElements = services.get(0).getByXPath("descendant::li");
    checkElementPresence(includedServicesHtmlElements, "includedServices");
    return includedServicesHtmlElements.stream().map(HtmlElement::asText).collect(toList());
  }

  private List<String> extractNotIncludedServices(List<HtmlElement> services)
  {
    List<HtmlElement> notIncludedServicesHtmlElements = services.get(1).getByXPath("descendant::li");
    checkElementPresence(notIncludedServicesHtmlElements, "notIncludedServices");
    return notIncludedServicesHtmlElements.stream().map(HtmlElement::asText)
                                          .collect(toList());
  }

  private String extractTravelDuration(HtmlPage page)
  {
    List<HtmlElement> duration = page.getByXPath("//div[@class='numeri-viaggio']/descendant::div[@class='text']");
    checkElementPresence(duration, "duration");
    return getTextFromFirstElement(duration);
  }

  private String extractTitle(HtmlPage page)
  {
    List<HtmlElement> title = page.getByXPath("//h1[@class='titolo']");
    checkElementPresence(title, "title");
    return getTextFromFirstElement(title);
  }

  private String getTextFromFirstElement(List<HtmlElement> elements)
  {
    return elements.get(0).asText();
  }

  private void checkElementPresence(List<HtmlElement> elementList, String elementName)
  {
    if(elementList.size() == 0)
    {
      LOGGER.warn(String.format("Impossible to get element %s", elementName));
      throw new PageStructureChangedException(String.format("Impossible to get element %s", elementName));
    }
  }

}
