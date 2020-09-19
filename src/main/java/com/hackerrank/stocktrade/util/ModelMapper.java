package com.hackerrank.stocktrade.util;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.payload.TradeResponse;

public class ModelMapper {

  public static TradeResponse mapTradeToTradeResponse(Trade trade) {

    TradeResponse tradeResponse = new TradeResponse();

    tradeResponse.setId(trade.getId());
    tradeResponse.setType(trade.getType());
    tradeResponse.setUser(trade.getUser());
    tradeResponse.setSymbol(trade.getStockSymbol());
    tradeResponse.setShares(trade.getStockQuantity());
    tradeResponse.setPrice(trade.getStockPrice());
    tradeResponse.setTimestamp(trade.getTradeTimestamp());

    return tradeResponse;
  }
}
