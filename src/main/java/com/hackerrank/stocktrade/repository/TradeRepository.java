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

  List<Trade> findAllByOrderByIdAsc();

  Optional<Trade> findById(Long id);

  @Query(value="FROM Trade t WHERE t.user.id = :userId order by t.id asc")
  List<Trade> findAllByUserByOrderByIdAsc(@Param("userId") Long userID);

  @Query(value="Select new com.hackerrank.stocktrade.payload.HighestLowestPrice(t.stockSymbol as symbol, MAX(t.stockPrice) as highest, MIN(t.stockPrice) as lowest) FROM Trade t WHERE t.stockSymbol = :stockSymbol AND t.tradeTimestamp >=  :startDate AND t.tradeTimestamp <= :endDate GROUP BY t.stockSymbol")
  Optional<HighestLowestPrice> getHighestLowestPrice(@Param("stockSymbol") String stockSymbol,
                                                      @Param("startDate") Date startDate,
                                                      @Param("endDate") Date endDate);

  List<Trade> findAllByStockSymbol(String stockSymbol);

  @Query(value="From Trade t WHERE t.tradeTimestamp >=  :startDate AND t.tradeTimestamp <= :endDate order by t.tradeTimestamp asc")
  List<Trade> findStocksInRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

  @Query(value="Select DISTINCT t.stockSymbol as symbol FROM Trade t")
  List<String> findDistinctStocks();

}
