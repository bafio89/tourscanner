package com.fiorellaviaggi.tourscanner;

import com.fiorellaviaggi.tourscanner.domain.ScraperService;
import com.fiorellaviaggi.tourscanner.domain.usecase.ViaggiavventuraUseCase;
import com.fiorellaviaggi.tourscanner.domain.usecase.WeRoadUseCase;
import com.fiorellaviaggi.tourscanner.domain.usecase.SiVolaScraperUseCase;
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

	private static WeRoadUseCase weRoadScraperService;
	private static SiVolaScraperUseCase siVolaScraperService;
	private static ViaggiavventuraUseCase viaggiavventuraUseCase;

	private static ScraperService scraperService;

	public TourscannerApplication(WeRoadUseCase weRoadScraperServiceUseCase,
																SiVolaScraperUseCase siVolaScraperServiceUseCase,
																ScraperService scraperService,
																ViaggiavventuraUseCase viaggiavventuraUseCase)
	{
		this.weRoadScraperService = weRoadScraperServiceUseCase;
		this.siVolaScraperService = siVolaScraperServiceUseCase;
		this.scraperService = scraperService;
		this.viaggiavventuraUseCase = viaggiavventuraUseCase;
	}

	public static void main(String[] args) {
		SpringApplication.run(TourscannerApplication.class, args);

		try
		{
//			weRoadScraperService.execute(scraperService.execute(new URL("https://www.weroad.it")));
//			siVolaScraperService.execute(scraperService.execute(new URL("https://www.sivola.it/viaggi")),
//																	 scraperService.execute(new URL("https://www.sivola.it/viaggi?page=2")));

//			viaggiavventuraUseCase.execute(new URL("http://www.viaggiavventurenelmondo.it/nuovosito/viaggi/indiceitalianuovo_.php"));
//			viaggiavventuraUseCase.execute(new URL("http://www.viaggiavventurenelmondo.it/nuovosito/viaggi/indiceeuropanuovo_.php"));
//			viaggiavventuraUseCase.execute(new URL("http://www.viaggiavventurenelmondo.it/nuovosito/viaggi/indicenordafricanuovo_.php"));
//			viaggiavventuraUseCase.execute(new URL("http://www.viaggiavventurenelmondo.it/nuovosito/viaggi/indicesaharanuovo_.php"));
//			viaggiavventuraUseCase.execute(new URL("http://www.viaggiavventurenelmondo.it/nuovosito/viaggi/indiceafricaoccnuovo_.php"));
//			viaggiavventuraUseCase.execute(new URL("http://www.viaggiavventurenelmondo.it/nuovosito/viaggi/indiceafricacentralenuovo_.php"));
//			viaggiavventuraUseCase.execute(new URL("http://www.viaggiavventurenelmondo.it/nuovosito/viaggi/indiceafricameridionalenuovo_.php"));
//			viaggiavventuraUseCase.execute(new URL("http://www.viaggiavventurenelmondo.it/nuovosito/viaggi/indicemedioorientenuovo_.php"));
//			viaggiavventuraUseCase.execute(new URL("http://www.viaggiavventurenelmondo.it/nuovosito/viaggi/indiceasiacentralenuovo_.php"));
//			viaggiavventuraUseCase.execute(new URL("http://www.viaggiavventurenelmondo.it/nuovosito/viaggi/indiceindianuovo_.php"));
//			viaggiavventuraUseCase.execute(new URL("http://www.viaggiavventurenelmondo.it/nuovosito/viaggi/indicesudestasianuovo_.php"));
//			viaggiavventuraUseCase.execute(new URL("http://www.viaggiavventurenelmondo.it/nuovosito/viaggi/indicecinanuovo_.php"));
//			viaggiavventuraUseCase.execute(new URL("http://www.viaggiavventurenelmondo.it/nuovosito/viaggi/indicemongoliasiberianuovo.php"));
//			viaggiavventuraUseCase.execute(new URL("http://www.viaggiavventurenelmondo.it/nuovosito/viaggi/indiceestremoorientenuovo_.php"));
//			viaggiavventuraUseCase.execute(new URL("http://www.viaggiavventurenelmondo.it/nuovosito/viaggi/indicenordamericanuovo_.php"));
			viaggiavventuraUseCase.execute(new URL("http://www.viaggiavventurenelmondo.it/nuovosito/viaggi/indicenordamericanuovo_.php"));
//			viaggiavventuraUseCase.execute(new URL("http://www.viaggiavventurenelmondo.it/nuovosito/viaggi/indicecentroamericanuovo_.php"));
//			viaggiavventuraUseCase.execute(new URL("http://www.viaggiavventurenelmondo.it/nuovosito/viaggi/indicesudamericanuovo_.php"));
//			viaggiavventuraUseCase.execute(new URL("http://www.viaggiavventurenelmondo.it/nuovosito/viaggi/indiceoceanianuovo_.php"));
			LOGGER.info("Process ended: OK");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}



}
