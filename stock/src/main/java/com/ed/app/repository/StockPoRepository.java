package com.ed.app.repository;


import com.ed.app.model.entity.StockPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public interface StockPoRepository extends JpaRepository<StockPo, Long> {
    @Transactional
    @Modifying
    @Query(value =
            "UPDATE StockPo s " +
                    "SET " +
                    "s.changePrice=?2, " +
                    "s.changeInPercent=?3, " +
                    "s.previousClosed=?4, " +
                    "s.price=?5, " +
                    "s.lastUpDateAt=?6, " +
                    "s.volume=?7 " +
                    "WHERE s.seq=?1")
    void updatePrice(
            @Param("id") Long id,
            @Param("changePrice") BigDecimal changePrice,
            @Param("changeInPercent") BigDecimal changeInPercent,
            @Param("previousClosed") BigDecimal previousClosed,
            @Param("price") BigDecimal price,
            @Param("lastUpDateTime") LocalDateTime lastUpDateTime,
            @Param("volume") Long volume
    );

    @Query(value = "SELECT t FROM StockPo t WHERE t.symbol=?1")
    Optional<StockPo> findStockBySymbol(String symbol);

    @Query(value = "SELECT t FROM StockPo t WHERE t.name=?1")
    Optional<StockPo> findStockByName(String name);
}
