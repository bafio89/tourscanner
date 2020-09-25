package com.fiorellaviaggi.tourscanner.controller;

import com.fiorellaviaggi.tourscanner.domain.ScraperService;
import com.fiorellaviaggi.tourscanner.domain.usecase.WeRoadUseCase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;

@RestController
public class GetToursEndPoint
{
  private WeRoadUseCase weRoadUseCase;
  private ScraperService scraperService;

  public GetToursEndPoint(WeRoadUseCase weRoadUseCase,
                          ScraperService scraperService)
  {
    this.weRoadUseCase = weRoadUseCase;
    this.scraperService = scraperService;
  }

  @GetMapping("/weroad")
  public ResponseEntity<String> GetWeRoadTours() throws Exception
  {
    weRoadUseCase.execute(scraperService.execute(new URL("https://www.weroad.it")));
    return ResponseEntity.ok("tutt appost");
  }

}
