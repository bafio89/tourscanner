package com.fiorellaviaggi.tourscanner.config;

import com.fiorellaviaggi.tourscanner.domain.ScraperService;
import com.fiorellaviaggi.tourscanner.domain.SiVolaTravelInfoExtractor;
import com.fiorellaviaggi.tourscanner.domain.SiVolaUrlExtractor;
import com.fiorellaviaggi.tourscanner.domain.TravelInfoExtractor;
import com.fiorellaviaggi.tourscanner.domain.UrlExtractor;
import com.fiorellaviaggi.tourscanner.domain.repository.JdbcTourRepository;
import com.fiorellaviaggi.tourscanner.domain.usecase.SiVolaPageCollector;
import com.fiorellaviaggi.tourscanner.domain.usecase.WeRoadPageCollector;
import com.fiorellaviaggi.tourscanner.domain.usecase.SiVolaScraperUseCase;
import com.fiorellaviaggi.tourscanner.domain.usecase.TourRepository;
import com.fiorellaviaggi.tourscanner.domain.usecase.ScraperUseCase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class UseCaseConfig
{
  @Bean
  public ScraperService scraperService()
  {
    return new ScraperService();
  }

  @Bean
  public WeRoadPageCollector weRoadPageCollector()
  {
    return new WeRoadPageCollector(new ScraperService());
  }

  @Bean
  public SiVolaPageCollector siVolaPageCollector()
  {
    return new SiVolaPageCollector(new ScraperService());
  }

  @Bean
  public TourRepository tourRepository(DataSource fiorellaViaggi)
  {
    return new JdbcTourRepository(new NamedParameterJdbcTemplate(fiorellaViaggi));
  }

  @Bean
  public ScraperUseCase weRoadScraperUseCase(WeRoadPageCollector weRoadPageCollector, TourRepository tourRepository)
  {

    return new ScraperUseCase(new UrlExtractor(), new TravelInfoExtractor(), weRoadPageCollector, tourRepository);
  }

  @Bean
  public SiVolaScraperUseCase siVolaScraperUseCase(SiVolaPageCollector siVolaPageCollector, TourRepository tourRepository)
  {
    return new SiVolaScraperUseCase(new SiVolaUrlExtractor(), new SiVolaTravelInfoExtractor(), siVolaPageCollector, tourRepository);
  }
}
