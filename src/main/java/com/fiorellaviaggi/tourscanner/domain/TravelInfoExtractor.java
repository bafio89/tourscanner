package com.fiorellaviaggi.tourscanner.domain;

import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.Html;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlUnorderedList;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class TravelInfoExtractor
{

  public TravelInfo execute(HtmlPage page)
  {
    List<HtmlDivision> price = page.getByXPath("//div[@class='prezzo mt-15']");
    List<HtmlDivision> itinerary = page.getByXPath("//div[@class='left-testata']");
    List<String> itineraryTitle = itinerary.stream().map(it -> it.getFirstChild().asText()).collect(toList());

    List<HtmlUnorderedList> services = page.getByXPath("//div[@class='lg-col-4 desktop-pr-40px']/descendant::ul");

    List<HtmlListItem> includedServicesHtmlElements = services.get(0).getByXPath("descendant::li");
    List<String> includedServices = includedServicesHtmlElements.stream().map(HtmlListItem::asText).collect(toList());

    List<HtmlListItem> notIncludedServicesHtmlElements = services.get(1).getByXPath("descendant::li");
    List<String> notIncludedServices = notIncludedServicesHtmlElements.stream().map(HtmlListItem::asText).collect(toList());

    List<HtmlListItem> commonCashServicesHtmlElements = services.get(2).getByXPath("descendant::li");
    List<String> commonCashServices = commonCashServicesHtmlElements.stream().map(HtmlListItem::asText).collect(toList());

    //manca la cassa comune.


    System.out.println("BOBOBO");
    return null;
  }
}
