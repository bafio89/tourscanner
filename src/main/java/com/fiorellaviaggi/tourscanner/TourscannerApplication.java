package com.fiorellaviaggi.tourscanner;

import com.fiorellaviaggi.tourscanner.domain.ScraperService;
import com.fiorellaviaggi.tourscanner.domain.usecase.ScraperUseCase;
import com.fiorellaviaggi.tourscanner.domain.usecase.SiVolaScraperUseCase;
import com.fiorellaviaggi.tourscanner.domain.usecase.WeRoadPageCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URL;

@EnableSwagger2
@SpringBootApplication
public class TourscannerApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(TourscannerApplication.class);

	private static ScraperUseCase weRoadScraperService;
	private static SiVolaScraperUseCase siVolaScraperService;

	private static ScraperService scraperService;

	public TourscannerApplication(ScraperUseCase weRoadScraperService,
																SiVolaScraperUseCase siVolaScraperService,
																ScraperService scraperService)
	{
		this.weRoadScraperService = weRoadScraperService;
		this.siVolaScraperService = siVolaScraperService;
		this.scraperService = scraperService;
	}

	public static void main(String[] args) {
		SpringApplication.run(TourscannerApplication.class, args);

		try
		{
			weRoadScraperService.execute(scraperService.execute(new URL("https://www.weroad.it")));
//			siVolaScraperService.execute(scraperService.execute(new URL("https://www.sivola.it/viaggi")),
//																	 scraperService.execute(new URL("https://www.sivola.it/viaggi?page=2")));

			LOGGER.info("Process ended: OK");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}



}
