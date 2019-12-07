package com.fiorellaviaggi.tourscanner.config;

import com.fiorellaviaggi.tourscanner.domain.ScraperService;
import com.fiorellaviaggi.tourscanner.domain.TravelInfoExtractor;
import com.fiorellaviaggi.tourscanner.domain.UrlExtractor;
import com.fiorellaviaggi.tourscanner.domain.repository.JdbcTourRepository;
import com.fiorellaviaggi.tourscanner.domain.usecase.PageCollector;
import com.fiorellaviaggi.tourscanner.domain.usecase.TourRepository;
import com.fiorellaviaggi.tourscanner.domain.usecase.WeRoadScraperUseCase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class UseCaseConfig
{
  @Bean
  public ScraperService scraperService(){
    return new ScraperService();
  }

  @Bean
  public PageCollector pageCollector(){
    return new PageCollector(new ScraperService());
  }

  @Bean
  public TourRepository tourRepository(DataSource fiorellaViaggi){
    return new JdbcTourRepository(new NamedParameterJdbcTemplate(fiorellaViaggi));
  }

  @Bean
  public WeRoadScraperUseCase weRoadScraperUseCase(PageCollector pageCollector, TourRepository tourRepository){

    return new WeRoadScraperUseCase(new UrlExtractor(), new TravelInfoExtractor(),pageCollector,tourRepository);
  }
}
