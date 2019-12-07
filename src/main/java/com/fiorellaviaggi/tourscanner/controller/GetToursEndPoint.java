package com.fiorellaviaggi.tourscanner.controller;

import com.fiorellaviaggi.tourscanner.domain.ScraperService;
import com.fiorellaviaggi.tourscanner.domain.usecase.WeRoadScraperUseCase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;

@RestController
public class GetToursEndPoint
{
  private WeRoadScraperUseCase weRoadScraperUseCase;
  private ScraperService scraperService;

  public GetToursEndPoint(WeRoadScraperUseCase weRoadScraperUseCase,
                          ScraperService scraperService)
  {
    this.weRoadScraperUseCase = weRoadScraperUseCase;
    this.scraperService = scraperService;
  }

  @GetMapping("/weroad")
  public ResponseEntity<String> GetWeRoadTours() throws Exception
  {
    weRoadScraperUseCase.execute(scraperService.execute(new URL("https://www.weroad.it")));
    return ResponseEntity.ok("tutt appost");
  }

}
