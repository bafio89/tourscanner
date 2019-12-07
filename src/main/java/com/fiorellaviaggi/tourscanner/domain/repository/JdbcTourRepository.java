package com.fiorellaviaggi.tourscanner.domain.repository;

import com.fiorellaviaggi.tourscanner.domain.TravelInfo;
import com.fiorellaviaggi.tourscanner.domain.usecase.TourRepository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

public class JdbcTourRepository implements TourRepository
{
  private String NATION_QUERY = "INSERT INTO tourscanner.nation (name) VALUES(:NAME)" +
                                " ON CONFLICT(name) DO UPDATE " +
                                  "SET name = :NAME";
  private String INSERT_TOUR_QUERY = "INSERT INTO tourscanner.tour (common_cash_description," +
    " common_cash_included_services, duration, included_services, itinerary, nation_id," +
    "not_included_services, title, price, company_id, link_to_tour )" +
    " VALUES(:COMMON_CASH_DESCRIPTION, :COMMON_CASH_INCLUDED_SERVICES," +
    " :DURATION, :INCLUDED_SERVICES, :ITINERARY, :NATION_ID, :NOT_INCLUDED_SERVICES, :TITLE," +
    " :PRICE, :COMPANY_ID, :LINK_TO_TOUR) " +
    "ON CONFLICT(link_to_tour) DO UPDATE " +
    "SET common_cash_description = :COMMON_CASH_DESCRIPTION," +
        "common_cash_included_services = :COMMON_CASH_INCLUDED_SERVICES," +
        "duration = :DURATION," +
        "included_services = :INCLUDED_SERVICES," +
        "itinerary = :ITINERARY," +
        "nation_id = :NATION_ID," +
        "not_included_services = :NOT_INCLUDED_SERVICES," +
        "title = :TITLE," +
        "price = :PRICE," +
        "company_id = :COMPANY_ID," +
        "link_to_tour = :LINK_TO_TOUR";


  private TourAdapter fromDomainToRepository;
  private final NamedParameterJdbcTemplate jdbcTemplate;

  public JdbcTourRepository(NamedParameterJdbcTemplate jdbcTemplate)
  {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void save(TravelInfo travelInfo)
  {
    fromDomainToRepository = new TourAdapter();

    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("NAME",  travelInfo.getNation());

    jdbcTemplate.update(NATION_QUERY,params, keyHolder);

    Integer nationID = (Integer) keyHolder.getKeys().get("ID");
    TourRapresentation tourRapresentation = fromDomainToRepository.execute(travelInfo, nationID);

    jdbcTemplate.update(INSERT_TOUR_QUERY, tourParams(tourRapresentation, nationID));
  }

  private MapSqlParameterSource tourParams(TourRapresentation tourRapresentation, Integer nationID){

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("COMMON_CASH_DESCRIPTION", tourRapresentation.getCommonCashDescription());
    params.addValue("COMMON_CASH_INCLUDED_SERVICES", tourRapresentation.getCommonCashIncludedServices());
    params.addValue("DURATION", tourRapresentation.getDuration());
    params.addValue("INCLUDED_SERVICES", tourRapresentation.getIncludedServices());
    params.addValue("ITINERARY", tourRapresentation.getItinerary());
    params.addValue("NATION_ID", nationID);
    params.addValue("NOT_INCLUDED_SERVICES", tourRapresentation.getNotIncludedServices());
    params.addValue("TITLE", tourRapresentation.getTravelName());
    params.addValue("PRICE", tourRapresentation.getPrice());
    params.addValue("COMPANY_ID", tourRapresentation.getCompanyId());
    params.addValue("LINK_TO_TOUR", tourRapresentation.getTourLink());

    return params;
  }
}
