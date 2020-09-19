package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.payload.SaveTradeRequest;
import com.hackerrank.stocktrade.payload.SaveUserRequest;
import com.hackerrank.stocktrade.payload.TradeResponse;
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
    void erase() {
        trades.clear();
    }

    @GetMapping("/trades")
    public List<TradeResponse> getAllTrades(){
        return tradeService.getAllTrades();
    }

    @PostMapping("/trades")
    public String postNewTrade(
        @RequestBody SaveTradeRequest saveTradeRequest
    ) throws IOException {
        Trade trade = tradeService.saveNewTrade(saveTradeRequest);

        //return new ResponseEntity<>(HttpStatus.OK);
      return "OK";
    }

    @PostMapping("/users")
    public String postNewUser(
      @RequestBody SaveUserRequest saveNewUser
    ) throws IOException {
        User user = userService.saveNewUser(saveNewUser);

        //return new ResponseEntity<>(HttpStatus.OK);
        return user.getName();
    }


}
