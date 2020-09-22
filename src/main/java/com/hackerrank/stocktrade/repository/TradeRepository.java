package com.hackerrank.stocktrade.repository;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.payload.HighestLowestPrice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TradeRepository extends CrudRepository<Trade, Long> {

  // Get all trades sorted in Ascending order
  List<Trade> findAllByOrderByIdAsc();

  // Find trade by Id
  Optional<Trade> findById(Long id);

  // Find all trades for a given stock symbol
  List<Trade> findAllByStockSymbol(String stockSymbol);

  // Find all trades by user sorted in ascending order
  @Query(value="FROM Trade t WHERE t.user.id = :userId order by t.id asc")
  List<Trade> findAllByUserByOrderByIdAsc(@Param("userId") Long userID);

  // Find the highest and lowest stock price for a stock symbol in given date range
  @Query(value="Select new com.hackerrank.stocktrade.payload.HighestLowestPrice(t.stockSymbol as symbol, MAX(t.stockPrice) as highest, MIN(t.stockPrice) as lowest) FROM Trade t WHERE t.stockSymbol = :stockSymbol AND t.tradeTimestamp >=  :startDate AND t.tradeTimestamp <= :endDate GROUP BY t.stockSymbol")
  Optional<HighestLowestPrice> getHighestLowestPrice(@Param("stockSymbol") String stockSymbol, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

  // Find all trades in given date range sorted in ascending order
  @Query(value="From Trade t WHERE t.tradeTimestamp >=  :startDate AND t.tradeTimestamp <= :endDate order by t.tradeTimestamp asc")
  List<Trade> findStocksInRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

  // Find distinct stock symbols
  @Query(value="Select DISTINCT t.stockSymbol as symbol FROM Trade t")
  List<String> findDistinctStocks();

}
