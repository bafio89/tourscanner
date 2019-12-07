package com.fiorellaviaggi.tourscanner;

import com.fiorellaviaggi.tourscanner.domain.ScraperService;
import com.fiorellaviaggi.tourscanner.domain.usecase.WeRoadScraperUseCase;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URL;

@EnableSwagger2
@SpringBootApplication
public class TourscannerApplication {

	private static WeRoadScraperUseCase weRoadScraperUseCase;

	private static ScraperService scraperService;

	public TourscannerApplication( WeRoadScraperUseCase weRoadScraperUseCase,
																 ScraperService scraperService)
	{
		this.weRoadScraperUseCase = weRoadScraperUseCase;
		this.scraperService = scraperService;
	}

	public static void main(String[] args) {
		SpringApplication.run(TourscannerApplication.class, args);

		try
		{
			weRoadScraperUseCase.execute(scraperService.execute(new URL("https://www.weroad.it")));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}



}
