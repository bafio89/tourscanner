package com.fiorellaviaggi.tourscanner.domain.usecase;

import com.fiorellaviaggi.tourscanner.domain.TourUrl;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Page
{
  private HtmlPage htmlPage;
  private TourUrl url;

  public Page(HtmlPage htmlPage, TourUrl url)
  {
    this.htmlPage = htmlPage;

    this.url = url;
  }

  public HtmlPage getHtmlPage()
  {
    return htmlPage;
  }

  public TourUrl getTourUrl()
  {
    return url;
  }
}
