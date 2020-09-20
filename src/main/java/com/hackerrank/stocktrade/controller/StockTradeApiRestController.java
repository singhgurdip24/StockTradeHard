package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.payload.SaveTradeRequest;
import com.hackerrank.stocktrade.payload.SaveUserRequest;
import com.hackerrank.stocktrade.payload.TradeResponse;
import com.hackerrank.stocktrade.payload.UserResponse;
import com.hackerrank.stocktrade.service.TradeService;
import com.hackerrank.stocktrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StockTradeApiRestController {

    public StockTradeApiRestController() {
        ArrayList<String> trades = new ArrayList<String>();
        ArrayList<String> users = new ArrayList<String>();
    }

    private ArrayList<String> trades;
    private ArrayList<String> users;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private UserService userService;

    @DeleteMapping("/erase")
    public ResponseEntity<?> deleteAllTrades() {
        tradeService.deleteAllTrades();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/trades")
    public List<TradeResponse> getAllTrades(){
        return tradeService.getAllTrades();
    }

    @PostMapping("/trades")
    public ResponseEntity<?> postNewTrade(
        @RequestBody SaveTradeRequest saveTradeRequest
    ) throws IOException {
        boolean saved = tradeService.saveNewTrade(saveTradeRequest);
        if(!saved) {
            return new ResponseEntity<>("Duplicate Trade Id", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<?> postNewUser(
      @RequestBody SaveUserRequest saveNewUser
    ) throws IOException {
        User user = userService.saveNewUser(saveNewUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/trades/users/{userID}")
    public ResponseEntity<?> getAllTradesByUser(
      @PathVariable(value = "userID") Long userID
    ){
        return tradeService.getAllTradesByUser(userID);
    }

  @GetMapping("/stocks/{stocksSymbol}/price")
  public ResponseEntity<?> getStockHighLowForDateRange(
    @PathVariable(value = "stocksSymbol") String stocksSymbol,
    @RequestParam(value = "start", required = true) String startDate,
    @RequestParam(value = "end", required = true) String endDate
  ){
    return tradeService.getStockHighLowForDateRange(stocksSymbol, startDate, endDate);
  }



}
