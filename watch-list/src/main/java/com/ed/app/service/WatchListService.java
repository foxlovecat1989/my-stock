package com.ed.app.service;

import com.ed.app.model.entity.WatchListPo;

import java.util.List;
import java.util.UUID;

public interface WatchListService {
    WatchListPo insert(UUID uuid, String stockSymbol);
    WatchListPo update(WatchListPo updatePo);
    void delete(UUID uuid, String stockSymbol);
    List<WatchListPo> findAllByUserId(UUID uuid);
    WatchListPo findBySeq(Long seq);
    WatchListPo findByUserIdAndStockSymbol(UUID uuid, String stockSymbol);
    WatchListPo save(WatchListPo watchListPo);
}
