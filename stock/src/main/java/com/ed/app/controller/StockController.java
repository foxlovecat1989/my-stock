package com.ed.app.controller;

import com.ed.app.model.entity.StockPo;
import com.ed.app.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import yahoofinance.histquotes.HistoricalQuote;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/stock")
public class StockController {
    private final StockService stockService;

    @GetMapping
    public void insert() {
        StockPo stock = StockPo.builder()
                .symbol("2311")
                .name("2311")
                .build();
        stockService.insert(stock);
        log.info("Insert stock symbol: {}", stock.getSymbol());
    }

    @GetMapping(path = "/findAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockPo>> findAllStocks() {
        List<StockPo> stockPos = stockService.findAllStocks();

        return new ResponseEntity<>(stockPos, HttpStatus.OK);
    }

    @GetMapping(path = "/{symbol}", produces = APPLICATION_JSON_VALUE)

    public ResponseEntity<StockPo> findStockBySymbol(@PathVariable("symbol") String symbol) {
        StockPo stockPo = stockService.findExistingStockBySymbol(symbol);

        return new ResponseEntity<>(stockPo, HttpStatus.OK);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<StockPo> findStockByName(@PathVariable("stockName") String stockName) {
        StockPo stockPo = stockService.findExistingStockByName(stockName);

        return new ResponseEntity<>(stockPo, HttpStatus.OK);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<StockPo> createStock(@RequestBody StockPo stockPo) {
        var newStock = stockService.insert(stockPo);

        return new ResponseEntity<>(newStock, HttpStatus.CREATED);
    }

    @PatchMapping(consumes = APPLICATION_JSON_VALUE)
    public StockPo updateStock(@RequestBody StockPo stockPo) {

        return stockService.updateStock(stockPo);
    }

    @GetMapping(path = "/refresh")
    @Transactional
    public void refreshStockOfPrice(){
        stockService.refreshPriceOfStocks();
    }

    @GetMapping(value = {"histquotes/{symbol:.+}/{month}"}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HistoricalQuote>> queryHistoricalQuotes(
            @PathVariable("symbol") String symbol,
            @PathVariable("month") Integer month) {
        var historicalQuotes = stockService.getHistoricalQuotes(symbol, month);

        return new ResponseEntity<>(historicalQuotes, HttpStatus.OK);
    }
}
