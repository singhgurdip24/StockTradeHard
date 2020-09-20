package com.hackerrank.stocktrade.repository;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.payload.HighestLowestPrice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TradeRepository extends CrudRepository<Trade, Long> {

  List<Trade> findAllByOrderByIdAsc();

  Optional<Trade> findById(Long id);

  @Query("FROM Trade t WHERE t.user.id = :userId order by t.id asc")
  List<Trade> findAllByUserByOrderByIdAsc(@Param("userId") Long userID);

  @Query(value="Select new com.hackerrank.stocktrade.payload.HighestLowestPrice (t.stockSymbol, MAX(t.stockPrice), MIN(t" +
    ".stockPrice)) FROM Trade t WHERE t.stockSymbol = :stockSymbol AND t.tradeTimestamp >=  :startDate AND t.tradeTimestamp <= " +
    ":endDate GROUP BY t.stockPrice")
  HighestLowestPrice getHighestLowestPrice(@Param("stockSymbol") String stockSymbol,
                                                      @Param("startDate") Timestamp startDate,
                                                      @Param("endDate") Timestamp endDate);
}
