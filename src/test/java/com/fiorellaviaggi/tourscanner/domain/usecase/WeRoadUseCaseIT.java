package com.fiorellaviaggi.tourscanner.domain.usecase;

import com.fiorellaviaggi.tourscanner.domain.ScraperService;
import com.fiorellaviaggi.tourscanner.domain.TravelInfoExtractor;
import com.fiorellaviaggi.tourscanner.domain.UrlExtractor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jmock.Expectations;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;

import java.net.URL;

public class WeRoadUseCaseIT
{
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery(){{
    setImposteriser(ClassImposteriser.INSTANCE);
  }};

  @Mock
  private UrlExtractor urlExtractor;

  @Mock
  private TravelInfoExtractor travelInfoExtractor;

  private ScraperService scraperService = new ScraperService();
  private WeRoadUseCase weRoadUseCase;

  @Before
  public void setUp() throws Exception
  {
    weRoadUseCase = new WeRoadUseCase(
      urlExtractor,
                                                    travelInfoExtractor,
                                                    null, null);
  }

  @Test
  public void checkAllTagsPresence() throws Exception
  {

    HtmlPage homePage = scraperService.execute(new URL("https://www.weroad.it/"));

    context.checking(new Expectations(){{
      oneOf(urlExtractor).execute(homePage);
    }});

    weRoadUseCase.execute(homePage);

  }
}
