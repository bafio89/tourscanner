package com.fiorellaviaggi.tourscanner.domain;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import static java.util.Collections.emptyList;

public class SiVolaTravelInfoExtractor extends TravelInfoExtractor
{
  private static final String TITLE_XPATH = "//div[contains(@class, 'nome-viaggio')]/h1";
  private static final String TRAVEL_DURATION_XPATH = "//div[contains(@class, 'card-dettaglio')]/descendant::div[@class='h4']";
  private static final String SERVICES_XPATH = "//div[@class='col-12 col-lg-6']/descendant::ul";
  private static final String PRICE_XPATH = "//span[@class='text-success h1 font-weight-bolder d-block']";
  private static final String ITINERARY_XPATH = "//h2[@class='h4 font-weight-bolder pr-1']";


  private Integer COMPANY_ID = 2;

  @Override
  public String getTitleXpath()
  {
    return TITLE_XPATH;
  }

  @Override
  public String getTravelDurationXPath(){
    return TRAVEL_DURATION_XPATH;
  }

  @Override
  public String getServicesXpath(){
    return SERVICES_XPATH;
  }

  @Override
  public String getPriceXpath(){
    return PRICE_XPATH;
  }

  @Override
  public String getItineraryXpath(){
    return ITINERARY_XPATH;
  }

  @Override
  public Integer getCompanyId(){
    return COMPANY_ID;
  }

  @Override
  public CommonCash extractCommonCash(HtmlPage page){
    return new CommonCash("Non prevista", emptyList());
  }

}
