package com.hackerrank.stocktrade.payload;

public class StockStatResponse {

  private String symbol;

  public StockStatResponse(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

}
