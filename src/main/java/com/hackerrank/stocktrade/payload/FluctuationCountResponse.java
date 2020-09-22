package com.hackerrank.stocktrade.payload;

import javax.persistence.Column;

public class FluctuationCountResponse extends StockStatResponse{

  private Long fluctuations;
  
  private Float max_rise;
  private Float max_fall;

  public FluctuationCountResponse(String symbol, Long fluctuations, Float max_rise, Float max_fall) {
    super(symbol);
    this.fluctuations = fluctuations;
    this.max_rise = max_rise;
    this.max_fall = max_fall;
  }

  public Long getFluctuations() {
    return fluctuations;
  }

  public void setFluctuations(Long fluctuations) {
    this.fluctuations = fluctuations;
  }

  public Float getMax_rise() {
    return max_rise;
  }

  public void setMax_rise(Float max_rise) {
    this.max_rise = max_rise;
  }

  public Float getMax_fall() {
    return max_fall;
  }

  public void setMax_fall(Float max_fall) {
    this.max_fall = max_fall;
  }
}
