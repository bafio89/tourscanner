package com.fiorellaviaggi.tourscanner.domain;

import com.fiorellaviaggi.tourscanner.domain.usecase.TourRepository;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ViaggiavventuraUseCaseTest
{
  private ScraperService scraperService = new ScraperService();

  private ViaggiavventuraUseCase urlExtractor;

  @Autowired
  private TourRepository tourRepository;

  @Test
  public void extractUrl() throws Exception
  {
    urlExtractor = new ViaggiavventuraUseCase(tourRepository);

    Set<TourUrl> result = urlExtractor.execute(new URL("http://www.viaggiavventurenelmondo.it/nuovosito/viaggi/indiceitalianuovo_.php"));
    assertThat(result.size(), is(63));
  }
}
