package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.payload.SaveTradeRequest;
import com.hackerrank.stocktrade.payload.TradeResponse;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.repository.UserRepository;
import com.hackerrank.stocktrade.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradeService {

  @Autowired
  private TradeRepository tradeRepository;

  @Autowired
  private UserRepository userRepository;

  public List<TradeResponse> getAllTrades(){
    List<Trade> trades = tradeRepository.findAllByOrderByIdAsc();

    return trades.stream().map((trade -> {
      return ModelMapper.mapTradeToTradeResponse(trade);
    })).collect(Collectors.toList());
  }

  public boolean saveNewTrade(SaveTradeRequest saveTradeRequest) {

    if (tradeRepository.findById(saveTradeRequest.getId()).isPresent()) {
      return false;
    }

    Trade trade = new Trade();

    trade.setId(saveTradeRequest.getId());
    trade.setType(saveTradeRequest.getType());

    //check if user exists
    //if not then add the user
    User user = userRepository.findById(saveTradeRequest.getUser().getId())
        .orElse(userRepository.save(saveTradeRequest.getUser()));

    trade.setUser(user);
    trade.setStockSymbol(saveTradeRequest.getSymbol());
    trade.setStockQuantity(saveTradeRequest.getShares());
    trade.setStockPrice(saveTradeRequest.getPrice());
    trade.setTradeTimestamp(saveTradeRequest.getTimestamp());

    tradeRepository.save(trade);

    return true;
  }

  public void deleteAllTrades() {

    /*
    get List< User > by using findAllById(ids)
    deleteAll(List< User > entities)
    */

    //List<Trade> tradeList = tradeRepository.findAllByOrderByIdAsc();
    tradeRepository.deleteAll();

  }
}
