package com.ed.app.repository;

import com.ed.app.model.entity.WatchListPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface WatchListRepository extends JpaRepository<WatchListPo, Long> {
    @Query(value = "SELECT w FROM WatchListPo w WHERE w.userId=?1 AND w.stockSymbol=?2")
    WatchListPo findByUserIdAndStockSymbol(UUID uuid, String stockSymbol);
}
