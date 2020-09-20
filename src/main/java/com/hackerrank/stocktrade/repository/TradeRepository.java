package com.hackerrank.stocktrade.repository;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TradeRepository extends CrudRepository<Trade, Long> {

  List<Trade> findAllByOrderByIdAsc();

  Optional<Trade> findById(Long id);

  @Query("FROM Trade t where t.user.id = :userId")
  List<Trade> findAllByUserAsc(@Param("userId") Long userID);
}
