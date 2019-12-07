package com.fiorellaviaggi.tourscanner.domain;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class TravelInfoExtractor
{
  private Integer COMPANY_ID = 1;

  private static final Logger LOGGER = LoggerFactory.getLogger(TravelInfoExtractor.class);

  public TravelInfo execute(HtmlPage page, TourUrl tourUrl)
  {

    return new TravelInfo(tourUrl.getTourName(), extractTitle(page), extractTravelDuration(page),
                          extractServices(page),
                          extractPrice(page), extractItinerary(page),
                          extractCommonCash(page), COMPANY_ID, tourUrl.getUrl());
  }

  private CommonCash extractCommonCash(HtmlPage page){
    return new CommonCash(extractCommonCashDescription(page), extractCommonCashServices(page));
  }

  private String extractCommonCashDescription(HtmlPage page)
  {
    List<HtmlElement> commonCashDescription = page.getByXPath("//div[@class='lg-col-4 desktop-pr-40px'][2]/descendant::p");
    if(commonCashDescription.size() == 0 ){
      commonCashDescription = page.getByXPath("//div[@class='lg-col-4 desktop-pr-40px'][2]/descendant::div[3]");
    }

    checkElementPresence(commonCashDescription, "commonCashDescription", page.getTitleText());

    return getTextFromFirstElement(commonCashDescription);
  }

  private List<String> extractCommonCashServices(HtmlPage page)
  {
    List<HtmlElement> services = page.getByXPath("//div[@class='lg-col-4 desktop-pr-40px']/descendant::ul");
    checkElementPresence(services, "commonCashServices", page.getTitleText());
    List<HtmlElement> commonCashServicesHtmlElements = services.get(2).getByXPath("descendant::li");
   checkElementPresence(commonCashServicesHtmlElements, "commonCashServicesHtmlElements", page.getTitleText());
    return commonCashServicesHtmlElements.stream().map(HtmlElement::asText)
                                         .collect(toList());
  }

  private List<String> extractItinerary(HtmlPage page)
  {
    List<HtmlElement> itinerary = page.getByXPath("//div[@class='left-testata']");
    checkElementPresence(itinerary, "itinerario", page.getTitleText());
    return itinerary.stream().map(it -> it.getFirstChild().asText()).collect(toList());
  }

  private String extractPrice(HtmlPage page)
  {
    List<HtmlElement> price = page.getByXPath("//div[@class='prezzo mt-15']");
    checkElementPresence(price, "price", page.getTitleText());
    return getTextFromFirstElement(price);
  }

  private Services extractServices(HtmlPage page)
  {
    List<HtmlElement> services = page.getByXPath("//div[@class='lg-col-4 desktop-pr-40px']/descendant::ul");

    checkElementPresence(services, "services", page.getTitleText());

    List<String> includedServices = extractIncludedServices(services, page.getTitleText());
    List<String> notIncludedServices = extractNotIncludedServices(services, page.getTitleText());

    return new Services(includedServices, notIncludedServices);
  }

  private List<String> extractIncludedServices(List<HtmlElement> services, String titleText)
  {
    List<HtmlElement> includedServicesHtmlElements = services.get(0).getByXPath("descendant::li");
    checkElementPresence(includedServicesHtmlElements, "includedServices", titleText);
    return includedServicesHtmlElements.stream().map(HtmlElement::asText).collect(toList());
  }

  private List<String> extractNotIncludedServices(List<HtmlElement> services, String titleText)
  {
    List<HtmlElement> notIncludedServicesHtmlElements = services.get(1).getByXPath("descendant::li");

    if(notIncludedServicesHtmlElements.size() == 0){
      notIncludedServicesHtmlElements = services.get(2).getByXPath("descendant::li");
    }

    checkElementPresence(notIncludedServicesHtmlElements, "notIncludedServices", titleText);
    return notIncludedServicesHtmlElements.stream().map(HtmlElement::asText)
                                          .collect(toList());
  }

  private String extractTravelDuration(HtmlPage page)
  {
    List<HtmlElement> duration = page.getByXPath("//div[@class='numeri-viaggio']/descendant::div[@class='text']");
    checkElementPresence(duration, "duration", page.getTitleText());
    return getTextFromFirstElement(duration);
  }

  private String extractTitle(HtmlPage page)
  {
    List<HtmlElement> title = page.getByXPath("//h1[@class='titolo']");
    checkElementPresence(title, "title", page.getTitleText());
    return getTextFromFirstElement(title);
  }

  private String getTextFromFirstElement(List<HtmlElement> elements)
  {
    return elements.get(0).asText();
  }

  private void checkElementPresence(List<HtmlElement> elementList, String elementName, String titleText)
  {
    if(elementList.size() == 0)
    {
      LOGGER.warn(String.format("Impossible to get element %s", elementName));
      throw new PageStructureChangedException(String.format("Impossible to get element %s. Page: %s ", elementName, titleText));
    }
  }

}
