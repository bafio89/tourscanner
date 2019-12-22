package com.fiorellaviaggi.tourscanner.controller;

import com.fiorellaviaggi.tourscanner.domain.ScraperService;
import com.fiorellaviaggi.tourscanner.domain.usecase.ScraperUseCase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;

@RestController
public class GetToursEndPoint
{
  private ScraperUseCase scraperUseCase;
  private ScraperService scraperService;

  public GetToursEndPoint(ScraperUseCase scraperUseCase,
                          ScraperService scraperService)
  {
    this.scraperUseCase = scraperUseCase;
    this.scraperService = scraperService;
  }

  @GetMapping("/weroad")
  public ResponseEntity<String> GetWeRoadTours() throws Exception
  {
    scraperUseCase.execute(scraperService.execute(new URL("https://www.weroad.it")));
    return ResponseEntity.ok("tutt appost");
  }

}
