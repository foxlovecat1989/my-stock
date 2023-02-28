package com.ed.app.config;

import com.ed.app.model.entity.StockPo;
import com.ed.app.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class InitialConfig {
    private final StockService stockService;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        generateStocks();
    }

    private void generateStocks() {
        List<StockPo> stockPos = List.of(
                new StockPo("2303.TW", "聯電"),
                new StockPo("2317.TW", "鴻海"),
                new StockPo("4938.TW", "和碩"),
                new StockPo("2308.TW", "台達電"),
                new StockPo("2603.TW", "長榮海"),
                new StockPo("3711.TW", "日月光控股"),
                new StockPo("1101.TW", "台泥"),
                new StockPo("8104.TW", "錸寶"),
                new StockPo("0050.TW", "元大台灣"),
                new StockPo("3380.TW", "明泰"));

        createStocks(stockPos);
        stockService.refreshPriceOfStocks();
        log.info("Finished to initial stock data");
    }

    private void createStocks(List<StockPo> stockPos){
        stockPos.forEach(stockService::insert);
    }
}
