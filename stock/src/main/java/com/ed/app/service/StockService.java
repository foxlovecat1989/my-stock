package com.ed.app.service;


import com.ed.app.model.entity.StockPo;
import yahoofinance.histquotes.HistoricalQuote;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface StockService {
    List<StockPo> findAllStocks();

    StockPo save(StockPo po);

    StockPo findExistingStockById(Long id);

    StockPo findExistingStockByName(String name);

    StockPo insert(StockPo StockPo);

    StockPo updateStock(StockPo StockPo);

    void refreshPriceOfStocks();

    void updatePrice(
            Long tStockId,
            BigDecimal changePrice,
            BigDecimal changeInPercent,
            BigDecimal preClosed,
            BigDecimal price,
            LocalDateTime lastUpDateTime,
            Long volume
    );

    StockPo findExistingStockBySymbol(String symbol);

    List<HistoricalQuote> getHistoricalQuotes(String symbol, Integer month);
}
