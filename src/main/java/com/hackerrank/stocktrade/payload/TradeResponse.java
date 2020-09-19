package com.hackerrank.stocktrade.payload;

import com.hackerrank.stocktrade.model.User;

import java.sql.Timestamp;

public class TradeResponse {
  private Long id;
  private String type;
  private User user;
  private String symbol;
  private Integer shares;
  private Float price;
  private Timestamp timestamp;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public Integer getShares() {
    return shares;
  }

  public void setShares(Integer shares) {
    this.shares = shares;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }
}
