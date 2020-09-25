package com.fiorellaviaggi.tourscanner.domain.repository;

import org.postgresql.Driver;
import ru.yandex.qatools.embed.postgresql.EmbeddedPostgres;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;

public class InMemoryDatasourceConfiguration
{
  private String url;

  @Bean(destroyMethod = "stop")
  public EmbeddedPostgres embeddedPostgres() throws IOException
  {
    EmbeddedPostgres postgres = new EmbeddedPostgres();
    url = postgres.start("localhost", 3306, "postgres", "postgres", "developer");
    postgres.getProcess().get().importFromFile(
      new File(Thread.currentThread().getContextClassLoader().getResource("schema.sql").getFile()));
    postgres.getProcess().get().importFromFile(
      new File(Thread.currentThread().getContextClassLoader().getResource("data.sql").getFile()));
    return postgres;
  }

  @DependsOn("embeddedPostgres")
  @Bean
  public DataSource dataSource() {
    return new SimpleDriverDataSource(new Driver(), url);
  }

  @Bean
  public JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(dataSource());
  }

  @Bean
  public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
    return new NamedParameterJdbcTemplate(dataSource());
  }
}
