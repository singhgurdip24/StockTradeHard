package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.payload.*;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.repository.UserRepository;
import com.hackerrank.stocktrade.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TradeService {

  @Autowired
  private TradeRepository tradeRepository;

  @Autowired
  private UserRepository userRepository;

  public List<TradeResponse> getAllTrades(){
    List<Trade> trades = tradeRepository.findAllByOrderByIdAsc();

    return trades.stream().map((ModelMapper::mapTradeToTradeResponse)).collect(Collectors.toList());
  }

  public boolean saveNewTrade(SaveTradeRequest saveTradeRequest) {

    if (tradeRepository.findById(saveTradeRequest.getId()).isPresent()) {
      return false;
    }

    //check if user exists
    //if not then add the user
    User user = userRepository.findById(saveTradeRequest.getUser().getId())
        .orElse(userRepository.save(saveTradeRequest.getUser()));

    Trade trade = new Trade();
    trade.setId(saveTradeRequest.getId());
    trade.setType(saveTradeRequest.getType());
    trade.setUser(user);
    trade.setStockSymbol(saveTradeRequest.getSymbol());
    trade.setStockQuantity(saveTradeRequest.getShares());
    trade.setStockPrice(saveTradeRequest.getPrice());
    trade.setTradeTimestamp(saveTradeRequest.getTimestamp());

    tradeRepository.save(trade);
    return true;
  }

  public void deleteAllTrades() {
    tradeRepository.deleteAll();
  }

  public ResponseEntity<?> getAllTradesByUser(Long userID) {

    if (userRepository.findById(userID).isPresent()){
      List<Trade> tradeList = tradeRepository.findAllByUserByOrderByIdAsc(userID);
      return new ResponseEntity<>(tradeList,HttpStatus.OK);
    }
    return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);

  }

  public ResponseEntity<?> getStockHighLowForDateRange(String stockSymbol, Date startDate, Date endDate) {

    List<Trade> trade = tradeRepository.findAllByStockSymbol(stockSymbol);

    if(trade.isEmpty()){
      return new ResponseEntity<>(new ApiResponse("Stock Symbol does not exist"), HttpStatus.NOT_FOUND);
    }

    Optional<HighestLowestPrice> priceDetail = tradeRepository.getHighestLowestPrice(stockSymbol,startDate,endDate);
    if(priceDetail.isPresent()){
      return new ResponseEntity<>(priceDetail.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ApiResponse("There are no trades in the given date range"), HttpStatus.OK);

  }

  public ResponseEntity<?> getStockFluctuations(Date startDate, Date endDate) {

    List<StockStatResponse> fluctuationCountResponses= new ArrayList<>();

    List<Trade> tradesList = tradeRepository.findStocksInRange(startDate,endDate);
    List<String> stocksInTradeList = tradesList.stream().map(trade -> trade.getStockSymbol()).collect(Collectors.toList());
    List<String> stocks = tradeRepository.findDistinctStocks();

    stocks.removeAll(stocksInTradeList);
    stocks.forEach( stock -> fluctuationCountResponses.add(new NoStatResponse(stock,"There are no trades in the given date range")));

    HashMap<String, List<Trade>> symbolTradeMap = new HashMap<>();

    for (Trade trade: tradesList) {
      String stockSymbol = trade.getStockSymbol();

      if(!symbolTradeMap.containsKey(stockSymbol)){
        List<Trade> symbolTradeList = new ArrayList<>();
        symbolTradeList.add(trade);
        symbolTradeMap.put(stockSymbol, symbolTradeList);
      } else {
        List<Trade> updatedSymbolTradeList = symbolTradeMap.get(stockSymbol);
        updatedSymbolTradeList.add(trade);
        symbolTradeMap.put(stockSymbol,updatedSymbolTradeList);
      }
    }

    symbolTradeMap.forEach((symbol, value) -> {
      Long count = countFluctuations(value);
      HashMap<String, Float> result = calculate(value);
      fluctuationCountResponses.add(new FluctuationCountResponse(symbol, count, result.get("MaxRise"), result.get("MaxFall")));
    });

    fluctuationCountResponses.sort(Comparator.comparing(StockStatResponse::getSymbol));

    return new ResponseEntity<>(fluctuationCountResponses,HttpStatus.OK);
  }

  private Long countFluctuations(List<Trade> tradeList){
    Long count = 1L;

    return count;
  }

  private HashMap<String,Float> calculate(List<Trade> tradeList){
    HashMap<String,Float> result = new HashMap<>();
    result.put("MaxRise",100.00F);
    result.put("MaxFall",50.00F);
    return result;
  }
}
