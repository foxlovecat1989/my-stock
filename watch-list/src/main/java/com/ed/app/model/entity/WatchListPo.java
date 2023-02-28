package com.ed.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "WatchListPo")
@Table(name = "WATCH_LIST")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WatchListPo {
    @SequenceGenerator(
            name = "WATCH_LIST_SEQUENCE",
            sequenceName = "WATCH_LIST_SEQUENCE",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "WATCH_LIST_SEQUENCE"
    )
    @Column(name = "SEQ")
    @Id
    private Long seq;

    @Column(
            name = "WATCH_LIST_ID",
            nullable = false,
            unique = true,
            updatable = false
    )
    private UUID watchListId;

    @Column(
            name = "USER_ID",
            nullable = false
    )
    private UUID userId;

    @Column(
            name = "STOCK_SYMBOL",
            nullable = false
    )
    private String stockSymbol;

    @Column(
            name = "NAME"
    )
    private String name;

    @Column(
            name = "CREATE_AT",
            updatable = false,
            nullable = false
    )
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(
            name = "LATEST_UPDATE_AT",
            insertable = false
    )
    @UpdateTimestamp
    private LocalDateTime latestUpdateAt;
}
