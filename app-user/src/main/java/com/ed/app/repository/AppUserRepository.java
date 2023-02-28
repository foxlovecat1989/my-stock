package com.ed.app.repository;

import com.ed.app.model.entity.AppUserPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUserPo, Long> {
    @Query("SELECT a FROM AppUserPo a WHERE a.userId=?1")
    AppUserPo findAppUserByUserId(UUID userId);
}
