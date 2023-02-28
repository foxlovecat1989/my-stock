package com.ed.app.service.impl;

import com.ed.app.clients.watch_list.AddStock2WatchListRequest;
import com.ed.app.clients.watch_list.WatchListClient;
import com.ed.app.model.entity.AppUserPo;
import com.ed.app.model.entity.WatchListPo;
import com.ed.app.repository.AppUserRepository;
import com.ed.app.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public record AppUserServiceImpl(
        AppUserRepository appUserRepository,
        WatchListClient watchListClient) implements AppUserService {

    @Override
    public AppUserPo createAppUser(AppUserPo appUserPo) {
        return appUserRepository.save(appUserPo);
    }

    @Override
    public AppUserPo updateAppUser(AppUserPo appUserPo) {
        return appUserRepository.save(appUserPo);
    }

    @Override
    public AppUserPo findAppUserByUserId(UUID userId) {
        return appUserRepository.findAppUserByUserId(userId);
    }

    @Override
    public WatchListPo addStockToWatchList(AddStock2WatchListRequest request) {
        return watchListClient.addStockToWatchList(request);
    }

    @Override
    public AppUserPo save(AppUserPo appUserPo) {
        return appUserRepository.save(appUserPo);
    }
}
