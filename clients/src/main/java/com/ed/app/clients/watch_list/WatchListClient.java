package com.ed.app.clients.watch_list;


import com.ed.app.model.entity.WatchListPo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "watch-list")
public interface WatchListClient {
    @PostMapping(value = "api/v1/add-stock-watch-list")
    WatchListPo addStockToWatchList(AddStock2WatchListRequest addStock2WatchListRequest);
}
