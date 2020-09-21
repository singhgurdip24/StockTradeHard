package com.hackerrank.stocktrade.payload;

public class NoStatResponse extends StockStatResponse{
  String message;

  public NoStatResponse(String symbol, String message) {
    super(symbol);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
