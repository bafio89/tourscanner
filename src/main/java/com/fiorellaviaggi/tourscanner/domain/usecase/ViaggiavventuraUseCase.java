package com.fiorellaviaggi.tourscanner.domain.usecase;

import com.fiorellaviaggi.tourscanner.domain.CommonCash;
import com.fiorellaviaggi.tourscanner.domain.ScraperService;
import com.fiorellaviaggi.tourscanner.domain.Services;
import com.fiorellaviaggi.tourscanner.domain.TourUrl;
import com.fiorellaviaggi.tourscanner.domain.TravelInfo;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;

public class ViaggiavventuraUseCase
{
  private TourRepository tourRepository;

  private static final Logger LOGGER = LoggerFactory.getLogger(ViaggiavventuraUseCase.class);
  public static final String URLS_XPATH = "//table[@class='map_labels']//a";
  public static final String NATIONS_XPATH = "//table[@background-color='#008000' and @bordercolor='#009900']";
  public static final String INCLUDED_SERVICES_XPATH = "//table[@bgcolor='#6ebb53' and @width='650' and @border='1' and @cellpadding='2' and @cellspacing='0' and @align='center']";
  private String BASE_PATH = "http://www.viaggiavventurenelmondo.it";

  private List<String> nations = asList("Afghanistan ", "Albania", "algeria", "Andorra", "angola ",
                                        "Antigua e Dip ", "Argentina ", "Armenia ", "Australia ", "Austria ",
                                        "Azerbaijan ", "Bahamas ", "Bahrain ", "Bangladesh ", "Barbados ",
                                        "Bielorussia ", "Belgio ", "Belize ", "Benin ", "Bhutan ", "Bolivia ",
                                        "Bosnia Erzegovina ", "Botswana ", "Brasile ", "Brunei ", "Bulgaria ",
                                        "Burkina ", "Burundi ", "Cambogia ", "Camerun ", "Canada ", "capo Verde ",
                                        "Rep. Centrafricana ", "Chad ", "Chile ", "Cina ", "Colombia ",
                                        "Comoros ", "Congo ", "Congo ", "Costa Rica ",
                                        "Croazia ", "Cuba ", "Cipro ", "Repubblica Ceca ", "Danimarca ",
                                        "Gibuti ", "Dominica ", "Repubblica Dominicana ", "Timor Est ", "Ecuador ",
                                        "Egitto ", "El Salvador ", "Guinea Equatoriale ", "l'Eritrea ", "Estonia ",
                                        "Etiopia ", "Fiji ", "Finlandia ", "Francia ", "Gabon ", "Gambia ",
                                        "Georgia ", "Germania ", "Ghana ", "Grecia ", "Grenada ", "Guatemala ",
                                        "Guinea ", "Guinea-Bissau ", "Guyana ", "Haiti ", "Honduras ",
                                        "Ungheria ", "Islanda ", "India ", "Indonesia ", "Iran ", "Iraq ",
                                        "Irlanda ", "Israele ", "Italia ", "Costa d'Avorio ",
                                        "Giamaica ", "Giappone ", "Giordania ", "Kazakistan ", "Kenia ",
                                        "Kiribati ", "Corea del Nord ", "Corea del Sud ", "Kosovo ", "Kuwait ",
                                        "Kyrgyzstan ", "Laos ", "Lettonia ", "Libano ", "Lesoto ", "Liberia ",
                                        "Libia ", "Liechtenstein ", "Lituania ", "Lussemburgo ", "Macedonia ",
                                        "Madagascar ", "Malawi ", "Malesia ", "Maldive ", "Mali ", "Malta ",
                                        "Isole Marshall ", "Mauritania ", "Mauritius ", "Messico ", "Micronesia ",
                                        "Moldova ", "Monaco ", "Mongolia ", "Montenegro ", "Marocco ",
                                        "Mozambico ", "Myanmar ", "{Birmania} ", "Namibia ", "Nauru ", "Nepal ",
                                        "Olanda ", "Nuova Zelanda ", "Nicaragua ", "Niger ", "Nigeria ",
                                        "Norvegia ", "Oman ", "Pakistan ", "Palau ", "Panama ",
                                        "Papua Nuova Guinea ", "Paraguay ", "Perù ", "Filippine ", "Polonia ",
                                        "Portogallo ", "Qatar ", "Romania ", "Federazione Russa ", "Ruanda ",
                                        "Saint Kitts e Nevis ", "Santa Lucia ", "Saint Vincent e Grenadine ",
                                        "Samoa ", "San Marino ", "Sao Tome e Principe ", "Arabia Saudita ",
                                        "Senegal ", "Serbia ", "Seychelles ", "Sierra Leone ", "Singapore ",
                                        "Slovacchia ", "Slovenia ", "Isole Salomone ", "Somalia ", "SudAfrica ",
                                        "Sudan del Sud ", "Spagna ", "Sri Lanka ", "Sudan ", "Suriname ",
                                        "Swaziland ", "Svezia ", "Svizzera ", "Siria ", "Taiwan ", "Tajikistan ",
                                        "Tanzania ", "Thailandia ", "tonga ", "Trinidad & Tobago ",
                                        "Tunisia ", "Turchia ", "Turkmenistan ", "Tuvalu ", "Uganda ",
                                        "Ucraina ", "Emirati Arabi Uniti ", "Regno Unito ", "stati Uniti ",
                                        "Uruguay ", "Uzbekistan ", "Vanuatu ", "Città del Vaticano ", "Venezuela ",
                                        "Vietnam ", "yemen ", "Zambia ", "Zimbabwe ");

  private ScraperService scraperService = new ScraperService();

  public ViaggiavventuraUseCase(TourRepository tourRepository)
  {
    this.tourRepository = tourRepository;
  }

  public Set<TourUrl> execute(URL initialUrl) throws IOException
  {
    List<TravelInfo> travels = new ArrayList<>();
    List<HtmlPage> travelPagesByContinent = new ArrayList<>();
    //    List<HtmlAnchor> continentUrls = homePage.getByXPath(URLS_XPATH);
    //    continentUrls.forEach(continentUrl -> {
    //      try
    //      {
    //        travelPagesByContinent.add(scraperService.execute(new URL(continentUrl.getHrefAttribute())));
    //      }
    //      catch (IOException e)
    //      {
    //        e.printStackTrace();
    //      }
    //    });

    travelPagesByContinent.add(scraperService.execute(initialUrl));

    List<HtmlTable> travelsTables = new ArrayList<>();
    travelPagesByContinent.forEach(travelPageByContinent -> {
      travelsTables.addAll(travelPageByContinent.getByXPath(NATIONS_XPATH));
    });

    travelsTables.forEach(travelTable -> {
      {
        travelTable.getRows().forEach(row -> {
          {
            if (row.getCells().size() > 1 && !row.getCell(0).getTextContent().contains("Viaggio"))
            {
              LOGGER.info("Extracting info from: " + extractTitle(row.getCell(0).getTextContent().trim()).trim());
              String title = extractTitle(row.getCell(0).getTextContent().trim()).trim();
              String duration = extractDuration(row.getCell(0).getTextContent().trim());
              String description = "";
              if (row.getCells().size() > 1)
              {
                description = row.getCell(1).getTextContent().trim();
              }
              else
              {
                System.out.println(title);
              }
              String nation = extractNation(title, description);
              String price = row.getCell(2).getTextContent().trim().concat(" €");
              URL pageLink = null;
              String includedServices = "";
              String notIncludedServices = "";
              String commonCash = "";

              DomNodeList<HtmlElement> anchors = row.getCell(0).getElementsByTagName("a");
              if (anchors.size() > 0)
              {
                HtmlAnchor a = (HtmlAnchor) anchors.get(0);
                try
                {
                  pageLink = new URL(BASE_PATH + a.getHrefAttribute());
                  HtmlPage page = scraperService.execute(pageLink);
                  List<HtmlTable> innerPageTable = page.getByXPath(INCLUDED_SERVICES_XPATH);
                  for (HtmlTableRow innerRow : innerPageTable.get(0).getRows())
                  {
                    {
                      String cellText = innerRow.getCell(0).getTextContent();

                      if (cellText.contains("LA QUOTA NON COMPRENDE"))
                      {
                        notIncludedServices = extractServices(cellText, "(?<=LA QUOTA NON COMPRENDE ).*");
                      }
                      else if (cellText.contains("LA QUOTA COMPRENDE"))
                      {
                        String extractedIncludedServices = extractServices(cellText, "(?<=LA QUOTA COMPRENDE ).*");
                        includedServices = extractedIncludedServices;
                      }
                    }
                  }

                  for (HtmlTableRow commonCashRows : innerPageTable.get(1).getRows())
                  {
                    {
                      List<HtmlTableCell> cells = commonCashRows.getCells();
                      if (cells.size() > 2)
                      {
                        commonCash = cells.get(2).getTextContent().concat(" €");
                      }
                    }
                  }
                }
                catch (IOException e)
                {
                  e.printStackTrace();
                }
              }

              TravelInfo travel = new TravelInfo(nation, title, duration,
                                                 new Services(asList(includedServices),
                                                              asList(notIncludedServices)),
                                                 price, asList(description), new CommonCash(commonCash, asList("")), 3,
                                                 pageLink);
              if (travel.getTourLink() != null)
              {
                travels.add(travel);
              }
            }
          }
        });

      }
    });

    tourRepository.resetActiveTravel();
    travels.forEach(travel -> {
      {
        tourRepository.save(travel);
      }
    });

    return null;
  }

  private String extractNation(String title, String description)
  {
    for (String nation : nations)
    {
      {
        if (title.toLowerCase().contains(nation.toLowerCase())
          || (title.length() > nation.length() && title.substring(title.length() - 1 - nation.length()).toLowerCase()
                                                       .contains(nation.toLowerCase().trim()))
          || description.toLowerCase().contains(nation.toLowerCase())
          || description.toLowerCase().contains(nation.toLowerCase().trim().concat(","))
          || description.toLowerCase().contains(nation.toLowerCase().trim().concat(":")))
        {
          return nation.trim();
        }
      }
    }
    return "Mondo";
  }

  private String extractTitle(String content)
  {
    Pattern pattern = Pattern.compile("(\\w+).*\\W+\\n\\t");
    Matcher matcher = pattern.matcher(content);
    if (matcher.find())
    {
      return matcher.group();
    }
    return content;
  }

  private String extractDuration(String content)
  {
    Pattern pattern = Pattern.compile("(?<=Durata gg: ).*");
    Matcher matcher = pattern.matcher(content);
    if (matcher.find())
    {

      return matcher.group().replace("Vai", "").concat(" giorni");
    }
    return "";
  }

  private String extractServices(String content, String patternToFind)
  {
    Pattern pattern = Pattern.compile(patternToFind);
    Matcher matcher = pattern.matcher(content);

    if (matcher.find())
    {
      return matcher.group();
    }
    return " ";
  }

}
