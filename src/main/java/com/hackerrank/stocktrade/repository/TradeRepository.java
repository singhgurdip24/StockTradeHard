package com.hackerrank.stocktrade.repository;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TradeRepository extends CrudRepository<Trade, Long> {

  List<Trade> findAllByOrderByIdAsc();
}
