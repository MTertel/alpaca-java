package io.github.mainstringargs.polygon;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.Sets;
import io.github.mainstringargs.polygon.domain.historic.quotes.Quotes;
import io.github.mainstringargs.polygon.domain.historic.trades.Tick;
import io.github.mainstringargs.polygon.domain.historic.trades.Trades;
import io.github.mainstringargs.polygon.domain.meta.Exchange;
import io.github.mainstringargs.polygon.domain.meta.SymbolAnalystRatings;
import io.github.mainstringargs.polygon.domain.meta.SymbolDetails;
import io.github.mainstringargs.polygon.domain.meta.SymbolDividend;
import io.github.mainstringargs.polygon.domain.meta.SymbolEarning;
import io.github.mainstringargs.polygon.domain.meta.SymbolEndpoints;
import io.github.mainstringargs.polygon.domain.meta.SymbolFinancial;
import io.github.mainstringargs.polygon.domain.meta.SymbolNews;
import io.github.mainstringargs.polygon.domain.reference.Market;
import io.github.mainstringargs.polygon.domain.reference.Split;
import io.github.mainstringargs.polygon.domain.reference.Ticker;
import io.github.mainstringargs.polygon.domain.reference.TypesMapping;
import io.github.mainstringargs.polygon.enums.ChannelType;
import io.github.mainstringargs.polygon.enums.Locale;
import io.github.mainstringargs.polygon.enums.Sort;
import io.github.mainstringargs.polygon.nats.PolygonStreamListener;
import io.github.mainstringargs.polygon.nats.message.ChannelMessage;
import io.github.mainstringargs.polygon.rest.exceptions.PolygonAPIException;

/**
 * The Class Example.
 */
public class PolygonExample {


  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    PolygonAPI polygonAPI = new PolygonAPI();

    String ticker = "MSFT";


    try {
      SymbolEndpoints symbolEndPoints = polygonAPI.getSymbolEndpoints(ticker);

      System.out.println("\n\n" + ticker + " Symbol Endpoints:");
      System.out.println("\t" + symbolEndPoints.getSymbol().getSymbol() + " "
          + symbolEndPoints.getSymbol().getName() + " " + symbolEndPoints.getSymbol().getType()
          + symbolEndPoints.getSymbol().getUrl() + " " + symbolEndPoints.getSymbol().getUpdated());
      System.out.println("\t" + symbolEndPoints.getEndpoints());



    } catch (PolygonAPIException e) {
      e.printStackTrace();
    }

    try {
      SymbolDetails symbolDetails = polygonAPI.getSymbolDetails(ticker);

      System.out.println("\n\n" + ticker + " Symbol Details:");
      System.out.println("\t" + symbolDetails);


    } catch (PolygonAPIException e) {
      e.printStackTrace();
    }

    try {
      SymbolAnalystRatings symbolAnalystRatings = polygonAPI.getSymbolAnalystRatings(ticker);

      System.out.println("\n\n" + ticker + " Symbol Analyst Ratings:");
      System.out.println("\t" + symbolAnalystRatings);


    } catch (PolygonAPIException e) {
      e.printStackTrace();
    }

    try {
      List<SymbolDividend> symbolDividends = polygonAPI.getSymbolDividends(ticker);

      System.out.println("\n\n" + ticker + " Symbol Dividends:");

      for (SymbolDividend div : symbolDividends)
        System.out.println("\t" + div);


    } catch (PolygonAPIException e) {
      e.printStackTrace();
    }


    try {
      List<SymbolEarning> symbolEarnings = polygonAPI.getSymbolEarnings(ticker);

      System.out.println("\n\n" + ticker + " Symbol Earnings:");

      for (SymbolEarning earning : symbolEarnings)
        System.out.println("\t" + earning);


    } catch (PolygonAPIException e) {
      e.printStackTrace();
    }


    try {
      List<SymbolFinancial> symbolFinancials = polygonAPI.getSymbolFinancials(ticker);

      System.out.println("\n\n" + ticker + " Symbol Financials:");
      for (SymbolFinancial financial : symbolFinancials)
        System.out.println("\t" + financial);


    } catch (PolygonAPIException e) {
      e.printStackTrace();
    }


    try {
      List<SymbolNews> symbolNews = polygonAPI.getSymbolNews(ticker);

      System.out.println("\n\n" + ticker + " Symbol News:");
      for (SymbolNews newsItem : symbolNews)
        System.out.println("\t" + newsItem);


    } catch (PolygonAPIException e) {
      e.printStackTrace();
    }

    try {
      List<Ticker> tickers =
          polygonAPI.getTickers(Sort.TICKER_ASC, null, null, Locale.US, "Tech", null, null, null)
              .getTickers();

      System.out.println("\n\n" + "Tech" + " Search US Tickers");
      for (Ticker tickerItem : tickers)
        System.out.println("\t" + tickerItem);


    } catch (PolygonAPIException e) {
      e.printStackTrace();
    }

    try {
      List<Market> markets = polygonAPI.getMarkets();

      System.out.println("\n\n" + " Markets:");
      for (Market market : markets)
        System.out.println("\t" + market);


    } catch (PolygonAPIException e) {
      e.printStackTrace();
    }


    try {
      List<io.github.mainstringargs.polygon.domain.reference.Locale> locales =
          polygonAPI.getLocales();

      System.out.println("\n\n" + " Locales:");
      for (io.github.mainstringargs.polygon.domain.reference.Locale locale : locales)
        System.out.println("\t" + locale);


    } catch (PolygonAPIException e) {
      e.printStackTrace();
    }


    try {
      TypesMapping typesMapping = polygonAPI.getTypesMapping();

      System.out.println("\n\n" + "typesMapping");
      System.out.println("\t" + typesMapping.getTypes());
      System.out.println("\t" + typesMapping.getIndexTypes());

    } catch (PolygonAPIException e) {
      e.printStackTrace();
    }

    try {
      List<Split> splits = polygonAPI.getSplits(ticker);

      System.out.println("\n\n" + ticker + " Split:");
      for (Split splitItems : splits)
        System.out.println("\t" + splitItems);


    } catch (PolygonAPIException e) {
      e.printStackTrace();
    }

    try {
      List<Exchange> exchanges = polygonAPI.getExchanges();

      System.out.println("\n\n" + "exchanges");
      for (Exchange exchange : exchanges)
        System.out.println("\t" + exchange);


    } catch (PolygonAPIException e) {
      e.printStackTrace();
    }

    try {
      Trades trades = polygonAPI.getHistoricTrades(ticker, LocalDate.of(2019, 5, 7), null, null);

      System.out.println("\n\n" + ticker + " Trades on " + LocalDate.of(2019, 5, 7) + ": ");
      System.out.println("map " + trades.getMap());
      System.out.println("ticks");
      for (Tick tick : trades.getTicks())
        System.out.println("\t" + tick);

    } catch (PolygonAPIException e) {
      e.printStackTrace();
    }

    try {
      Quotes quotes = polygonAPI.getHistoricQuotes(ticker, LocalDate.of(2019, 5, 7), null, null);

      System.out.println("\n\n" + ticker + " Quotes on " + LocalDate.of(2019, 5, 1) + ": ");
      System.out.println("map " + quotes.getMap());
      System.out.println("ticks");
      for (io.github.mainstringargs.polygon.domain.historic.quotes.Tick tick : quotes.getTicks())
        System.out.println("\t" + tick);

    } catch (PolygonAPIException e) {
      e.printStackTrace();
    }



    polygonAPI.addPolygonStreamListener(new PolygonStreamListener() {

      @Override
      public void streamUpdate(String ticker, ChannelType channelType, ChannelMessage message) {
        System.out.println("===> streamUpdate " + ticker + " " + channelType + " " + message);

      }

      @Override
      public Map<String, Set<ChannelType>> getStockChannelTypes() {
        Map<String, Set<ChannelType>> subscribedTypes = new HashMap<>();
        subscribedTypes.put(ticker, Sets.newHashSet(ChannelType.values()));
        return subscribedTypes;
      }
    });

    try {
      Thread.sleep(10000L);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    System.exit(0);

  }

}
