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

@Entity(name = "AppUserPo")
@Table(name = "APP_USER")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppUserPo {
    @SequenceGenerator(
            name = "APP_USER_SEQUENCE",
            sequenceName = "APP_USER_SEQUENCE",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "APP_USER_SEQUENCE"
    )
    @Column(name = "SEQ")
    @Id
    private Long seq;

    @Column(
            name = "USER_ID",
            nullable = false,
            updatable = false,
            unique = true,
            columnDefinition = "TEXT"
    )
    private UUID userId;

    @Column(
            name = "USER_NAME",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String userName;

    @Column(
            name = "CREATE_AT",
            nullable = false,
            updatable = false
    )
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(
            name = "UPDATE_AT",
            nullable = false,
            insertable = false
    )
    @UpdateTimestamp
    private LocalDateTime updateAt;
}
