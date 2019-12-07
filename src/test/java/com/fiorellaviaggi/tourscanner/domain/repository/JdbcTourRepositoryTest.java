package com.fiorellaviaggi.tourscanner.domain.repository;

import com.fiorellaviaggi.tourscanner.domain.CommonCash;
import com.fiorellaviaggi.tourscanner.domain.Nation;
import com.fiorellaviaggi.tourscanner.domain.Services;
import com.fiorellaviaggi.tourscanner.domain.TravelInfo;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@JdbcTest
@ContextConfiguration(classes = InMemoryDatasourceConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
public class JdbcTourRepositoryTest
{
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  private JdbcTourRepository tourRepository;

  @Test
  public void happyPath() throws MalformedURLException
  {
    tourRepository = new JdbcTourRepository(jdbcTemplate);
    Services services = new Services(asList("Servizio incluso 1", " Servizio incluso 2"),
                                     asList("servizio escluso 1", "servizio escluso 2"));
    CommonCash commonCash = new CommonCash("a description", asList("Common cash service"));

    TravelInfo travelInfoToInsert = new TravelInfo("Italia", "Italia Tour",
                                                   "10 giorni", services, "1000 $",
                                                   asList("Giorno 1", "Giorno 2"), commonCash, 1, new URL("http://www.alink.com"));
    tourRepository.save(travelInfoToInsert);

    List<Nation> nationList = jdbcTemplate.query("SELECT * FROM tourscanner.nation", mapResultInANation());
    List<TravelInfo> travelInfo = jdbcTemplate.query("SELECT * FROM tourscanner.tour", mapResultInATour());

    assertThat(nationList.get(0), is(new Nation(1, "Italia")));
    assertThat(travelInfo.get(0), is(expectedTravelInfo()));
  }

  private TravelInfo expectedTravelInfo() throws MalformedURLException
  {
    Services expectedServices = new Services(asList("Servizio incluso 1", "Servizio incluso 2"),
                                             asList("servizio escluso 1", "servizio escluso 2"));
    CommonCash expectedCommonCash = new CommonCash("a description", asList("Common cash service"));

    return new TravelInfo("1", "Italia Tour",
                                                   "10 giorni", expectedServices, "1000 $",
                                                   asList("Giorno 1", "Giorno 2"), expectedCommonCash, 1, new URL("http://www.alink.com"));
  }

  private ResultSetExtractor<List<TravelInfo>> mapResultInATour()
  {
    return (ResultSet rs) -> {

      List<TravelInfo> travelInfoList = new ArrayList<>();

      while (rs.next())
      {
        URL link_to_tour = null;
        try
        {
          link_to_tour = new URL(rs.getString("LINK_TO_TOUR"));
        }
        catch (MalformedURLException e)
        {
          e.printStackTrace();
        }
        travelInfoList.add(new TravelInfo(
          rs.getString("NATION_ID"),
          rs.getString("TITLE"),
          rs.getString("DURATION"),
          new Services(Arrays.asList(rs.getString("INCLUDED_SERVICES").split("\n ")),
                       Arrays.asList(rs.getString("NOT_INCLUDED_SERVICES").split("\n"))),
          rs.getString("PRICE"),
          Arrays.asList(rs.getString("ITINERARY").split("\n")),
          new CommonCash(rs.getString("COMMON_CASH_DESCRIPTION"),
                         Arrays.asList(rs.getString("COMMON_CASH_INCLUDED_SERVICES").split("\n "))),
          Integer.parseInt(rs.getString("COMPANY_ID")),
          link_to_tour));
      }
      return travelInfoList;
    };
  }

  private ResultSetExtractor<List<Nation>> mapResultInANation()
  {
    return (ResultSet rs) -> {

      List<Nation> nationList = new ArrayList<>();

      while (rs.next())
      {
        nationList.add(new Nation(Integer.parseInt(rs.getString("ID")), rs.getString("NAME")));
      }
      return nationList;
    };
  }

}


