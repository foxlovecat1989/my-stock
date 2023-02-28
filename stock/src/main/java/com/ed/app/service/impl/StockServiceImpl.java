package com.ed.app.service.impl;

import com.ed.app.model.entity.StockPo;
import com.ed.app.repository.StockPoRepository;
import com.ed.app.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static com.ed.app.constant.StockConstant.EMPTY_INPUT;

@Service
@Slf4j
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockPoRepository stockRepository;

    public Stock getStock() {
        Stock stock;
        try {
            stock = YahooFinance.get("INTC");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BigDecimal price = stock.getQuote().getPrice();
        BigDecimal change = stock.getQuote().getChangeInPercent();
        BigDecimal peg = stock.getStats().getPeg();
        BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();

        stock.print();

        return stock;
    }

    @Override
    public List<StockPo> findAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public StockPo findExistingStockById(Long id) {
        return null;
    }

    @Override
    public StockPo findExistingStockByName(String name) {
        return null;
    }

    @Override
    public StockPo insert(StockPo po) {
        return save(po);
    }

    @Override
    public StockPo save(StockPo po) {
        StockPo returnPo = stockRepository.save(po);
        log.info("Insert Stock Symbol:<{}> to DB", returnPo.getSymbol());

        return returnPo;
    }

    @Override
    public StockPo updateStock(StockPo stockPo) {
        return null;
    }

    @Override
    public void refreshPriceOfStocks() {
        List<StockPo> stockPos = findAllStocks();
        stockPos.forEach(stock -> {
            try {
                Stock stockFromYahooFinance = YahooFinance.get(stock.getSymbol());
                stock.setChangePrice(stockFromYahooFinance.getQuote().getChange());
                stock.setChangeInPercent(stockFromYahooFinance.getQuote().getChangeInPercent());
                stock.setPreviousClosed(stockFromYahooFinance.getQuote().getPreviousClose());
                stock.setPrice(stockFromYahooFinance.getQuote().getPrice());

                var date = stockFromYahooFinance.getQuote().getLastTradeTime().getTime();
                var instant = Instant.ofEpochMilli(date.getTime());
                LocalDateTime currentTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

                stock.setLastUpDateAt(currentTime);
                stock.setVolume(stockFromYahooFinance.getQuote().getVolume());
                this.updatePrice(
                        stock.getSeq(),
                        stock.getChangePrice(),
                        stock.getChangeInPercent(),
                        stock.getPreviousClosed(),
                        stock.getPrice(),
                        stock.getLastUpDateAt(),
                        stock.getVolume()
                );
            } catch (Exception e) {
                log.error("e.getCause(): {}, e.getMessage(): {}", e.getCause(), e.getMessage());
            }
        });
        log.info("Finished to update the stock price at {}", LocalDateTime.now());
    }

    @Override
    public void updatePrice(
            Long stockId,
            BigDecimal changePrice,
            BigDecimal changeInPercent,
            BigDecimal previousClosed,
            BigDecimal price,
            LocalDateTime transactionDate,
            Long volume) {
        stockRepository.updatePrice(
                stockId, changePrice, changeInPercent, previousClosed, price, transactionDate, volume);
    }

    @Override
    public StockPo findExistingStockBySymbol(String symbol) {
        return null;
    }

    @Override
    public List<HistoricalQuote> getHistoricalQuotes(String symbol, Integer month) {
        List<HistoricalQuote> historicalQuotes = null;
        var inputSymbol =
                Optional.of(symbol).orElseThrow(
                        () -> {
                            log.error(EMPTY_INPUT);
                            return new NullPointerException(EMPTY_INPUT);
                        });

        try {
            Calendar from = Calendar.getInstance();
            Calendar to = Calendar.getInstance();
            from.add(Calendar.MONTH, -month); // -1 indicate from 1 month ago

            Stock stock = YahooFinance.get(inputSymbol);
            historicalQuotes = stock.getHistory(from, to, Interval.DAILY);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return historicalQuotes;
    }
}
