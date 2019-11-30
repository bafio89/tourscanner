package com.fiorellaviaggi.tourscanner.domain;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.URL;

public class ScraperService
{

  public HtmlPage execute(URL url) throws IOException
  {

    WebClient client = new WebClient();
    client.getOptions().setCssEnabled(false);
    client.getOptions().setJavaScriptEnabled(false);

    return client.getPage(url);

  }
}
