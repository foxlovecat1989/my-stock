package com.ed.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "StockPo")
@Table(name = "STOCK")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StockPo {
    @SequenceGenerator(
            name = "STOCK_SEQUENCE",
            sequenceName = "STOCK_SEQUENCE",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "STOCK_SEQUENCE"
    )
    @Column(name = "SEQ")
    @Id
    private Long seq;

    @Column(
            name = "SYMBOL",
            nullable = false,
            updatable = false,
            unique = true,
            columnDefinition = "TEXT"
    )
    private String symbol;

    @Column(
            name = "NAME",
            nullable = false,
            unique = true,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "PREVIOUS_CLOSED",
            columnDefinition = "Decimal(10,2) default '0.00'"
    )
    private BigDecimal previousClosed;

    @Column(
            name = "PRICE",
            columnDefinition = "Decimal(10,2) default '0.00'"
    )
    private BigDecimal price;

    @Column(
            name = "CHANGE_PRICE",
            columnDefinition = "Decimal(10,2) default '0.00'"
    )
    private BigDecimal changePrice;

    @Column(
            name = "CHANGE_IN_PERCENT",
            columnDefinition = "Decimal(10,2) default '0.00'"
    )
    private BigDecimal changeInPercent;


    @Column(
            name = "VOLUME"
    )
    private Long volume;

    @Column(
            name = "LAST_UPDATE_AT",
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime lastUpDateAt;

    public StockPo(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }
}
