package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.payload.SaveTradeRequest;
import com.hackerrank.stocktrade.payload.TradeResponse;
import com.hackerrank.stocktrade.service.TradeService;
import com.hackerrank.stocktrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
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
    ) {
        // return 0 if duplicate trade id is present
        boolean saved = tradeService.saveNewTrade(saveTradeRequest);
        if(!saved) {
            return new ResponseEntity<>("Duplicate Trade Id", HttpStatus.BAD_REQUEST);
        }
        // successfully saved
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/trades/users/{userID}")
    public ResponseEntity<?> getAllTradesByUser(
        @PathVariable(value = "userID") Long userID
    ){
        return tradeService.getAllTradesByUser(userID);
    }

    @GetMapping("/stocks/{stockSymbol}/price")
    public ResponseEntity<?> getStockHighLowForDateRange(
        @PathVariable(value = "stockSymbol") String stockSymbol,
        @RequestParam(value = "start", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
        @RequestParam(value = "end", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate
    ){
        return tradeService.getStockHighLowForDateRange(stockSymbol, startDate, endDate);
    }

    @GetMapping("/stocks/stats")
    public ResponseEntity<?> getStockFluctuations(
      @RequestParam(value = "start", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
      @RequestParam(value = "end", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate
    ){
        return tradeService.getStockFluctuations(startDate, endDate);
    }
}
